package com.rongke.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.rongke.model.Channel;
import com.rongke.service.ChannelService;
import com.rongke.utils.ConstantFactory;
import com.rongke.web.ans.config.SysReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    private ChannelService channelService;
    private final String accessUrl= ConstantFactory.getConfig().getAccessUrl();//用户签名
    @RequestMapping("/0/{id}")
    public String page(@PathVariable(value = "id") String id){
        //56de472501b194e23283df3ea1efdd07
        Channel channel = channelService.selectOne(new EntityWrapper<Channel>().eq("encryption", id.trim()));
        if(channel!=null){
            return "redirect:"+ accessUrl+"?channelName="+id.trim();
        }
        return "redirect:http://www.baidu.com";
    }


}
