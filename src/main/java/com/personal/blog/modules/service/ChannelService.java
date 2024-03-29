package com.personal.blog.modules.service;

import com.personal.blog.modules.entity.Channel;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 *
 * @author weizp
 *
 */
public interface ChannelService {
	List<Channel> findAll(int status);
	Map<Integer, Channel> findMapByIds(Collection<Integer> ids);
	Channel getById(int id);
	void update(Channel channel);
	void updateWeight(int id, int weighted);
	void delete(int id);
	long count();
}
