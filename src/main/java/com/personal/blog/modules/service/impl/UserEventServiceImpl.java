package com.personal.blog.modules.service.impl;

import com.personal.blog.base.lang.Consts;
import com.personal.blog.modules.repository.UserRepository;
import com.personal.blog.modules.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 * 用户事件操作, 用于统计用户信息
 * @author weizp
 */
@Service
@Transactional
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void identityPost(Long userId, boolean plus) {
        userRepository.updatePosts(userId, (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Long userId, boolean plus) {
        userRepository.updateComments(Collections.singleton(userId), (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Set<Long> userIds, boolean plus) {
        userRepository.updateComments(userIds, (plus) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

}
