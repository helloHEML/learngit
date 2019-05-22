package com.rongke.web.saas;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongke.commons.JsonResp;
import com.rongke.model.Address;
import com.rongke.utils.HttpClientUtil;
import com.rongke.utils.ans.R;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/api/saas")
@Transactional
@CrossOrigin
public class SaasCoreController {

    /**
     * @查询服务余额
     *@return 返回值JsonResp
     */
    @RequestMapping(value="/getBalance", method = RequestMethod.GET)
    public JSONObject addAddress(){
        String url="http://47.111.9.78:2222/user/accountBalance";
        String token="50dec4d4aec13fa549ad36173281d7e4";
        String body = "token=" + token;
        String result = HttpClientUtil.sendPostSSLRequest(url, body, "UTF-8", "application/x-www-form-urlencoded");
        return JSON.parseObject(result);
    }
}





