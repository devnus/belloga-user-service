package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.common.util.SecurityUtil;
import com.devnus.belloga.user.user.domain.EnterpriseUser;
import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     * 기업사용자 회원가입시 비동기로 받는 기업 사용자의 정보를 저장한다
     */
    @Override
    @Transactional
    public boolean saveUserEnterprise(EventAccount.RegisterAccountEnterprise event) {

        userRepository.save(EnterpriseUser.builder()
                .id(SecurityUtil.encryptSHA256(event.getAccountId()))
                .accountId(event.getAccountId())
                .userRole(event.getUserRole())
                .email(event.getEmail())
                .name(event.getName())
                .organization(event.getOrganization())
                .phoneNumber(event.getPhoneNumber())
                .build());

        return true;
    }
}
