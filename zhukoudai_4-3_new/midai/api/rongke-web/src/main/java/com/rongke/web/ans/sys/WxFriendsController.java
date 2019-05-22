package com.rongke.web.ans.sys;

import java.util.Arrays;
import java.util.Map;

import com.rongke.model.ans.WxFriendsEntity;
import com.rongke.service.ans.sys.WxFriendsService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 微信 好友
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
@RestController
@RequestMapping("sys/wxfriends")
public class WxFriendsController {
    @Autowired
    private WxFriendsService wxFriendsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxFriendsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WxFriendsEntity wxFriends = wxFriendsService.selectById(id);

        return R.ok().put("wxFriends", wxFriends);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WxFriendsEntity wxFriends){
        wxFriendsService.insert(wxFriends);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WxFriendsEntity wxFriends){
        wxFriendsService.updateAllColumnById(wxFriends);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wxFriendsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
