package com.rongke.web.modules.sys.controller;

import com.rongke.model.SysDeciding;
import com.rongke.service.SysDecidingService;
import com.rongke.utils.ans.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/sys/deciding")
public class SysDecidingContriller {

    @Autowired
    private SysDecidingService sysDecidingService;

    /**
     *  获得所有决策内容
     */
    @CrossOrigin
    @RequestMapping("/getAll")
    public R getAll(){
        return sysDecidingService.decidingAll();
    }


    /**
     * 添加所有内容
     */
    @RequestMapping("/saveBatch")
    public R saveAll(@RequestBody List<SysDeciding> list){
        if(!list.isEmpty()){
            sysDecidingService.insertOrUpdateBatch(list);
        }
        return R.ok();
    }



}
