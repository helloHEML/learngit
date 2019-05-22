package com.rongke.web.ans.sys;

import java.util.Arrays;
import java.util.Map;

import com.rongke.model.ans.WxDebittipEntity;
import com.rongke.service.ans.sys.WxDebittipService;
import com.rongke.utils.ans.PageUtils;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 微信——服务费
 *
 * @author Ans
 * @email 867917319@qq.com
 * @date 2018-08-26 17:51:56
 */
@RestController
@RequestMapping("sys/wxdebittip")
public class WxDebittipController {
    @Autowired
    private WxDebittipService wxDebittipService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = wxDebittipService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        WxDebittipEntity wxDebittip = wxDebittipService.selectById(id);

        return R.ok().put("wxDebittip", wxDebittip);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody WxDebittipEntity wxDebittip){
        wxDebittipService.insert(wxDebittip);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody WxDebittipEntity wxDebittip){
        wxDebittipService.updateById(wxDebittip);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        wxDebittipService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

}
