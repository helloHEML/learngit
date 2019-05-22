package com.rongke.service.ans.sys;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.ans.WxSystemmEntity;
import com.rongke.utils.ans.PageUtils;

import java.util.Map;

/**
 * 微信服务费
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-24 09:21:04
 */
public interface WxSystemmService extends IService<WxSystemmEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

