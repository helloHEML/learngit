package com.rongke.service;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.AdminPermission;import java.util.List;/** * @AdminPermissionService * @Service * @version : Ver 1.0 */public interface AdminPermissionService extends IService<AdminPermission>{    List<AdminPermission> selectByCondition(AdminPermission adminPermission);    List<Integer> selectMenus(Integer roleId);}