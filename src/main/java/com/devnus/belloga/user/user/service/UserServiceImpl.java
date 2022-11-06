package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.common.exception.error.InvalidAccountIdException;
import com.devnus.belloga.user.common.util.SecurityUtil;
import com.devnus.belloga.user.user.domain.AdminUser;
import com.devnus.belloga.user.user.domain.EnterpriseUser;
import com.devnus.belloga.user.user.domain.LabelerUser;
import com.devnus.belloga.user.user.domain.User;
import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.dto.ResponseUser;
import com.devnus.belloga.user.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public boolean saveEnterprise(EventAccount.RegisterEnterprise event) {

        userRepository.save(EnterpriseUser.builder()
                .id(SecurityUtil.encryptSHA256(event.getAccountId()))
                .accountId(event.getAccountId())
                .email(event.getEmail())
                .name(event.getName())
                .organization(event.getOrganization())
                .phoneNumber(event.getPhoneNumber())
                .build());

        return true;
    }

    /**
     * 관리자 회원가입시 관리자 정보 저장
     */
    @Override
    @Transactional
    public boolean saveAdmin(EventAccount.RegisterAdmin event) {

        userRepository.save(AdminUser.builder()
                .id(SecurityUtil.encryptSHA256(event.getAccountId()))
                .accountId(event.getAccountId())
                .email(event.getEmail())
                .name(event.getName())
                .phoneNumber(event.getPhoneNumber())
                .build());

        return true;
    }

    /**
     * 일반사용자 회원가입시 비동기로 받는 일반 사용자의 정보를 저장한다
     */
    @Override
    @Transactional
    public boolean saveLabeler(EventAccount.RegisterLabeler event) {

        userRepository.save(LabelerUser.builder()
                .id(SecurityUtil.encryptSHA256(event.getAccountId()))
                .accountId(event.getAccountId())
                .email(event.getEmail())
                .name(event.getName())
                .phoneNumber(event.getPhoneNumber())
                .birthYear(event.getBirthYear())
                .build());

        return true;
    }

    @Override
    public ResponseUser.EnterpriseInfo getEnterpriseInfo (String accountId){

        //사용자 정보 확인
        Optional<User> user = userRepository.findByAccountId(accountId);
        EnterpriseUser enterpriseUser = (EnterpriseUser) user.orElseThrow(() -> new InvalidAccountIdException());

        return ResponseUser.EnterpriseInfo.builder()
                .email(enterpriseUser.getEmail())
                .name(enterpriseUser.getName())
                .organization(enterpriseUser.getOrganization())
                .phoneNumber(enterpriseUser.getPhoneNumber())
                .build();
    }

    @Override
    public ResponseUser.LabelerInfo getLabelerInfo (String accountId){

        //사용자 정보 확인
        Optional<User> user = userRepository.findByAccountId(accountId);
        LabelerUser labelerUser = (LabelerUser) user.orElseThrow(() -> new InvalidAccountIdException());

        return ResponseUser.LabelerInfo.builder()
                .email(labelerUser.getEmail())
                .name(labelerUser.getName())
                .phoneNumber(labelerUser.getPhoneNumber())
                .birthYear(labelerUser.getBirthYear())
                .build();
    }
}
