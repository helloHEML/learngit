package com.rongke.service;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.UserPhoneRecord;import java.util.List;import java.util.Map;/** * @UserPhoneRecordService * @用户通话记录Service * @version : Ver 1.0 */public interface UserPhoneRecordService extends IService<UserPhoneRecord>{    List<UserPhoneRecord> selectByCondition(UserPhoneRecord userPhoneRecord);    List<UserPhoneRecord> selectByPage(Map<String,Object> map);    Integer selectCount(Map<String,Object> map);    List<String> selectConnTimeTop(Long userId);    List<UserPhoneRecord> selectByUserId(Map<String,Object> map);    void saveCall(List<UserPhoneRecord> userPhoneRecords);    void resetYys();}