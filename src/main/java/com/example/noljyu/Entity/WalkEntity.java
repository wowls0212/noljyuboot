package com.example.noljyu.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name ="\"walk\"")
@Builder
@SequenceGenerator(
        name = "walk",
        sequenceName = "walk_seq",
        allocationSize = 1,
        initialValue = 1
)
public class WalkEntity {
    @Id
    @Column(name = "\"walknum\"")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "walk")
    long walknum;
    @Column(name = "\"id\"", nullable = false)
    @NotNull(message = "id는 필수값입니다.")
    String id;
    @Column(name = "\"walktitle\"")
    String walktitle;
    @Column(name = "\"walkdetail\"")
    String walkdetail;
    @Column(name = "\"walkimg\"")
    String walkimg;
    @Column(name = "\"walkcnt\"")
    int walkcnt;

    public int getWalkcnt() {
        return walkcnt;
    }

    public void setWalkcnt(int walkcnt) {
        this.walkcnt = walkcnt;
    }
}
