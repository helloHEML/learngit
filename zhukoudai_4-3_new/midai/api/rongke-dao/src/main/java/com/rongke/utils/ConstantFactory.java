package com.rongke.utils;

import com.rongke.constant.ConfigConstant;
import com.rongke.constant.FuYouConstant;
import com.rongke.constant.OSSConstant;
import com.rongke.model.Proconstant;
import com.rongke.model.TConstant;
import com.rongke.rediscluster.SpringContextUtils;
import com.rongke.service.ConstantService;
import com.rongke.service.ProconstantService;
import com.rongke.utils.ans.ConvertUtils;

import java.util.Date;

/**
 * 常量工具类
 */
public class ConstantFactory {
    private static ConfigConstant configConstant;
    private static FuYouConstant fuYouConstant;
    private static OSSConstant ossConstant;
    private static Proconstant proconstant;
    static {
        ConstantService constantService = (ConstantService) SpringContextUtils.getBean("constantService");
        TConstant constant = constantService.getConstant();
        ConstantFactory.configConstant = ConvertUtils.sourceToTarget(constant,ConfigConstant.class);
//        把类的属性copy给constant
        ConstantFactory.fuYouConstant = ConvertUtils.sourceToTarget(constant,FuYouConstant.class);
        ConstantFactory.ossConstant = ConvertUtils.sourceToTarget(constant,OSSConstant.class);
        System.err.println("加载ConstantFactory:" + System.currentTimeMillis());
        System.err.println("加载ConstantFactory:" + new Date().getTime());
        ProconstantService pService = (ProconstantService) SpringContextUtils.getBean("proconstantService");
        ConstantFactory.proconstant = pService.getProconstant();
        System.out.println(ConstantFactory.proconstant.toString());
    }

    public static ConfigConstant getConfig(){
        return configConstant;
    }

    public static FuYouConstant getFuYouConfig(){
        return fuYouConstant;
    }

    public static OSSConstant getOSSConfig(){
        return ossConstant;
    }

    public static Proconstant getProconstant(){
        return proconstant;
    }

}
