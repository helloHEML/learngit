package com.rongke.web.ans.sys;

import java.util.Arrays;
import java.util.Map;

import com.rongke.model.ans.WxPurposeEntity;
import com.rongke.service.ans.sys.WxPurposeService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 微信 ——  借条 借款用途
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
@RestController
@RequestMapping("sys/wxpurpose")
public class WxPurposeController {
    @Autowired
    private WxPurposeService wxPurposeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxPurposeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WxPurposeEntity wxPurpose = wxPurposeService.selectById(id);

        return R.ok().put("wxPurpose", wxPurpose);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WxPurposeEntity wxPurpose){
        wxPurpose.setPid(0L);
        wxPurpose.setStatus(1);
        wxPurposeService.insert(wxPurpose);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WxPurposeEntity wxPurpose){
        wxPurposeService.updateById(wxPurpose);//更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestParam Long[] ids){
        wxPurposeService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
