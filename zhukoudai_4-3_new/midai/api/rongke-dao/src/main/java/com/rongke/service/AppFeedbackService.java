package com.rongke.service;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.AppFeedback;import com.rongke.utils.ans.PageUtils;import java.util.List;import java.util.Map;/** * @AppFeedbackService * @用户反馈Service * @version : Ver 1.0 */public interface AppFeedbackService extends IService<AppFeedback>{//    List<AppFeedback> selectAllFeedBackList(Integer type, String phone, Integer currentPage);    Integer selectAllFeedBackTotal(Integer type, String phone);    /**     * 用户反馈列表     */    PageUtils selectAllFeedBackList(Map<String, Object> params);    /**     *@app查询聊天记录     */    List<AppFeedback> selectFeedBackByUserId(Long userId);}