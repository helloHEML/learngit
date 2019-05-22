package com.rongke.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.*;
import com.rongke.rediscluster.CacheUtil;
import com.rongke.service.*;
import com.rongke.utils.RSMohe;
import com.rongke.web.saas.RSMlAPI;
import com.rongke.web.saas.RSTcreditAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;


//首先指定Junit的Runner
@RunWith(SpringJUnit4ClassRunner.class)
//指明配置文件所在
@ContextConfiguration(locations="classpath:spring-applicationContext.xml")
public class RiskDecisionServiceTest {

//    @Autowired
//   private User

    @Test
    public void test(){
       JSONObject jsonObject =  RSMohe.getAll("TASKYYS100000201905121657550681104182");
        System.out.println(jsonObject);
    }

}