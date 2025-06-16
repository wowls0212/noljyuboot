package com.example.noljyu.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name = "post",
        sequenceName = "ppost_seq",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "\"post\"")
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post")
    @Column(name = "\"postnum\"")
    long postnum;
    @Column(name = "\"id\"")
    String id;
    @Column(name = "\"posttitle\"")
    String posttitle;
    @Column(name = "\"postdetail\"")
    String postdetail;
    @Column(name = "\"postimg\"")
    String postimg;
    @Column(name = "\"postcnt\"")
    int postcnt;
    @Column(name = "\"postdate\"")
    LocalDate postdate;
    @PrePersist
    public void prePersist() {
        this.postdate = LocalDate.now();
    }
    @Column(name = "\"posttype\"")
    String posttype;
    @Column(name = "\"good\"")
    int good;

}
