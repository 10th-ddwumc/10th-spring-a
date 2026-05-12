package com.example.umc10th_a.domain.member.entity;

import com.example.umc10th_a.domain.member.enums.Address;
import com.example.umc10th_a.domain.member.enums.Gender;
import com.example.umc10th_a.domain.member.enums.SocialProvider;
import com.example.umc10th_a.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Builder.Default
    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Builder.Default
    @Column(name = "address", nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address = Address.NONE;

    @Column(name = "detail_address", nullable = false)
    private String detailAddress;

    @Column(name = "social_uid", nullable = false)
    private String socialUid;

    @Builder.Default
    @Column(name = "social_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider = SocialProvider.NONE;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Builder.Default
    @Column(name = "point", nullable = false)
    private Integer point = 0;
}