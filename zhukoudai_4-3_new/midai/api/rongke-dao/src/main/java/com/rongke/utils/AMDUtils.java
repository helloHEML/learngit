package com.rongke.utils;

import com.rongke.model.Authentication;
import com.rongke.model.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class AMDUtils {
    public static void UserAuthS(User u, Authentication a){
        if(u!=null&&a!=null&&u.getId().equals(a.getUserId())){
            List<String> auth = Arrays.asList(a.getPhoneRecordAuth(),a.getBankCardAuth(), a.getUserBaseMsgAuth(), a.getPhoneRecordAuth(), a.getIdentityAuth(), a.getTaobaoAuth());
            if(auth.contains("0")){u.setAuthStatus("未认证");}
            if(auth.contains("3")){u.setAuthStatus("未认证");}
            if(auth.contains("2")){u.setAuthStatus("认证失败");}
            if(StringUtils.isBlank(u.getAuthStatus())){
                if(!auth.contains("0")&&!auth.contains("2")&&!auth.contains("3")){ u.setAuthStatus("已认证");}
            }
        }
    }

}
