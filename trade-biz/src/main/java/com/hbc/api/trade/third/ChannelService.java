/**
 * @Author lukangle
 * @2015年11月11日@上午10:44:25
 */
package com.hbc.api.trade.third;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.hbc.api.trade.bdata.common.cache.CacheFinal;
import com.hbc.api.trade.bdata.mapper.channel.gen.ChannelAgentMapper;
import com.hbc.api.trade.bdata.mapper.channel.gen.bean.ChannelAgent;
import com.hbc.api.trade.order.enums.deliver.PkChannelType;

@Component
public class ChannelService {
	@Autowired
	ChannelAgentMapper channelAgentMapper;
	private final static Logger log = LoggerFactory.getLogger(ChannelService.class);
	public ChannelAgent getAgentByid(String agentId) {
		return channelAgentMapper.selectByPrimaryKey(agentId);
	}
	@Cacheable(value=CacheFinal.channelCache,key="#root.targetClass+#root.methodName"+"+#agentId")
	public PkChannelType getPkType(String agentId) {
		ChannelAgent channelAgent = channelAgentMapper.selectByPrimaryKey(agentId);
		if(channelAgent==null){
			log.error(agentId+" has no channel id");
		}
		return PkChannelType.getType(channelAgent.getChannelServiceType());
	}
}
