package com.rongke.utils;import java.util.regex.Matcher;import java.util.regex.Pattern;/** * Created by cui on 2017/8/10. */public class MobileUtil {    /**     * 验证手机号码 1开头 11位     */    public static boolean isPhoneBase(String phoneNum) {        try {            if (phoneNum.length() != 11) {                return false;            }            if (phoneNum.startsWith("1")) {                return true;            }        } catch (Exception e) {            return false;        }        return false;    }    public static void main(String[] args) {        boolean phoneBase = isPhoneBase("13599999999");        System.out.println(phoneBase);        boolean checkEmail = checkEmail("asfsdfgsdgdsgdsasfdasfasfasfafsfasf@qq.com");        System.out.println(checkEmail);    }    /**     * 邮箱验证     * @param email     * @return     */    public static boolean checkEmail(String email){        boolean flag = false;        try{            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";            Pattern regex = Pattern.compile(check);            Matcher matcher = regex.matcher(email);            flag = matcher.matches();        }catch(Exception e){            flag = false;        }        return flag;    }}