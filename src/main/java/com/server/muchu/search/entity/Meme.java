package com.server.muchu.search.entity;

import com.server.muchu.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 엔티티에는, NoArgsConstructor 가 필수적
// Builder 패턴에서, 생성자를 정의하게 되면 전체 생성자 또한 정의해야함
// '다른 생성자가 존재하지 않을 때' 전체 생성자를 자동으로 만들어주므로

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEME_TB",
        indexes = {
                @Index(name = "cluster_index", columnList = "MEME_CLUSTER")
        }
)
public class Meme extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEME_ID")
    private Long id;

    @Column(name = "MEME_FILENAME", nullable = false)
    private String fileName;

    @Column(name = "MEME_CLUSTER")
    private int clusterNum;

}
