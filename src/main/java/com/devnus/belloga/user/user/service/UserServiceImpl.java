package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.common.exception.error.InvalidAccountIdException;
import com.devnus.belloga.user.common.util.SecurityUtil;
import com.devnus.belloga.user.user.domain.EnterpriseUser;
import com.devnus.belloga.user.user.domain.User;
import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.dto.ResponseUser;
import com.devnus.belloga.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    /**
     * AccountId를 통해 유저 정보를 가져온다
     */
    @Override
    public ResponseUser.UserInfo getUserInfoByAccountId(String accountId) {
        Optional<User> optionalUser = userRepository.findByAccountId(accountId);
        User user = (User) optionalUser.orElseThrow(() -> new InvalidAccountIdException("Account Id 가 유효하지 않습니다"));

        return ResponseUser.UserInfo.builder()
                .userId(user.getId())
                .userRole(user.getUserRole())
                .build();
    }
}