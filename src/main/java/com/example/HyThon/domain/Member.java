package com.example.HyThon.domain;

import com.example.HyThon.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String userName;

    @Column(nullable = false, length = 100)
    private String password;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Diary> writingDiaryList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Transmission> transmissionList = new ArrayList<>();
}