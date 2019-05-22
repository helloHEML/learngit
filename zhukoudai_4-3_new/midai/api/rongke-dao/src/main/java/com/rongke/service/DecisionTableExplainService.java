package com.rongke.service;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.DecisionTableExplain;
import com.rongke.utils.ans.PageUtils;

import java.util.Map;

public interface DecisionTableExplainService extends IService<DecisionTableExplain> {

    PageUtils getPages(Map<String, Object> params);

}
