package com.rongke.service.impl;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.mapper.SqlHelper;import com.baomidou.mybatisplus.mapper.Wrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;import com.google.common.collect.Lists;import com.rongke.commons.FailException;import com.rongke.commons.JsonCodeEnum;import com.rongke.mapper.AdminMapper;import com.rongke.model.Admin;import com.rongke.model.AdminRole;import com.rongke.service.AdminRoleService;import com.rongke.service.AdminService;import com.rongke.utils.DateUtils;import com.rongke.utils.ans.PageUtils;import com.rongke.utils.ans.Query;import com.rongke.utils.ans.R;import org.apache.commons.lang3.StringUtils;import org.apache.shiro.SecurityUtils;import org.apache.shiro.subject.PrincipalCollection;import org.apache.shiro.subject.Subject;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import javax.annotation.Resource;import java.util.Arrays;import java.util.List;import java.util.Map;/** * @AdminServiceImpl * @后台登录ServiceImpl * @version : Ver 1.0 */@Servicepublic class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {    @Resource    private AdminMapper adminMapper;    @Autowired    private AdminRoleService adminRoleService;//角色    /**     * @根据条件查找后台登录     * @param admin     * @return     */    @Override    public List<Admin> selectByCondition(Admin admin) {        return adminMapper.selectByCondition(admin);    }    @Override    public void logout() {        Subject subject = SecurityUtils.getSubject();        if (subject.isAuthenticated()) {            // session 会销毁，在SessionListener监听session销毁，清理权限缓存            subject.logout();        }    }    @Override    public List<String> selectAdminPermissionList(Map map) {        return adminMapper.selectAdminPermissionList(map);    }    /**     * 获取当前登陆用户     * @return JsonResp     */    public Admin findLoginUser() {        Subject subject = SecurityUtils.getSubject();        PrincipalCollection collection = subject.getPrincipals();        if (null != collection && !collection.isEmpty()) {            String loginName =(String)collection.iterator().next();            Admin selectadmin=new Admin();            selectadmin.setLoginName(loginName);            Admin admin = adminMapper.selectOne(selectadmin);            if(admin==null){                throw new FailException(JsonCodeEnum.OVERTIME.getMessage());            }            if("下线".equals(admin.getStatus())){                throw new FailException(JsonCodeEnum.OVERTIME.getMessage());            }            return admin;        }        throw new FailException(JsonCodeEnum.OVERTIME.getMessage());    }    @Override    public List<Map> getAdminList(Map map) {        return adminMapper.getAdminList(map);    }    @Override    public Integer getAdminListSize() {        return adminMapper.getAdminListSize();    }    @Override    public List<Map> selectAdminList(Map map) {        return adminMapper.selectAdminList(map);    }    @Override    public Admin selectAdmin(Map map) {        return adminMapper.selectAdmin(map);    }    /**     * 管理员列表     */    public PageUtils adminPage(Map<String,Object> params) {        Page<Admin> adminPage = this.selectPage(new Query<Admin>(params).getPage(), new EntityWrapper<Admin>()                .like(StringUtils.isNotBlank((String) params.get("name")), "name", (String) params.get("name"))                .eq(StringUtils.isNotBlank((String) params.get("adminRoleId")), "admin_role_id", params.get("adminRoleId"))                .ne("id","1")                .orderDesc(Arrays.asList(""))        );        List<Integer> roleIds = Lists.newArrayList();        for(Admin admin:adminPage.getRecords()){            if(!roleIds.contains(admin.getAdminRoleId())){                roleIds.add(admin.getAdminRoleId());            }        }        if(!roleIds.isEmpty()){            List<AdminRole> adminRoles = adminRoleService.selectList(new EntityWrapper<AdminRole>()                    .setSqlSelect("id,name")                    .in("id", roleIds));            for(Admin admin:adminPage.getRecords()){                for(AdminRole role:adminRoles){                    if(admin.getAdminRoleId().equals(role.getId())){                        admin.setAdminRole(role);                    }                }            }        }        return new PageUtils(adminPage);    }    /**     *  根据校色类型获得所有管理员     */    public PageUtils adminTypePage(Map<String,Object> params){        List<AdminRole> adminRoles = adminRoleService.selectList(new EntityWrapper<AdminRole>()                .eq(StringUtils.isNotBlank((String) params.get("roleType")), "type", params.get("roleType"))                .eq(StringUtils.isNotBlank((String) params.get("roleId")), "id", params.get("roleId"))                .eq("available", "1")        );        if(adminRoles.isEmpty()){            return new PageUtils(null,0,10,1);        }        List<Object> roleId = Lists.newArrayList();        for(AdminRole role:adminRoles){            if(!roleId.contains(role.getId())){                roleId.add(role.getId());            }        }        Page<Admin> adminPage = this.selectPage(new Query<Admin>(params).getPage(), new EntityWrapper<Admin>()                .setSqlSelect("id,name,admin_role_id as adminRoleId,status")                .like(StringUtils.isNotBlank((String) params.get("adminName")),"name", (String) params.get("adminName"))                .in(!roleId.isEmpty(),"admin_role_id",roleId)        );        for(Admin a:adminPage.getRecords()){            for(AdminRole role:adminRoles){                if(a.getAdminRoleId().equals(role.getId())){                    a.setAdminRole(role);                }            }        }        return new PageUtils(adminPage);    }    /**     * 获得审核员以及手下头工作量     */    public List<Admin> evaCount(){        Long a = System.currentTimeMillis();        List<Admin> adminList = baseMapper.evaCount(new EntityWrapper<Admin>()                .setSqlSelect("id")                .eq("r.type", "1")                .eq("a.status","上线")                .groupBy("a.id ")        );        return adminList;    }    /**     * 获得打款员以及手下头工作量     */    public List<Admin> payAdminCount(){        Long a = System.currentTimeMillis();        List<Admin> adminList = baseMapper.payAdminCount(new EntityWrapper<Admin>()                .setSqlSelect("id")                .eq("r.type", "5")                .eq("a.status","上线")                .groupBy("a.id ")        );        return adminList;    }    /**     * 审核员工作统计     */    public PageUtils TJadminEvaCount(Map<String, Object> params){        String id = (String) params.get("id");        String time1 = (String) params.get("time1");        String time2 = (String) params.get("time2");        if(StringUtils.isBlank(time1)||StringUtils.isBlank(time2)){            time2 = DateUtils.nowDate(DateUtils.YYYY_MM_DD);            time1 = time2.substring(0,8)+"01";        }        long l = System.currentTimeMillis();        Page<Map<String, Object>> mapPage = tjAdminEvaBB(new Query<Map<String, Object>>(params).getPage(),new EntityWrapper<Admin>()                .eq(StringUtils.isNotBlank(id),"a.id",id)                .between(StringUtils.isNotBlank(time1)&&StringUtils.isNotBlank(time2),"b.date",time1,time2)        );        System.err.println(System.currentTimeMillis()-l);        return new PageUtils(mapPage);    }    /**     * 自定义sql     */    /**     * 管理员 审核工作量 统计报表     */    public Page<Map<String,Object>> tjAdminEvaBB(Page<Map<String,Object>> var1,Wrapper<Admin> var2){        SqlHelper.fillWrapper(var1, var2);        var1.setRecords(this.baseMapper.adminEvaCountPage(var1, var2));        return var1;    }    /**     * 根据角色类型获得管理员（不会获得终极管理员）     */    public R selroleAdmin(Map<String,Object> params){        String type = (String) params.get("type");        if(StringUtils.isBlank(type)){            type = "1";        }        List<Admin> adminList = this.selectList(new EntityWrapper<Admin>().setSqlSelect("id,name")                .ne("id", "1")                .where("admin_role_id in (select id from admin_role where type = {0} )", type));        return R.ok().put("data",adminList);    }}