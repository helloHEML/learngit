package com.rongke.service.ans.sys;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.ans.WxFriendsEntity;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;

import java.util.Map;

/**
 * 微信 好友
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
public interface WxFriendsService extends IService<WxFriendsEntity> {

    PageUtils queryPage(Map<String, Object> params);
    /**获得微信 好友列表有分页*/
    PageUtils selectWxUsrFriends(Map<String, Object> params);
    /**获得微信 好友某人*/
     R searchWxUsrFriends(Map<String, Object> params);

}

