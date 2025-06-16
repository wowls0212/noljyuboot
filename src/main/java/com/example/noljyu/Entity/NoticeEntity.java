package com.example.noljyu.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name="notice",
        sequenceName = "notice_seq",
        allocationSize = 1,
        initialValue = 1
)
@Table(name = "\"notice\"")
@Builder
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notice")
    @Column(name="\"nnum\"")
    long nnum;
    @Column(name="\"ntitle\"")
    String ntitle;
    @Column(name="\"ndetail\"")
    String ndetail;
    @Column(name="\"ncnt\"")
    int ncnt;
}
