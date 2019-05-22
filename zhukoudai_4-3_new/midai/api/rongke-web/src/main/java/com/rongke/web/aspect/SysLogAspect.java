package com.rongke.web.aspect;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.rongke.commons.FailException;
import com.rongke.commons.JsonCodeEnum;
import com.rongke.model.Admin;
import com.rongke.model.SysLogEntity;
import com.rongke.model.SysOnline;
import com.rongke.model.User;
import com.rongke.service.AdminService;
import com.rongke.service.UserService;
import com.rongke.utils.RequestUtil;
import com.rongke.utils.ans.CookieUtils;
import com.rongke.web.annotation.SysLog;
import com.rongke.web.common.IPConfig;
import com.rongke.web.modules.sys.service.SysLogService;
import com.rongke.web.modules.sys.service.SysOnlineService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 日志切面
 */
@Aspect
@Component
public class SysLogAspect {
    public static final String ASN_TOKEN = "anspsq";
    @Autowired
    private SessionDAO sessionDao;
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysOnlineService sysOnlineService;

    /**
     * 操作日志
     */
    @Pointcut("@annotation(com.rongke.web.annotation.SysLog)")
    public void log(){
    }

    /**
     *  验证
     */
    @Pointcut("@annotation(com.rongke.web.annotation.AUTH)")
    public void AUTH(){
    }

    /**
     *  日志记录
     */
    @Around(value = "log()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        System.out.println();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        System.out.println("The method " + ((MethodSignature) point.getSignature()).getMethod().getName()+ " ends with " + result.toString());

        //point
        LogAround(point,time);
        return result;
    }

    /**
     * 验证登陆
     */
    @Around(value = "AUTH()")
    public Object AUTHAround(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = RequestUtil.getRequest();
        HttpServletResponse response = RequestUtil.getResponse();
        access(request,response);
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        System.out.println();
        long time = System.currentTimeMillis() - beginTime;

        //point
        return result;
    }

    public void access(HttpServletRequest request,HttpServletResponse response){
        String value = CookieUtils.getCookieValue(request, CookieUtils.ASN_TOKEN);
        if(StringUtils.isBlank(value)){
            tuichu();
        }
        Admin admin = adminService.findLoginUser();
        SysOnline online = sysOnlineService.selectOne(new EntityWrapper<SysOnline>()
                .eq("admin_id", admin.getId())
                .eq("token", value)
        );
        if(online==null){
            tuichu();
        }
        if(online.getExpiredTime().getTime()<System.currentTimeMillis()){//过期退出
            tuichu();
        }
        if(!online.getIp().equals(IPConfig.getIpAddr(request))){//IP发生改变重新登陆
            tuichu();
        }
        String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
        if(!sessionId.equals(online.getSessionId())){
            online.setSessionId(sessionId);
            if(System.currentTimeMillis()-online.getUpdateTime().getTime()<(10 * 60 * 1000)){//五分钟刷新token
                sysOnlineService.updateById(online);
            }
        }
        if(System.currentTimeMillis()-online.getUpdateTime().getTime()>(10 * 60 * 1000)){//五分钟刷新token
            String token = UUID.randomUUID().toString().replaceAll("-","").toLowerCase();
            online.setToken(token);
            online.setUpdateTime(new Date());
            online.setExpiredTime(new Date(online.getUpdateTime().getTime()+(2 * 3600 * 1000)));
            sysOnlineService.updateById(online);
            CookieUtils.setCookie(response, CookieUtils.ASN_TOKEN,token,365 * 24 * 60 * 60);
        }
    }

    public void tuichu(){
        SecurityUtils.getSubject().logout();
        throw new FailException(JsonCodeEnum.OVERTIME.getMessage());
    }

    /**
     * 日志记录
     */
    private void LogAround(ProceedingJoinPoint joinPoint, long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();//执行方法

        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if(syslog != null){
            //注解上的描述
            sysLog.setOperation(syslog.value());
            if(syslog.type()==0){//不做操作
                sysLog.setTraget("0");
                sysLog.setName("未作拦截");
                sysLog.setType(0);
            }else if(syslog.type()==1){
                SysOnline online = sysOnlineService.onlineG();
                if(online!=null){
                    sysLog.setOnlineId(online.getId());
                }
                Admin admin = adminService.findLoginUser();
                sysLog.setTraget(String.valueOf(admin.getId()));
                sysLog.setName(admin.getName());
                sysLog.setType(1);
            }else if(syslog.type()==2){
                User user = userService.selectCurrentUser();
                sysLog.setTraget(String.valueOf(user.getId()));
                sysLog.setName(userService.selectCurrentUser().getRealName());
                sysLog.setType(2);
            }
        }
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args = joinPoint.getArgs();
        List<Object> list = Lists.newArrayList();
        for(int i=0;i<args.length;i++){
            if(args[i] instanceof HttpServletRequest){
                list.add(((HttpServletRequest)args[i]).getParameterMap());
            }else if(args[i] instanceof HttpServletResponse){

            }else{
                list.add(args[i]);
            }
        }
        try{
            String params = new Gson().toJson(list);
            sysLog.setParams(params);
        }catch (Exception e){

        }
        HttpServletRequest request = RequestUtil.getRequest();
        sysLog.setIp(IPConfig.getIpAddr(request));
        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        sysLogService.insert(sysLog);
    }

}
