package com.rongke.service.impl.ans.sys;

import com.rongke.mapper.ans.WxSystemmDao;
import com.rongke.model.ans.WxSystemmEntity;
import com.rongke.service.ans.sys.WxSystemmService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("wxSystemmService")
public class WxSystemmServiceImpl extends ServiceImpl<WxSystemmDao, WxSystemmEntity> implements WxSystemmService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WxSystemmEntity> page = this.selectPage(
                new Query<WxSystemmEntity>(params).getPage(),
                new EntityWrapper<WxSystemmEntity>()
        );

        return new PageUtils(page);
    }

}
