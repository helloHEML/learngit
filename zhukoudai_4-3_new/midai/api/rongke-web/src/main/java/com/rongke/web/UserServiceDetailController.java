package com.rongke.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rongke.commons.JsonResp;
import com.rongke.utils.HttpClientUtil;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value="/api/userServiceDetail")
@Transactional
@CrossOrigin
public class UserServiceDetailController {

    private Logger log = Logger.getLogger(this.getClass());
    static final String token = "50dec4d4aec13fa549ad36173281d7e4";

    /**
     *用户服务充值记录
     * http://47.111.9.78:2222/user/userRechargeslist?token=c946e14ed60395e7b7f440858af5f9f3&page=1&limit=10
     * */
    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public JsonResp recharge(@RequestParam Map<String,Object> params) {
        log.debug("用户服务充值记录");
        String url = "http://47.111.9.78:2222/user/userRechargeslist";
        System.out.println("****************into**********************"+params);
        String page = params.get("page").toString();
        String limit = params.get("limit").toString();
        JSONObject json = sameMethod(url,page,limit);
        return JsonResp.ok(json);
    }

    /**
     *用户服务调用列表
     * http://47.111.9.78:2222/user/userApiInvokeslist?token=c946e14ed60395e7b7f440858af5f9f3&page=1&limit=10
     * */
    @RequestMapping(value = "/research", method = RequestMethod.GET)
    public JsonResp research(@RequestParam Map<String,Object> params) {
        log.debug("用户服务充值记录");
        String url = "http://47.111.9.78:2222/user/userApiInvokeslist";
        String page = params.get("page").toString();
        String limit = params.get("limit").toString();
        JSONObject json = sameMethod(url,page,limit);
        return JsonResp.ok(json);
    }

    public JSONObject sameMethod(String url, String page, String limit){
        String urls = url+"?token="+token+"&page="+page+"&limit="+limit;
        String result = HttpClientUtil.sendGetSSLRequest(urls,"UTF-8");
        JSONObject json = JSON.parseObject(result);
        return json;
    }

}
