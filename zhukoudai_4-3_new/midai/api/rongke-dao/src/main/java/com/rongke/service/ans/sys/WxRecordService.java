package com.rongke.service.ans.sys;import com.baomidou.mybatisplus.service.IService;import com.rongke.model.ans.WxRecordEntity;import com.rongke.utils.ans.PageUtils;import java.util.Map;/** * 微信 消费记录表 * * @author Ans * @email 867917319@qq.com * @date 2018-08-22 15:48:29 */public interface WxRecordService extends IService<WxRecordEntity> {    PageUtils queryPage(Map<String, Object> params);}