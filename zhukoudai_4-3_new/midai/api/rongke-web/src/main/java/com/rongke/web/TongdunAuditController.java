package com.rongke.web;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.rongke.commons.JsonResp;import com.rongke.model.Evaluation;import com.rongke.model.TongdunAudit;import com.rongke.model.User;import com.rongke.service.EvaluationService;import com.rongke.service.TongdunAuditService;import com.rongke.service.UserService;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.transaction.annotation.Transactional;import org.springframework.web.bind.annotation.*;/** * @TongdunAuditController * @同盾贷前审核结果Controller * @version : Ver 1.0 */@RestController@RequestMapping(value="/api/tongdunAudit")@Transactional@CrossOriginpublic class TongdunAuditController {    private Logger log = Logger.getLogger(this.getClass());    @Autowired    private TongdunAuditService tongdunAuditService;    @Autowired    private EvaluationService evaluationService;    @Autowired    private UserService userService;    /**     * @添加同盾贷前审核结果     * @param tongdunAudit     * @return 返回值JsonResp     */    @RequestMapping(value="/add", method = RequestMethod.POST)    public JsonResp addTongdunAudit(@RequestBody TongdunAudit tongdunAudit){        log.debug("添加同盾贷前审核结果");        tongdunAuditService.insert(tongdunAudit);        return JsonResp.ok(tongdunAudit);    }    /**     * @修改同盾贷前审核结果     * @param tongdunAudit     * @return 返回值JsonResp     */    @RequestMapping(value="/update", method = RequestMethod.POST)    public JsonResp updateTongdunAudit(@RequestBody TongdunAudit tongdunAudit){        log.debug("修改同盾贷前审核结果");        tongdunAuditService.updateById(tongdunAudit);        return JsonResp.ok(tongdunAudit);    }    /**     * @根据id查找同盾贷前审核结果     * @param id     * @return 返回值JsonResp     */    @RequestMapping(value="/selectOne", method = RequestMethod.GET)    public JsonResp selectTongdunAudit(Long id){        log.debug("查找同盾贷前审核结果");        TongdunAudit tongdunAudit = tongdunAuditService.selectById(id);        return JsonResp.ok(tongdunAudit);    }    /**     * @根据评估报告id查找同盾贷前审核结果     * @param id     * @return 返回值JsonResp     */    @RequestMapping(value="/selectTongdunAuditById", method = RequestMethod.GET)    public JsonResp selectTongdunAuditById(Long id){        log.debug("查找同盾贷前审核结果");        EntityWrapper<TongdunAudit> wrapper = new EntityWrapper<>();        wrapper.eq("evaluation_id",id);        TongdunAudit tongdunAudit = tongdunAuditService.selectOne(wrapper);        if(tongdunAudit==null){            Evaluation evaluation=evaluationService.selectById(id);            User user=userService.selectById(evaluation.getUserId());            String name=evaluation.getName();            String appType=null;            if(name.contains("iPhone")){                appType="ios";            }else {                appType="and";            }//            evaluationService.beforeAdd(user,appType,evaluation);        }        return JsonResp.ok(tongdunAudit);    }}