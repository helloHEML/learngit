package com.rongke.web.ans.usr;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.rongke.model.ans.WxPurposeEntity;
import com.rongke.service.ans.sys.WxPurposeService;
import com.rongke.utils.ans.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 微信 借款用途
 */
@RestController
@RequestMapping("api/usrPurpose")
@Transactional
public class WeChatPurposeController {
    @Autowired
    private WxPurposeService wxPurposeService;//用途表

    /**
     * 获得借款用途
     */
    @RequestMapping("/list")
    public R getPurpose(Map<String,Object> params){
        List<WxPurposeEntity> wxPurposeEntities = wxPurposeService.selectList(new EntityWrapper<WxPurposeEntity>()
                .eq(StringUtils.isNotBlank((String) params.get("pid")),"pid",params.get("pid")).orderAsc(Arrays.asList("create_time")));
        List<String> list = Lists.newArrayList();
        for(WxPurposeEntity p:wxPurposeEntities){
            list.add(p.getTitle());
        }
        return R.ok().put("data",list);
    }




}
