package com.rongke.service;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.UserPhone;import com.rongke.model.UserZhifubao;import java.util.List;import java.util.Map;/** * @UserZhifubaoService * @Service * @version : Ver 1.0 */public interface UserZhifubaoService extends IService<UserZhifubao>{    List<UserZhifubao> selectByCondition(UserZhifubao userZhifubao);    Integer selectPartCount(Map<String, Object> map);    List<UserPhone> selectPartByPage(Map<String, Object> map);}