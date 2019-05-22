package com.rongke.web.ans.usr;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.ans.WxDebittipEntity;
import com.rongke.service.ans.sys.WxDebittipService;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务费
 */
@RestController
@RequestMapping("api/usrDebitTip")
@Transactional
public class WeChatDebitTipController {
    @Autowired
    private WxDebittipService wxDebittipService;

    /**
     * 获得当前服务费 服务费
     */
    @RequestMapping("/debitTip")
    public R getDebitTip(){
        WxDebittipEntity status = wxDebittipService.selectOne(new EntityWrapper<WxDebittipEntity>().eq("status", "1"));
        if(status == null){
            return R.ok().put("data",0);
        }
        return R.ok().put("data",status.getMoney().toString());
    }


}
