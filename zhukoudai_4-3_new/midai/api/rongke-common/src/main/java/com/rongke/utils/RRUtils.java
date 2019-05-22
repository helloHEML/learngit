package com.rongke.utils;

import com.rongke.utils.ans.R;

public class RRUtils {


    public static boolean assertCode(R r,String code){
        if(r.get("code").toString().equals(code)){
            return true;
        }
        return false;
    }


}
