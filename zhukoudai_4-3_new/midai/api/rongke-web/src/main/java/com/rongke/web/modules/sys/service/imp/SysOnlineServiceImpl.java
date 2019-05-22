package com.rongke.web.modules.sys.service.imp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongke.enums.SMSEnums;
import com.rongke.mapper.SysOnlineMapper;
import com.rongke.model.Admin;
import com.rongke.model.LogSms;
import com.rongke.model.SysOnline;
import com.rongke.rediscluster.CacheUtil;
import com.rongke.utils.DateUtils;
import com.rongke.utils.RequestUtil;
import com.rongke.utils.ans.CookieUtils;
import com.rongke.utils.ans.R;
import com.rongke.web.common.IPConfig;
import com.rongke.web.modules.sys.service.LogSmsService;
import com.rongke.web.modules.sys.service.SysOnlineService;
import com.rongke.web.sms.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 在线列表
 */
@Service
public class SysOnlineServiceImpl extends ServiceImpl<SysOnlineMapper,SysOnline> implements SysOnlineService {

    @Autowired
    private SmsUtils smsUtil;
    @Autowired
    private CacheUtil cache;
    @Autowired
    private LogSmsService logSmsService;

    /**
     *  在线登陆
     */
    public R online(Map<String,Object> params){
        Admin admin = (Admin) params.get("admin");
        HttpServletRequest request = RequestUtil.getRequest();
        HttpServletResponse response = RequestUtil.getResponse();
        String cookie = CookieUtils.getCookieValue(request, CookieUtils.ASN_TOKEN);
        SysOnline online = null;
        Date date = new Date();
        String addrs = "";
//        JSONObject json = JSON.parseObject(IPConfig.ipAddrs((String) params.get("IPKEY"), request));
//        if(json.getIntValue("code")==200){
//            JSONArray jsonArray = json.getJSONObject("data").getJSONArray("multiAreas");
//            if(!jsonArray.isEmpty()){
//                JSONObject jsonObject = jsonArray.getJSONObject(0);
//                addrs = jsonObject.getString("prov") + jsonObject.getString("city") + jsonObject.getString("district");
//            }
//        }
//        if((StringUtils.isBlank(cookie)||StringUtils.isBlank(addrs))&&StringUtils.isBlank((String)params.get("code"))){
//            SecurityUtils.getSubject().logout();
//            return R.ok("需要短信验证").put("sms","1");
//        }
        online = this.selectOne(new EntityWrapper<SysOnline>().eq("token", cookie));
        if(online==null&&StringUtils.isBlank((String)params.get("code"))){
            SecurityUtils.getSubject().logout();
            return R.ok("需要短信验证").put("sms","1");
        }

        if(StringUtils.isBlank(admin.getPhone())){
            SecurityUtils.getSubject().logout();
            return R.error("手机号未设置，请联系总管理员设置验证手机");
        }

        if(StringUtils.isBlank((String)params.get("code"))){
            long days = DateUtils.getDistanceDays(DateUtils.dateSimpleSFM(online.getExpiredTime()), DateUtils.nowDate(DateUtils.DEFAULT_DATE_FORMAT));
            if(online.getExpiredTime().getTime()<new Date().getTime()&&days>0){
                SecurityUtils.getSubject().logout();
                return R.ok("需要短信验证").put("sms","1");
            }
        }
        String token = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        if(StringUtils.isNotBlank((String)params.get("code"))){
            LogSms logSms = logSmsService.selectOne(new EntityWrapper<LogSms>()
                    .eq("type", SMSEnums.LOGIN_SMS.getType())
                    .eq("target", params.get("username"))
                    .orderDesc(Arrays.asList("create_time"))
            );
            if(logSms==null){
                return R.error("验证码错误，请重新获取");
            }
            if(logSms.getIsuse()==1){
                return R.error("验证码已使用，请重新获取。");
            }
            if(logSms.getExpriedTime().getTime()<System.currentTimeMillis()){
                logSms.setIsuse(1);
                logSms.setResult("过期");
                logSmsService.updateById(logSms);
                return R.error("验证码已过期");
            }
            if(!logSms.getCode().equals(String.valueOf(params.get("code")))){
                logSms.setIsuse(1);
                logSms.setResult("输入错误");
                logSmsService.updateById(logSms);
                return R.error("验证码错误.请重新获取");
            }
            logSms.setIsuse(1);
            logSms.setResult("已使用");
            logSmsService.updateById(logSms);
        }
        if(StringUtils.isNotBlank(cookie)){
            online = this.selectOne(new EntityWrapper<SysOnline>().eq("token", cookie));
            if(online==null){
                online = new SysOnline();
                online.setAdminId(admin.getId());
                online.setName(admin.getLoginName());
            }
        }else{
            online = new SysOnline();
        }
        online.setName(admin.getLoginName());
        online.setAdminId(admin.getId());
        online.setCreateTime(date);
        online.setExpiredTime(new Date(date.getTime()+ 2 * 3600 * 1000));
        online.setIp(IPConfig.getIpAddr(request));
        online.setIpAddr(addrs);
        online.setToken(token);
        online.setSessionId(String.valueOf(params.get("sessionId")));
        online.setUpdateTime(new Date());
        online.setStatus(1);
        CookieUtils.setCookie(response,CookieUtils.ASN_TOKEN,token,365 * 24 * 60 * 60);
        this.insertOrUpdate(online);
        return R.ok().put("sms","0");
    }

    public SysOnline onlineG() {
        String cookie = CookieUtils.getCookieValue(RequestUtil.getRequest(), CookieUtils.ASN_TOKEN);
        SysOnline online = this.selectOne(new EntityWrapper<SysOnline>().eq("token", cookie));
        return online;
    }

    public static void main(String[] args){
        System.out.println(DateUtils.getDistanceDays("2018-12-05", DateUtils.nowDate(DateUtils.DEFAULT_DATE_FORMAT)));
    }

}
