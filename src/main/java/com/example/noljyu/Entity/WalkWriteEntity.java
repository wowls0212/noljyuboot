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
        name = "wwrite",
        sequenceName = "wwrite_seq",
        allocationSize = 1,
        initialValue = 1
)
@Table(name = "\"walkwrite\"")
@Builder
public class WalkWriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "wwrite")
    @Column(name = "\"walkwritenum\"")
    long walkwritenum;
    @Column(name = "\"id\"")
    String id;
    @Column(name = "\"walkwritetitle\"")
    String walkwritetitle;
    @Column(name = "\"walkwritedetail" +
            "" +
            "\"")
    String walkwritedetail;
}
