package com.rongke.web.ans.sys;import java.util.Arrays;import java.util.Map;import com.rongke.model.ans.WxAboutEntity;import com.rongke.service.ans.sys.WxAboutService;import com.rongke.utils.ans.PageUtils;import com.rongke.utils.ans.R;import org.apache.shiro.authz.annotation.RequiresPermissions;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestParam;import org.springframework.web.bind.annotation.RestController;/** * 微信  富文本 * * @author Ans * @email 867917319@qq.com * @date 2018-08-22 15:48:29 */@RestController@RequestMapping("sys/wxabout")public class WxAboutController {    @Autowired    private WxAboutService wxAboutService;    /**     * 列表     */    @RequestMapping("/list")    public R list(@RequestParam Map<String, Object> params){        PageUtils page = wxAboutService.queryPage(params);        return R.ok().put("page", page);    }    /**     * 信息     */    @RequestMapping("/info/{id}")    public R info(@PathVariable("id") Long id){        WxAboutEntity wxAbout = wxAboutService.selectById(id);        return R.ok().put("wxAbout", wxAbout);    }    /**     * 保存     */    @RequestMapping("/save")    public R save(@RequestBody WxAboutEntity wxAbout){        wxAbout.setType(1);        wxAbout.setTypeRemark("关于我们");        wxAbout.setStatus("1");        wxAboutService.insert(wxAbout);        return R.ok();    }    /**     * 修改     */    @RequestMapping("/update")    public R update(@RequestBody WxAboutEntity wxAbout){        wxAboutService.updateById(wxAbout);//全部更新                return R.ok();    }    /**     * 删除     */    @RequestMapping("/delete")    public R delete(@RequestParam Long[] ids){        wxAboutService.deleteBatchIds(Arrays.asList(ids));        return R.ok();    }}