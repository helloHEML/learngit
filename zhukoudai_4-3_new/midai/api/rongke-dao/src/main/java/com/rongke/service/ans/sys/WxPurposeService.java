package com.rongke.service.ans.sys;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.ans.WxPurposeEntity;
import com.rongke.utils.ans.PageUtils;

import java.util.Map;

/**
 * 微信 ——  借条 借款用途
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
public interface WxPurposeService extends IService<WxPurposeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

