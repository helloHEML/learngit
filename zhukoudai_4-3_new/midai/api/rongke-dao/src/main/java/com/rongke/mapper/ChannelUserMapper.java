package com.rongke.mapper;import com.baomidou.mybatisplus.mapper.BaseMapper;import com.baomidou.mybatisplus.mapper.Wrapper;import com.rongke.model.AppFeedback;import com.rongke.model.Channel;import com.rongke.model.ChannelUser;import org.apache.ibatis.annotations.Param;import org.apache.ibatis.session.RowBounds;import java.util.List;import java.util.Map;/** * 渠道负责账号 */public interface ChannelUserMapper extends BaseMapper<ChannelUser> {    Map<String, Object> getFkCount(@Param("ew") Wrapper<ChannelUser> var2);}