package com.devnus.belloga.user.user.domain;

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
public abstract class User {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "account_id")
    private String accountId;
}
