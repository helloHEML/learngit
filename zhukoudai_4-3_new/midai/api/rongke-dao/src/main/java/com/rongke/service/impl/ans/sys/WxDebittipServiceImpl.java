package com.rongke.service.impl.ans.sys;

import com.rongke.mapper.ans.WxDebittipDao;
import com.rongke.model.ans.WxDebittipEntity;
import com.rongke.service.ans.sys.WxDebittipService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("wxDebittipService")
public class WxDebittipServiceImpl extends ServiceImpl<WxDebittipDao, WxDebittipEntity> implements WxDebittipService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<WxDebittipEntity> page = this.selectPage(
                new Query<WxDebittipEntity>(params).getPage(),
                new EntityWrapper<WxDebittipEntity>()
        );

        return new PageUtils(page);
    }

}
