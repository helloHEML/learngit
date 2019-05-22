package com.rongke.web.ans.utils;

import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.Map;

public class MoneyUtils {

    public static String wan(BigDecimal money){
        if(money==null){
            return "0元";
        }
        BigDecimal ljz = new BigDecimal("10000");
        if(money.compareTo(new BigDecimal("10000"))==-1){//小于一万
           return money.stripTrailingZeros().toPlainString()+"元";
        }else{
//            return money.divide(new BigDecimal("10000"));
        }
        return "";
    }

    public static void main(String[] args) {
        BigDecimal money = new BigDecimal("1.00");
//        if(){
//
//        }
        System.out.println(money.divide(new BigDecimal("10000")).stripTrailingZeros().toPlainString()+"万元");
        System.out.println(money.divide(new BigDecimal("1")).stripTrailingZeros().toPlainString()+"万元");
    }

}
