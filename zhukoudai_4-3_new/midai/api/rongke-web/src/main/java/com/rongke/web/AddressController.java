package com.rongke.web;import com.rongke.commons.JsonResp;import com.rongke.mapper.AddressMapper;import com.rongke.model.Address;import com.rongke.service.AddressService;import org.apache.log4j.Logger;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.transaction.annotation.Transactional;import org.springframework.web.bind.annotation.*;import java.math.BigInteger;import java.util.Date;import java.util.List;/** * @AddressController * @地址Controller * @version : Ver 1.0 */@RestController@RequestMapping(value="/api/address")@Transactional@CrossOriginpublic class AddressController {    private Logger log = Logger.getLogger(this.getClass());    @Autowired    private AddressService addressService;    /**     * @添加地址     * @param address     * @return 返回值JsonResp     */    @RequestMapping(value="/add", method = RequestMethod.POST)    public JsonResp addAddress(@RequestBody Address address){        log.debug("添加地址");        address.setGmtDatetime(new Date());        addressService.insert(address);        return JsonResp.ok(address);    }    /**     * @修改地址     * @param address     * @return 返回值JsonResp     */    @RequestMapping(value="/update", method = RequestMethod.POST)    public JsonResp updateAddress(@RequestBody Address address){        log.debug("修改地址");        address.setUptDatetime(new Date());        addressService.updateById(address);        return JsonResp.ok(address);    }    /**     * @根据id查找地址     * @param id     * @return 返回值JsonResp     */    @RequestMapping(value="/selectOne", method = RequestMethod.GET)    public JsonResp selectAddress(Long id){        log.debug("查找地址");        Address address = addressService.selectById(id);        return JsonResp.ok(address);    }    /**     * @根据条件查找地址     * @param address     * @return 返回值JsonResp     */    @RequestMapping(value = "/selectByCondition", method = RequestMethod.POST)    public JsonResp selectAddressByCondition(@RequestBody Address address){        log.debug("根据条件查找地址");        if(address==null)            return JsonResp.toFail("参数不能为空");        List<Address> addressList=addressService.selectByCondition(address);        return JsonResp.ok(addressList);    }}