package com.devnus.belloga.user.user.service;

import com.devnus.belloga.user.user.dto.EventAccount;
import com.devnus.belloga.user.user.dto.ResponseUser;

public interface UserService {
    boolean saveEnterprise(EventAccount.RegisterEnterprise event);
    boolean saveLabeler(EventAccount.RegisterLabeler event);
    boolean saveAdmin(EventAccount.RegisterAdmin event);

    ResponseUser.EnterpriseInfo getEnterpriseInfo (String accountId);
    ResponseUser.LabelerInfo getLabelerInfo (String accountId);
}
