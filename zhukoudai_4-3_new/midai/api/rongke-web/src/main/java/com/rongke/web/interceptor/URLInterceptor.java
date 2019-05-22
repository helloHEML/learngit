package com.rongke.web.interceptor;

import com.rongke.rediscluster.CacheUtil;
import com.rongke.rediscluster.SpringContextUtils;
import com.rongke.service.UserPhoneRecordService;
import com.rongke.service.UserTaobaoService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class URLInterceptor extends HandlerInterceptorAdapter {

    private List<String> excludedUrls;

    public List<String> getExcludedUrls() {
        return excludedUrls;
    }

    public void setExcludedUrls(List<String> excludedUrls) {
        this.excludedUrls = excludedUrls;
    }

    /**
     *
     * 在业务处理器处理请求之前被调用 如果返回false
     * 从当前的拦截器往回执行所有拦截器的afterCompletion(),
     * 再退出拦截器链, 如果返回true 执行下一个拦截器,
     * 直到所有的拦截器都执行完毕 再执行被拦截的Controller
     * 然后进入拦截器链,
     * 从最后一个拦截器往回执行所有的postHandle()
     * 接着再从最后一个拦截器往回执行所有的afterCompletion()
     *
     * @param  request
     *
     * @param  response
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("x-client-token");
        CacheUtil redisClusterCache = (CacheUtil) SpringContextUtils.getBean(CacheUtil.class);
        if (redisClusterCache.hasKey(token)) {
            resetTbYys();
        }
        return true;
    }

    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    /**
     *
     * 在DispatcherServlet完全处理完请求后被调用
     * 当有拦截器抛出异常时,
     * 会从当前拦截器往回执行所有的拦截器的afterCompletion()
     *
     * @param request
     *
     * @param response
     *
     * @param handler
     *
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

    }

    public void resetTbYys(){
        UserTaobaoService constantService = (UserTaobaoService) SpringContextUtils.getBean(UserTaobaoService.class);
        UserPhoneRecordService userPhoneRecordService = (UserPhoneRecordService) SpringContextUtils.getBean(UserPhoneRecordService.class);
        constantService.resetTaobao();
        userPhoneRecordService.resetYys();
    }

}
