package com.rongke.service.impl.ans.sys;import com.rongke.mapper.ans.DebitRecordDao;import com.rongke.model.ans.DebitRecordEntity;import com.rongke.service.ans.sys.DebitRecordService;import com.rongke.utils.ans.PageUtils;import com.rongke.utils.ans.Query;import org.springframework.stereotype.Service;import java.util.Map;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.baomidou.mybatisplus.service.impl.ServiceImpl;@Service("debitRecordService")public class DebitRecordServiceImpl extends ServiceImpl<DebitRecordDao, DebitRecordEntity> implements DebitRecordService {    @Override    public PageUtils queryPage(Map<String, Object> params) {        Page<DebitRecordEntity> page = this.selectPage(                new Query<DebitRecordEntity>(params).getPage(),                new EntityWrapper<DebitRecordEntity>()        );        return new PageUtils(page);    }}