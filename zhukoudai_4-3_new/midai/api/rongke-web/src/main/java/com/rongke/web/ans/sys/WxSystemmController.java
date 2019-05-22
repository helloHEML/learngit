package com.rongke.web.ans.sys;

import java.util.Arrays;
import java.util.Map;

import com.rongke.model.ans.WxSystemmEntity;
import com.rongke.service.ans.sys.WxSystemmService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信服务费
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-24 09:21:04
 */
@RestController
@RequestMapping("sys/wxsystemm")
public class WxSystemmController {
    @Autowired
    private WxSystemmService wxSystemmService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxSystemmService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WxSystemmEntity wxSystemm = wxSystemmService.selectById(id);

        return R.ok().put("wxSystemm", wxSystemm);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WxSystemmEntity wxSystemm){
        wxSystemmService.insert(wxSystemm);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WxSystemmEntity wxSystemm){
        wxSystemmService.updateAllColumnById(wxSystemm);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wxSystemmService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
