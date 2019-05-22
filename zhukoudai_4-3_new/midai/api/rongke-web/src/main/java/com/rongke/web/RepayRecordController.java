package com.rongke.web;import com.baomidou.mybatisplus.mapper.EntityWrapper;import com.baomidou.mybatisplus.plugins.Page;import com.rongke.commons.JsonResp;import com.rongke.commons.PageDto;import com.rongke.enums.FileType;import com.rongke.model.RepayRecord;import com.rongke.model.User;import com.rongke.service.AdminService;import com.rongke.service.OrderService;import com.rongke.service.RepayRecordService;import com.rongke.utils.StringUtil;import com.rongke.utils.ans.PageUtils;import com.rongke.utils.ans.R;import org.apache.log4j.Logger;import org.apache.poi.xssf.usermodel.*;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.transaction.annotation.Transactional;import org.springframework.web.bind.annotation.*;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import java.io.File;import java.io.IOException;import java.text.SimpleDateFormat;import java.util.Date;import java.util.HashMap;import java.util.List;import java.util.Map;/** * @RepayRecordController * @还款记录Controller * @version : Ver 1.0 */@RestController@RequestMapping(value="/api/repayRecord")@Transactional@CrossOriginpublic class RepayRecordController {    private Logger log = Logger.getLogger(this.getClass());    @Autowired    private RepayRecordService repayRecordService;    @Autowired    private AdminService adminService;    @Autowired    private OrderService orderService;    /**     * @添加还款记录     */    @RequestMapping(value="/add", method = RequestMethod.POST)    public JsonResp addRepayRecord(@RequestBody RepayRecord repayRecord){        log.debug("添加还款记录");        repayRecord.setGmtDatetime(new Date());        repayRecordService.insert(repayRecord);        return JsonResp.ok(repayRecord);    }    /**     * @修改还款记录     */    @RequestMapping(value="/update", method = RequestMethod.POST)    public JsonResp updateRepayRecord(@RequestBody RepayRecord repayRecord){        log.debug("修改还款记录");        repayRecord.setUptDatetime(new Date());        repayRecordService.updateById(repayRecord);        return JsonResp.ok(repayRecord);    }    /**     * @查找还款记录     * @param id     * @return 返回值JsonResp     */    @RequestMapping(value="/selectOne", method = RequestMethod.GET)    public JsonResp selectRepayRecord(Long id){        log.debug("查找还款记录");        RepayRecord repayRecord = repayRecordService.selectById(id);        return JsonResp.ok(repayRecord);    }    /**     * @根据条件查找还款记录     * @return 返回值JsonResp     */    @RequestMapping(value = "/findList", method = RequestMethod.GET)    public JsonResp findList(Page page, String time){        log.debug("根据条件查找还款记录");        Map map=new HashMap();        String time1="",time2="";        if (StringUtil.isNotEmpty(time)) {            String[] array = time.split("~");            time1 = array[0];            time2 = array[1];        }        map.put("time1",time1);        map.put("time2",time2);        map.put("startIndex",(page.getCurrent()-1)*page.getSize());        map.put("size",page.getSize());        List<RepayRecord> repayRecords=repayRecordService.selectRepayRecordList(map);        Integer size=repayRecordService.selectRepayRecordListSize(map);        page.setRecords(repayRecords);        page.setTotal(size);        return JsonResp.ok(page);    }    /**     * @根据订单id分页查询还款记录     * @return 返回值JsonResp     */    @RequestMapping(value="/findListByOrderId", method = RequestMethod.GET)    public JsonResp findListByOrderId(String orderId,Integer current,Integer pageSize){        log.debug("根据订单id分页查询还款记录");        Integer pageNo = (current-1)*pageSize;        List<RepayRecord> list = repayRecordService.findListByOrderId(orderId,pageNo,pageSize);        EntityWrapper<RepayRecord> wrapper = new EntityWrapper<>();        wrapper.eq("order_id",orderId);        Integer count = repayRecordService.selectList(wrapper).size();        PageDto pageDto = new PageDto(current,pageSize,list,count);        return JsonResp.ok(pageDto);    }    /**     * @导出excel     * @return 返回值JsonResp     */    @RequestMapping(value="/makeExcle", method = RequestMethod.GET)    public JsonResp makeExcle(String time, HttpServletRequest request, HttpServletResponse response) throws IOException {        log.debug("导出excel");        if(time==null || "".equals(time)){            return JsonResp.fa("请选择要导出的数据时间范围");        }        Map map=new HashMap();        String time1="",time2="";        if (StringUtil.isNotEmpty(time)) {            String[] array = time.split("~");            time1 = array[0];            time2 = array[1];        }        map.put("time1",time1);        map.put("time2",time2);        List<RepayRecord> repayRecords=repayRecordService.selectRepayRecordList(map);        if (repayRecords.isEmpty()) {            return JsonResp.fa("选择导出的时间范围数据为空");        }        XSSFWorkbook excelbook = new XSSFWorkbook(); //创建workBook        XSSFSheet excelSheet = excelbook.createSheet();//创建sheet表        XSSFRow excelRow = excelSheet.createRow(0);//创建第一行        XSSFCellStyle headerStyle = excelbook.createCellStyle();//设置 居中        headerStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);        //接下来是创建 列标题 ,cell的起始值是 0,可创建n个列标题        String[] Title = { "用户姓名", "用户手机","还款金额", "还款天数", "逾期费","还款类型","还款方式","还款时间"};        for(int a=0;a<Title.length;a++){            XSSFCell cell = excelRow.createCell(a);            cell.setCellStyle(headerStyle);//居中            cell.setCellValue(Title[a]);        }        //接下来遍历List,并写入EXCEL中        for(int i = 0, y=repayRecords.size();i<y; i++) {            //创建行,行号应从1开始,因为表头行(列标题)占据了第0行            excelRow = excelSheet.createRow(i + 1);            //将该行每一列的数据写入,可写n列            RepayRecord repayRecord = repayRecords.get(i);// List 的起始值是0            User user=repayRecord.getUser();            excelRow.createCell(0).setCellValue(user.getRealName());            excelRow.createCell(1).setCellValue(user.getPhone());            excelRow.createCell(2).setCellValue(repayRecord.getMoney().toString());            excelRow.createCell(3).setCellValue(String.valueOf(repayRecord.getDays()));            excelRow.createCell(4).setCellValue(repayRecord.getOvertimeMoney().toString());            int type=repayRecord.getType();            String str="";            if(type==1){                str="正常日租金";            }else if(type==2){                str="逾期还款";            }else if(type==3){                str="赎金";            }            excelRow.createCell(5).setCellValue(str);            Integer payType=repayRecord.getPayType();            String payTpyeTitle=null;            if(payType==null){                payTpyeTitle="未知";            }else if(payType==1){                payTpyeTitle="银联还款";            }else if(payType==2){                payTpyeTitle="支付宝还款";            }else if(payType==3){                payTpyeTitle="微信还款";            }else if(payType==4){                payTpyeTitle="线下还款";            }            excelRow.createCell(6).setCellValue(payTpyeTitle);            excelRow.createCell(7).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(repayRecord.getGmtDatetime()));        }        String fileanme = "还款明细";        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());        FileType fileType = FileType.XLS;        String path = fileType.getAbsolutePath();        String filePath = path + File.separator+fileanme+date+".xls";        log.info("filePath"+filePath);        EvaluationController.writeExcel(request,response, excelbook, filePath, "文件名");//具体导出的方法        String url = request.getRequestURL().toString().split("api")[0]+"/pic/xls/"+File.separator+fileanme+date+".xls";        return JsonResp.ok(url);    }    /**     * 订单列表     */    @RequestMapping("/oderUserRePay")    public PageUtils oderUserRePay(@RequestParam Map<String,Object> params){        adminService.findLoginUser();        return  repayRecordService.oderUserRePay(params);    }    /**     * 还款明细表     */    @RequestMapping("/repayPage")    public PageUtils repayPage(@RequestParam Map<String,Object> params){        adminService.findLoginUser();        return repayRecordService.repayPage(params);    }}