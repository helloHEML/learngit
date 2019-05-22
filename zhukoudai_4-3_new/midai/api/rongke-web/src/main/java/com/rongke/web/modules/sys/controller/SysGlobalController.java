package com.rongke.web.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.enums.GlobalEnums;
import com.rongke.model.SysGlobal;
import com.rongke.utils.ConstantFactory;
import com.rongke.utils.ans.R;
import com.rongke.web.modules.sys.service.SysGlobalssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin
@RequestMapping("/sys/global")
@RestController

public class SysGlobalController {

    @Autowired
    private SysGlobalssService sysGlobalService;

    /**
     * 打款修改验证码
     */
    @RequestMapping("/payPassWordCode")
    public R payPassWordCode() throws Exception {
        return sysGlobalService.payPassWordCode();
    }

    /**
     *  修改打款密码
     */
    @RequestMapping("/payPassWord")
    public R payPassWord(@RequestParam Map<String,String> params){
        return sysGlobalService.payPassWord(params);
    }


    /**
     *  获得打款密码
     */
    @RequestMapping("/payPassWords")
    public R payPassWords(){
        return R.ok().put("data",sysGlobalService.key(GlobalEnums.PAYPASSWORD.getValue()));
    }

    /**
     * 手机续期
     */
    @RequestMapping("/isRenewal")
    public R payPassWords(Boolean key){
        SysGlobal key1 = sysGlobalService.key(GlobalEnums.RENEWAL.getValue());
        if(key){
            key1.setSysValues("1");
        }else{
            key1.setSysValues("0");
        }
        sysGlobalService.updateById(key1);
        return R.ok();
    }

    /**
     * 审核打款
     */
    @RequestMapping("/isExamineAndmake")
    public R isExamineAndmake(Boolean key,String sysKeys){
        SysGlobal key1 = sysGlobalService.key(sysKeys);
        if(key){
            key1.setSysValues("1");
        }else{
            key1.setSysValues("0");
        }
        sysGlobalService.updateById(key1);
        return R.ok();
    }


    /**
     * 添加所有内容
     */
    @RequestMapping("/saveBatch")
    public R saveAll(@RequestBody List<SysGlobal> list){
        if(!list.isEmpty()){
            sysGlobalService.insertOrUpdateBatch(list);
        }
        return R.ok();
    }


    /**
     * 手机续期
     */
    @RequestMapping("/getRENEWAL")
    public R getRENEWAL(){
        SysGlobal key1 = sysGlobalService.key(GlobalEnums.RENEWAL.getValue());
        return R.ok().put("data",key1);
    }



    /***
     * 获取审核 打款
     * */
    @RequestMapping("/getMake_and_examine")
    public R getMake_and_examine(){
        EntityWrapper entityWrapper=new EntityWrapper();
        entityWrapper.in("sys_keys",GlobalEnums.EXAMINE.getValue()+","+GlobalEnums.Make.getValue()+","+"max_quota"+","+"min_quota"+","+"stride");
        entityWrapper.in("sys_name",GlobalEnums.Make.getKey()+","+GlobalEnums.EXAMINE.getKey()+","+"最高打款额度"+","+"最低打款额度"+","+"借款幅度");
        List<SysGlobal> key1 = sysGlobalService.selectList(entityWrapper);
        return R.ok().put("data",key1);
    }

    /**
     * 获得项目Config
     */
    @CrossOrigin
    @RequestMapping("/proconstant")
    public R proconstant(){
        return R.ok().put("data", ConstantFactory.getProconstant());
    }


    public static void main(String[] args) throws Exception {
        String asdas = "ä½\u0099é¢\u009Dä¸\u008Dè¶³ | å\u008D¡ä¸\u008Aç\u009A\u0084ä½\u0099é¢\u009Dä¸\u008Dè¶³[1000051]";
        System.out.println(new String(asdas.getBytes("ISO-8859-1"),"UTF-8"));
    }

}
