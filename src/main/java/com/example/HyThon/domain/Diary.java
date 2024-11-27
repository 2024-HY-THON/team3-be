package com.example.HyThon.domain;

import com.example.HyThon.domain.common.BaseEntity;
import com.example.HyThon.domain.enums.EmotionType;
import com.example.HyThon.domain.enums.SubjectType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member writer;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Transmission> tranmissionList = new ArrayList<>();

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 400)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmotionType emotionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectType subjectType;
}
