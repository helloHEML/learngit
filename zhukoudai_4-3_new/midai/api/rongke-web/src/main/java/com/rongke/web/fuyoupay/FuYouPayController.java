package com.rongke.web.fuyoupay;

import com.rongke.alipayUtil.alipay.util.httpClient.HttpRequest;
import com.rongke.service.UserService;
import com.rongke.utils.ans.R;
import com.rongke.web.fuyoupay.service.IFuYouPay;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("/api/fuyou")
public class FuYouPayController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private IFuYouPay iFuYouPay;

    /**
     * 查询银行卡是否绑定
     */
    @RequestMapping("/cardBinQuery")
    public R cardBinQuery(@RequestParam String cardNo){
        return iFuYouPay.cardBinQuery(cardNo);
    }


    /**
     * 认证绑定
     */
    @RequestMapping("/authBangdingCard1")
    public R bangdingCard1(@RequestParam Map<String,String> params){
        return iFuYouPay.authBangdingCard1(params);
    }
    /**
     * 认证绑定
     */
    @RequestMapping("/authBangdingCard2")
    public R authBangdingCard2(@RequestParam Map<String,String> params){
        return iFuYouPay.authBangdingCard2(params);
    }

    /**
     * 绑卡查询
     */
    @RequestMapping("/bindQuery")
    public R bindQuery(){
        return iFuYouPay.bindQuery();
    }

    /**
     * 解绑
     */
    @RequestMapping("/bankUnbind")
    public R bankUnbind(@RequestParam Map<String,String> params){
        return iFuYouPay.bankUnbind(params);
    }

    /**
     * 付款
     */
    @RequestMapping("/orderPay")
    public R orderPay(@RequestParam Map<String,String> params){
        return iFuYouPay.orderPay(params);
    }

    /**
     *  打款
     */
    @CrossOrigin
    @RequestMapping("/paymentUser")
    public R paymentUser(@RequestParam Map<String,String> params) throws  Exception{
        log.info("打款发起"+params.toString());
        return iFuYouPay.paymentUser(params);
    }


    @RequestMapping("/sssss")
    public String sssss(@RequestParam Map<String,Object> param){
        log.info("付款回调="+param.toString());
        return "SUCCESS";
    }


    /**
     * 代付成功
     * @param param
     * @return
     */
    @RequestMapping("/paymentCallbackSuces")
    public String paymentCallbackSuces(@RequestParam Map<String,String> param){
          log.info("富有支付回调信息:"+ JSONObject.fromObject(param).toString());
          iFuYouPay.paymentCallbackSuces(param);
        return "SUCCESS";
    }

    /**
     * 退票
     */
    @RequestMapping("/paymentCallbackErr")
    public String paymentCallbackErr(@RequestParam Map<String,Object> param){
        log.info("退票："+param.toString());
        return "SUCCESS";
    }

}
