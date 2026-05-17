package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.entity.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "store")
public class Store {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member user;

    @Column(name = "store_name", nullable = false)
    private String name;

    @Column(name = "manager_number")
    private String managerNumber;

    @Column(name = "detail_address")
    private String detailAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_mission_id")
    private MemberMission memberMission;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missions = new ArrayList<>();
}