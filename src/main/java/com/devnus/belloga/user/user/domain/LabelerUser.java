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
@Table(name = "labeler")
@DiscriminatorValue("labeler")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class LabelerUser extends User {
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_year")
    private String birthYear;
}
