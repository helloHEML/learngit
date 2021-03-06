package com.rongke.mapper;import com.baomidou.mybatisplus.mapper.Wrapper;import com.rongke.model.Authentication;import com.baomidou.mybatisplus.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;import org.apache.poi.ss.formula.functions.T;import java.util.List;import java.util.Map;/** * @AuthenticationMapper * @用户认证Mapper * @version : Ver 1.0 */public interface AuthenticationMapper extends BaseMapper<Authentication>{    List<Authentication>  selectByCondition(Authentication authentication);    Integer selectPreviousAuditorId();    List<Authentication> selectAuthenList();    List<Authentication> selectAuthenAdmiList(Map map);    List<Map> selectUserAuthenById(Map map);    List<Authentication> authJoinUser();    Integer authJoinUserCount(@Param("ew") Wrapper<Authentication> var1);}