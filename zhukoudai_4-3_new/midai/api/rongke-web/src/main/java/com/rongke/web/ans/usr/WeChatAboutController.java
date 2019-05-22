package com.rongke.web.ans.usr;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.ans.WxAboutEntity;
import com.rongke.service.ans.sys.WxAboutService;
import com.rongke.utils.ans.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * 微信关于列表
 */
@RestController
@RequestMapping("api/usrAbout")
@Transactional
public class WeChatAboutController {
    @Autowired
    private WxAboutService wxAboutService;

    /**获得关于列表*/
    @RequestMapping("/getAbout")
    public R getusrAbout(@RequestParam Map<String,Object> params){
        List<WxAboutEntity> type = wxAboutService.selectList(new EntityWrapper<WxAboutEntity>()
                .setSqlSelect("id,title")
                .eq(StringUtils.isNotBlank((String) params.get("type")),"type", params.get("type"))
                .eq("status",2)
        );
        return R.ok().put("data",type);
    }


    /**获得根据ID获得详情*/
    @RequestMapping("/getAboutDetails")
    public R getAboutDetails(@RequestParam String id){
        WxAboutEntity wxAboutEntity = wxAboutService.selectById(id);
        return R.ok().put("data",wxAboutEntity);
    }


}
