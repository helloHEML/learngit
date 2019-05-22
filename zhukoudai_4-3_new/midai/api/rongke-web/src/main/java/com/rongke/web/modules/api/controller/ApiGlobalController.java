package com.rongke.web.modules.api.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.SysGlobal;
import com.rongke.utils.ans.R;
import com.rongke.web.modules.sys.service.SysGlobalssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/global")
@RestController
public class ApiGlobalController {

    @Autowired
    private SysGlobalssService sysGlobalService;

    /**
     *  获得是否开启续期功能
     */
    @RequestMapping("/key/{key}")
    public R appDetails(@PathVariable("key") String key){
        SysGlobal sysGlobal = sysGlobalService.selectOne(new EntityWrapper<SysGlobal>().eq("sys_keys", key));
        return R.ok().put("data",sysGlobal);
    }


}
