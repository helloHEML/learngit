package com.rongke.web.shiro;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.rongke.mapper.AdminMapper;import com.rongke.mapper.ChannelMapper;import com.rongke.mapper.ChannelUserMapper;import com.rongke.mapper.UserMapper;import com.rongke.model.Admin;import com.rongke.model.Channel;import com.rongke.model.ChannelUser;import com.rongke.model.User;import com.rongke.service.AdminService;import com.rongke.service.ChannelUserService;import org.apache.shiro.authc.AuthenticationException;import org.apache.shiro.authc.AuthenticationInfo;import org.apache.shiro.authc.AuthenticationToken;import org.apache.shiro.authc.SimpleAuthenticationInfo;import org.apache.shiro.authz.AuthorizationInfo;import org.apache.shiro.authz.SimpleAuthorizationInfo;import org.apache.shiro.realm.AuthorizingRealm;import org.apache.shiro.subject.PrincipalCollection;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.transaction.annotation.Transactional;import javax.annotation.Resource;/** * 管理员验证 */@Transactionalpublic class AdminPasswordRealm extends AuthorizingRealm {    @Autowired    private AdminService adminService;    @Autowired    private ChannelUserService channelUserService;    /**     * 为当前登录的用户授予权限     */    @Override    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {        //String phone = (String)principals.getPrimaryPrincipal();        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();        try {        } catch (Exception e) {            e.printStackTrace();        }        return authorizationInfo;    }    /**     * 验证当前登录的用户     */    @Override    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {        String userName = (String) token.getPrincipal();        Admin admin = adminService.selectOne(new EntityWrapper<Admin>().eq("login_name",userName));        if (admin != null){            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(admin.getLoginName(), admin.getPassword(), getName());            return authcInfo;        } else {            ChannelUser channelUser = channelUserService.selectOne(new EntityWrapper<ChannelUser>().eq("login_name",userName));            if(channelUser!=null){                System.out.println(channelUser);                AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(channelUser, channelUser.getPassword(), getName());                return authcInfo;            }            return null;        }    }}	