package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private String conditional;

    @OneToMany(mappedBy = "mission")
    private List<MemberMission> memberMissionList = new ArrayList<>();
}