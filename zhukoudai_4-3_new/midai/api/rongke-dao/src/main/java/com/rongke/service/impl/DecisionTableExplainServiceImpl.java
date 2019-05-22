package com.rongke.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongke.mapper.DecisionTableExplainMapper;
import com.rongke.model.DecisionTableExplain;
import com.rongke.service.DecisionTableExplainService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Service
public class DecisionTableExplainServiceImpl extends ServiceImpl<DecisionTableExplainMapper, DecisionTableExplain> implements DecisionTableExplainService {

    @Override
    public PageUtils getPages(Map<String, Object> params) {
        Page<DecisionTableExplain> page = this.selectPage(new Query<DecisionTableExplain>(params).getPage(),
                new EntityWrapper<DecisionTableExplain>().orderAsc(Arrays.asList("id"))
        );
        return new PageUtils(page);
    }
}
