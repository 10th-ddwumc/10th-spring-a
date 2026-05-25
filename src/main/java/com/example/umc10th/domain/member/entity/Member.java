package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.SocialType;
import com.example.umc10th.domain.mission.entity.Location;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(nullable = false, length = 20)
    private String name;

    private String profileUrl;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String phoneNumber;
    private String address;
    private Integer point;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialUid;

    @Builder
    public Member(String name, String email, String password, String phoneNumber, String address, SocialType socialType, String socialUid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
        this.socialType = socialType;
        this.socialUid = socialUid;
    }
}