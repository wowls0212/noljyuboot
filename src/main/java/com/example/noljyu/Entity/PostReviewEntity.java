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
        name="postreview",
        sequenceName = "ppreview_seq",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "\"postreview\"")
@Builder
public class PostReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "postreview")
    @Column(name = "\"postreviewnum\"")
    long postreviewnum;
    @Column(name = "\"id\"")
    String id;
    @Column(name = "\"postnum\"")
    long postnum;
    @Column(name = "\"postreview\"")
    String postreview;
    @Column(name = "\"postgroups\"")
    int postgroups;
    @Column(name = "\"poststep\"")
    int poststep;
    @Column(name = "\"postindent\"")
    int postindent;
    @Column(name = "\"posttype\"")
    String posttype;
}
