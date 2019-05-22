package com.rongke.mapper;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.rongke.model.ChannelSum;
import com.rongke.model.UserIdentity;
import com.rongke.service.AuthenticationService;
import com.rongke.service.ChannelSumService;
import com.rongke.service.TongDunMessageService;
import com.rongke.service.UserIdentityService;
import com.rongke.utils.ans.Query;
import com.rongke.web.saas.RSSmsUtils;
import com.yeepay.g3.utils.common.encrypt.AES;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
//指明配置文件所在
@ContextConfiguration(locations="classpath:spring-applicationContext.xml")
public class ChannelSumMapperTest {

    @Resource
    private ChannelSumMapper channelSumMapper;
    @Autowired
    private ChannelSumService channelSumService;
    @Autowired
    private TongDunMessageService tongDunMessageService;
    @Resource
    private TongDunMessageMapper tongDunMessageMapper;
    @Autowired
    private UserIdentityService userIdentityService;
    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void test2(){
//        Long a = tongDunMessageMapper.selectTDMUserId(1719L);
//        Long a = tongDunMessageService.showTDMUserId(1719L);
        String a = "1234566";
        System.out.println(a);
    }

    @Test
    public void test3(){
        UserIdentity userIdentity = userIdentityService.selectOne(new EntityWrapper<UserIdentity>().eq("user_id", "1720").
                orderBy("id",false));
        System.out.println(userIdentity);
    }

    @Test
    public void test(){
//        Map params = new HashMap();
//        params.put("page","1");
//        params.put("limit","10");
        ChannelSum channelSum = new ChannelSum();
        channelSum.setCid(18L);
        channelSum.setName("aa");
        System.out.println(channelSumMapper.channelSumInsert(channelSum));
//        List list = channelSumMapper.selectFfrs();
//        System.out.println(list.size());
//        System.out.println(channelSumMapper.updateChennlSum()+"up1");
//        System.out.println(channelSumMapper.updateFfrs()+"up1");
//        Page<ChannelSum> page = channelSumService.selectPage(new Query<ChannelSum>(params).getPage(),
//                new EntityWrapper<ChannelSum>());
//        List<ChannelSum> list = page.getRecords();
//        System.out.println( list);
    }

    @Test
    public void test4(){
//       42346 42343  42344 42345 42342 42348 42340 42341
        boolean a = RSSmsUtils.sendsms("18872567851", "", "42347");
        System.out.println(a);
    }

    @Test
    public void test5(){
        authenticationService.YysAuth(7251L);
    }

}