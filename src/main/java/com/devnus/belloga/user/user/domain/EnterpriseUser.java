package com.devnus.belloga.user.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise")
@DiscriminatorValue("enterprise")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class EnterpriseUser extends User{
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "organization")
    private String organization;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
}
