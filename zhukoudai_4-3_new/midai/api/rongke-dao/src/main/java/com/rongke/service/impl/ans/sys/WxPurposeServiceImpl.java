package com.rongke.service.impl.ans.sys;

import com.rongke.mapper.ans.WxPurposeDao;
import com.rongke.model.ans.WxPurposeEntity;
import com.rongke.service.ans.sys.WxPurposeService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.Query;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("wxPurposeService")
public class WxPurposeServiceImpl extends ServiceImpl<WxPurposeDao, WxPurposeEntity> implements WxPurposeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WxPurposeEntity> page = this.selectPage(
                new Query<WxPurposeEntity>(params).getPage(),
                new EntityWrapper<WxPurposeEntity>()
                        .like(StringUtils.isNotBlank((String) params.get("title")),"title",(String)params.get("title"))
                        .eq(StringUtils.isNotBlank((String) params.get("status")),"status",params.get("status"))
        );
        return new PageUtils(page);
    }

}
