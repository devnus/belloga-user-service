package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.user.dto.EventAccount;

public interface UserService {
    boolean saveUserEnterprise(EventAccount.RegisterAccountEnterprise event);
}
