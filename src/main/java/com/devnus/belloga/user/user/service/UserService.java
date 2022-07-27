package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.dto.ResponseUser;

public interface UserService {
    boolean saveUserEnterprise(EventAccount.RegisterAccountEnterprise event);
    ResponseUser.UserInfo getUserInfoByAccountId(String AccountId);
}
