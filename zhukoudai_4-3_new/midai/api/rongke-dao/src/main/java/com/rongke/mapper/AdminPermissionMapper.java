package com.rongke.mapper;import com.rongke.model.AdminPermission;import com.baomidou.mybatisplus.mapper.BaseMapper;import java.util.List;/** * @AdminPermissionMapper * @Mapper * @version : Ver 1.0 */public interface AdminPermissionMapper extends BaseMapper<AdminPermission>{    List<AdminPermission>  selectByCondition(AdminPermission adminPermission);    List<Integer> selectMenus(Integer roleId);}