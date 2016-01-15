/**
 * @Author lukangle
 * @2015年12月3日@下午9:08:26
 */
package com.hbc.api.trade.third;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.mapper.channel.gen.ChannelAgentMapper;
import com.hbc.api.trade.bdata.mapper.channel.gen.bean.ChannelAgent;

@Component
public class ChannelAgentService {
	@Autowired
	ChannelAgentMapper channelAgentMapper;
	
	public ChannelAgent getChannelType(Integer agentId){
		ChannelAgent channelAgent = channelAgentMapper.selectByPrimaryKey(agentId+"");
		return channelAgent;
	}
}
