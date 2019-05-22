package com.rongke.mapper.ans;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.rongke.model.ans.WxFriendsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 微信 好友
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
public interface WxFriendsDao extends BaseMapper<WxFriendsEntity> {

    /** 根据id获取好友名字于头像*/
    List<Map<String,Object>> selectWxUsrFriends (Pagination page, Map<String,Object> params);

}
