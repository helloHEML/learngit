package com.rongke.mapper;import com.rongke.model.Dictionary;import com.baomidou.mybatisplus.mapper.BaseMapper;import java.util.List;/** * @DictionaryMapper * @Mapper * @version : Ver 1.0 */public interface DictionaryMapper extends BaseMapper<Dictionary>{    List<Dictionary>  selectByCondition(Dictionary dictionary);}