package com.rongke.web;

import com.rongke.commons.JsonResp;
import com.rongke.model.DecisionTableExplain;
import com.rongke.service.DecisionTableExplainService;
import com.rongke.utils.ans.PageUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 决策字段翻译表
 */
@RestController
@RequestMapping(value="api/decisionTableExplain")
@Transactional
@CrossOrigin
public class DecisionTableExplainController {

    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private DecisionTableExplainService decisionTableExplainService;

    @RequestMapping(value="/add",method = RequestMethod.POST)
    public JsonResp addDecisionTableExplain(@RequestBody DecisionTableExplain decisionTableExplain){
        log.debug("添加");
        decisionTableExplainService.insert(decisionTableExplain);
        return JsonResp.ok(decisionTableExplain);
    }

    @RequestMapping(value="/info/{id}",method = RequestMethod.GET)
    public JsonResp thisInfo(@PathVariable("id") String id){
        log.debug("修改的信息");
        DecisionTableExplain decisionTableExplain = decisionTableExplainService.selectById(id);
        return JsonResp.ok(decisionTableExplain);
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    public JsonResp updateDecisionTableExplain(@RequestBody DecisionTableExplain decisionTableExplain){
        log.debug("修改");
        decisionTableExplainService.updateById(decisionTableExplain);
        return JsonResp.ok(decisionTableExplain);
    }

    @RequestMapping(value="/deleteById",method = RequestMethod.GET)
    public JsonResp deleteDecisionTableExplain(Long id){
        log.debug("删除");
        decisionTableExplainService.deleteById(id);
        return JsonResp.ok();
    }

    @RequestMapping(value="/getPages",method = RequestMethod.GET)
    public PageUtils getPages(@RequestParam Map<String,Object> params){
        log.debug("分页显示");
        return decisionTableExplainService.getPages(params);
    }
}
