package com.rongke.web.modules.test;

import com.rongke.utils.ConstantFactory;
import com.rongke.utils.ans.R;
import com.rongke.web.ans.utils.TcreditAPI;
import com.rongke.web.fuyou.base.APIBaseServlet;
import com.rongke.web.modules.cloud.AliyunCloudStorageService;
import com.rongke.web.modules.cloud.OSSFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sys/test")
public class SysTestController {

    @RequestMapping(value="/oosUpload", method = RequestMethod.POST)
    public R oosUpload(String data) throws Exception {
        long z = System.currentTimeMillis();
        String s = null;
        if(StringUtils.isNotBlank(data)){
            s = OSSFactory.build().uploadSuffixBase64(data, ".png");
        }
        String ff = AliyunCloudStorageService.GetImageStr("C:\\Users\\ALIENWARE\\Desktop\\dow\\123.jpg");
        long l = System.currentTimeMillis();
        String s1 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s2 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s3 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s4 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s5 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s6 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s7 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s8 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s9 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s10 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s11 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s12 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        String s13 = OSSFactory.build().uploadSuffixBase64(ff, ".jpg");
        System.err.println("上传执行："+(System.currentTimeMillis()-l)+"ms");
        System.err.println("总执行："+(System.currentTimeMillis()-z)+"ms");
        return R.ok().put("url1",s4).put("url2",s5).put("url3",s6);
    }

    @RequestMapping(value="/constant")
    public R constant(){
        return R.ok()
                .put("cehis", TcreditAPI.aa())
                .put("config", ConstantFactory.getConfig())
                .put("fuyou", ConstantFactory.getFuYouConfig())
                .put("oos",ConstantFactory.getOSSConfig())
                .put("fuyou", APIBaseServlet.key+"|"+APIBaseServlet.mchntcd+"|"+APIBaseServlet.backnotifyurl+"|"+ConstantFactory.getFuYouConfig().getFuyouMchntUserPay()+"|"+ConstantFactory.getFuYouConfig().getFuyouIpadder());
    }
}
