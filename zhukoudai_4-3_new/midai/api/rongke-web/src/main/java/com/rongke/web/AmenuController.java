package com.rongke.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.rongke.model.Admin;
import com.rongke.model.AdminPermission;
import com.rongke.service.AdminPermissionService;
import com.rongke.service.AdminService;
import com.rongke.utils.ans.R;
import com.rongke.web.annotation.AUTH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/menu")
public class AmenuController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminPermissionService adminPermissionService;//菜单


    /**
     * 获得所有菜单
     */
    @CrossOrigin
    @RequestMapping(value="/menuAll", method = RequestMethod.GET)
    public List<AdminPermission> menuAll(){
//        Admin loginUser = adminService.findLoginUser();
        List<AdminPermission> adminPermissions = adminPermissionService.selectList(new EntityWrapper<AdminPermission>()
                .eq("available", "1"));
        //获取所有一级节点
        List<AdminPermission> one = Lists.newArrayList();
        for(AdminPermission a:adminPermissions){
            if(a.getParentid()==0){
                List<AdminPermission> two = Lists.newArrayList();
                for(AdminPermission b:adminPermissions){
                    if(a.getId()==b.getParentid()){
                        two.add(b);
                    }
                }
                a.setAdminPermissions(two);
                one.add(a);
            }
        }
        return one;
    }

    /**
     * 获得个人菜单内容
     */
    @AUTH
    @RequestMapping(value="/adminMenuAll", method = RequestMethod.GET)
    public R adminMenuAll(){
        Admin loginUser = adminService.findLoginUser();
        Integer adminRoleId = loginUser.getAdminRoleId();
        List<AdminPermission> adminPermissions = Lists.newArrayList();
        if(loginUser.getId()==1){
            adminPermissions = adminPermissionService.selectList(new EntityWrapper<AdminPermission>().orderDesc(Arrays.asList("pindex")));
        }else{
            adminPermissions = adminPermissionService.selectList(new EntityWrapper<AdminPermission>()
                    .eq("available","1")
                    .where("id in (select admin_permission_id from admin_role_permission where admin_role_id = {0})",adminRoleId)
                    .orderDesc(Arrays.asList("pindex"))
            );
        }
        List<AdminPermission> menu1 = Lists.newArrayList();
        List<AdminPermission> menu2 = Lists.newArrayList();
        for(AdminPermission a:adminPermissions){
            if(a.getParentid()==0){
                menu1.add(a);
            }else{
                menu2.add(a);
            }
        }
        for(AdminPermission m1:menu1){
          List<AdminPermission> erzi = Lists.newArrayList();
            for(AdminPermission m2:menu2){
                if(m1.getId().equals(m2.getParentid())){
                    erzi.add(m2);
                }
            }
            m1.setAdminPermissions(erzi);
        }
        return R.ok().put("menu",menu2).put("parentMenu",menu1).put("adminName",loginUser.getName()).put("data",menu1);
    }

}
