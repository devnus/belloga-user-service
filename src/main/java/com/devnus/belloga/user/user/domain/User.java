package com.devnus.belloga.user.user.domain;

import com.devnus.belloga.user.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public abstract class User extends BaseTimeEntity {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "account_id")
    private String accountId;
}
