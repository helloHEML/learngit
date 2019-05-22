package com.rongke.service.ans.sys;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.ans.WxDebittipEntity;
import com.rongke.utils.ans.PageUtils;

import java.util.Map;

/**
 * 微信——服务费
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
public interface WxDebittipService extends IService<WxDebittipEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

