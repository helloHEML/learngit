package com.rongke.web.config;


import com.rongke.rediscluster.CacheUtil;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

public class ConfigListener implements ServletContextAware {

   // private CacheUtil redisClusterCache;

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("********************");
      /*  if (redisClusterCache.get("makeCurrent")==null ||  "".equals(redisClusterCache.get("makeCurrent")) ){

            redisClusterCache.set("makeCurrent",1,-1);
            System.out.println("**************************************makeCurrent:"+redisClusterCache.get("makeCurrent"));
        }
        if (redisClusterCache.get("current")==null ||  "".equals(redisClusterCache.get("current")) ){

            redisClusterCache.set("current",1,-1);
            System.out.println("**************************************makeCurrent:"+redisClusterCache.get("makeCurrent"));
        }*/

    }









}