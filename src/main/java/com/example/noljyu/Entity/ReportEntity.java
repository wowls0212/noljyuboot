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
        name="postreport",
        sequenceName = "report_seq",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "\"postreport\"")
@Builder
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "postreport")
    @Column(name = "\"postreportnum\"")
    long postreportnum;
    @Column(name = "\"postnum\"")
    int postnum;
    @Column(name = "\"postid\"")
    String postid;
    @Column(name = "\"reportreason\"")
    String reportreason;
    @Column(name = "\"reportid\"")
    String reportid;
    @Column(name = "\"reportdetail\"")
    String reportdetail;

}
