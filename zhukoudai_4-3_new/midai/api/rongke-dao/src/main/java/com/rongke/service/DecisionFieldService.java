package com.rongke.service;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.DecisionField;import com.rongke.utils.ans.PageUtils;import java.util.Map;/** * @UserPhoneService * @Service * @version : Ver 1.0 */public interface DecisionFieldService extends IService<DecisionField>{    public PageUtils getPages(Map<String, Object> params);}