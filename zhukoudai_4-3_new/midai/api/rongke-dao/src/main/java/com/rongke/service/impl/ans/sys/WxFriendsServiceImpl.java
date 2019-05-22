package com.rongke.service.impl.ans.sys;

import com.rongke.mapper.ans.WxFriendsDao;
import com.rongke.model.ans.WxFriendsEntity;
import com.rongke.service.ans.sys.WxFriendsService;
import com.rongke.utils.ans.MapUtils;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.Query;
import com.rongke.utils.ans.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("wxFriendsService")
public class WxFriendsServiceImpl extends ServiceImpl<WxFriendsDao, WxFriendsEntity> implements WxFriendsService {

    public PageUtils queryPage(Map<String, Object> params) {
        Page<WxFriendsEntity> page = this.selectPage(
                new Query<WxFriendsEntity>(params).getPage(),
                new EntityWrapper<WxFriendsEntity>()
        );
        return new PageUtils(page);
    }

    /** 根据id获取好友名字于头像*/
    public PageUtils selectWxUsrFriends(Map<String, Object> params) {
        Page<Map<String, Object>> page = new Query<Map<String, Object>>(params).getPage();
        Map<String, Object> search =  new MapUtils().put("accountID", params.get("accountID"));
        if(StringUtils.isNotBlank((String)params.get("search"))){
            search.put("search",params.get("search"));
        }
        page.setRecords(this.baseMapper.selectWxUsrFriends(page,search));
        return new PageUtils(page);
    }

    /** 根据id获取好友名字于头像*/
    public R searchWxUsrFriends(Map<String, Object> params) {
        Map<String, Object> search =  new MapUtils().put("accountID", params.get("accountID"));
        if(params.get("targetID")==null){
            search.put("targetidIN",params.get("targetidIN"));
        }else{
            search.put("targetID",params.get("targetID"));
        }

        List<Map<String, Object>> maps = this.baseMapper.selectWxUsrFriends(null, search);
        if(maps.isEmpty()){
            return R.error();
        }

        return R.ok();
    }
}
