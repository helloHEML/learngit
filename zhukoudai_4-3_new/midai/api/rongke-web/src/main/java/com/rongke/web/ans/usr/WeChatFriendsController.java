package com.rongke.web.ans.usr;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.ans.TblAccountEntity;
import com.rongke.model.ans.WxFriendsEntity;
import com.rongke.service.ans.sys.WxFriendsService;
import com.rongke.service.ans.usr.WeChatUserService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 微信用户好友
 */
@RestController
@RequestMapping("api/usrFriends")
@Transactional
public class WeChatFriendsController {
    @Autowired
    private WxFriendsService wxFriendsService;
    @Autowired
    private WeChatUserService weChatUserService;


    /**
     * 微信好友列表 返回名字和头像
     */
    @RequestMapping("/pageList")
    public R pageWxUsrFriends(@RequestParam  Map<String,Object> params){
        TblAccountEntity tblAccountEntity = weChatUserService.selectWechatUser();
        params.put("accountID",tblAccountEntity.getAccountId());
        PageUtils pageUtils = wxFriendsService.selectWxUsrFriends(params);
        return R.ok().put("total",pageUtils.getTotalCount()).put("data",pageUtils.getList());
    }

    /**
     * 根据ID判断是否为好友
     */
    @RequestMapping("/isFriends")
    public R isFriends(@RequestParam("targerID") String targerID){
        TblAccountEntity tblAccountEntity = weChatUserService.selectWechatUser();

        int i = wxFriendsService.selectCount(new EntityWrapper<WxFriendsEntity>()
                .eq("account_id", tblAccountEntity).eq("target_id", targerID));
        if(i==0){
            return R.error("只能查看好友信息");
        }
        return R.ok();
    }



}
