package com.rongke.service.impl;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.mapper.SqlHelper;import com.baomidou.mybatisplus.mapper.Wrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import com.rongke.commons.FailException;import com.rongke.commons.JsonCodeEnum;import com.rongke.commons.JsonResp;import com.rongke.mapper.ChannelMapper;import com.rongke.mapper.ChannelUserMapper;import com.rongke.model.AppFeedback;import com.rongke.model.Channel;import com.rongke.model.ChannelUser;import com.rongke.model.DataReport;import com.rongke.service.ChannelService;import com.rongke.service.ChannelUserService;import com.rongke.utils.Md5;import com.rongke.utils.ans.PageUtils;import com.rongke.utils.ans.Query;import com.rongke.utils.ans.R;import org.apache.commons.lang3.StringUtils;import org.apache.shiro.SecurityUtils;import org.apache.shiro.authc.*;import org.apache.shiro.session.Session;import org.apache.shiro.session.mgt.eis.SessionDAO;import org.apache.shiro.subject.PrincipalCollection;import org.apache.shiro.subject.Subject;import org.apache.shiro.subject.support.DefaultSubjectContext;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Arrays;import java.util.Collection;import java.util.List;import java.util.Map;@Servicepublic class ChannelUserServiceImpl extends ServiceImpl<ChannelUserMapper, ChannelUser> implements ChannelUserService{    @Autowired    private  ChannelUserMapper channelUserMapper;    public PageUtils channelUserPage(Map<String, Object> params){        Page<ChannelUser> page = this.selectPage(new Query<ChannelUser>(params).getPage(), new EntityWrapper<ChannelUser>()                .like(StringUtils.isNotBlank((String) params.get("name")),"name", (String) params.get("name"))                .orderDesc(Arrays.asList("update_time"))        );        return new PageUtils(page);    }    /**     * 登陆     */    public R channelUserLogin(String loginName, String password) {        System.out.println(Md5.md5Encode(password));        UsernamePasswordToken token = new UsernamePasswordToken(loginName, Md5.md5Encode(password));        token.setRememberMe(true);        Subject currentUser = SecurityUtils.getSubject();        try {            currentUser.login(token);        }catch(UnknownAccountException uae){            return R.error("未知账户");        }catch(IncorrectCredentialsException ice){            return R.error("密码不正确");        }catch(LockedAccountException lae){            return R.error("账户已锁定");        }catch(ExcessiveAttemptsException eae){            return R.error("用户名或密码错误次数过多");        }catch(AuthenticationException ae){            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景            ae.printStackTrace();            return R.error("用户名或密码不正确");        }        ChannelUser channelUser=null;        //验证是否登录成功        if(currentUser.isAuthenticated()){            channelUser = this.selectOne(new EntityWrapper<ChannelUser>());            channelUser.setToken(currentUser.getSession().getId().toString());            this.updateById(channelUser);        }else{            token.clear();        }        return R.ok().put("data",channelUser);    }    /**     * 获得当前渠道账号     */    public ChannelUser channelUser() {        Subject subject = SecurityUtils.getSubject();        PrincipalCollection collection = subject.getPrincipals();        if (null != collection && !collection.isEmpty()) {            System.out.println(collection.iterator().next());            ChannelUser channelUser =(ChannelUser)collection.iterator().next();            return channelUser;        }        throw new FailException(JsonCodeEnum.OVERTIME.getMessage());    }    /**     * 统计渠道出款数量     */    public R getFkCount(Map<String,Object> params) {        String channelUser=(String) params.get("channelUser");        String channelName=(String) params.get("name");        String time1 = (String) params.get("time1");        String time2 = (String) params.get("time2");        Map<String, Object> fkCount = getFkCount(new EntityWrapper<ChannelUser>()                .eq(StringUtils.isNotBlank(channelUser),"cu.id", channelUser)                .eq(StringUtils.isNotBlank(channelName),"c.id", channelName)                .between(StringUtils.isNotBlank(time1) && StringUtils.isNotBlank(time2), "u.gmt_datetime", time1, time2)        );        return  R.ok().put("data",fkCount);    }    /**     * 自定义mybatis-plus sql查询     */    public  Map<String, Object> getFkCount(Wrapper<ChannelUser> wrapper) {        return this.baseMapper.getFkCount(wrapper);    }}