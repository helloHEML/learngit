package com.rongke.mapper;import com.baomidou.mybatisplus.mapper.BaseMapper;import com.rongke.model.UserZm;import java.util.List;import java.util.Map;/** * @UserZmMapper * @Mapper * @version : Ver 1.0 */public interface UserZmMapper extends BaseMapper<UserZm>{    List<UserZm>  selectByCondition(UserZm userZm);    Integer selectPartCount(Map<String, Object> map);    List<UserZm> selectPartByPage(Map<String, Object> map);}