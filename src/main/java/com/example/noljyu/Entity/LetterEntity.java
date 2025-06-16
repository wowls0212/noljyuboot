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
@Table(name="\"letter\"")
@Builder
@SequenceGenerator(
        name = "remember",
        sequenceName = "letternum_seq",
        allocationSize = 1,
        initialValue = 1
)
public class LetterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "remember")
    @Column(name = "\"letternum\"")
    long letternum;

    @Column(name = "\"id\"")
    String id;

    @Column(name = "\"lettertitle\"")
    String lettertitle;


    @Column(name = "\"letterdetail\"")
    String letterdetail;

    @Column(name = "\"writeday\"")
    LocalDate writeday;

    @PrePersist
    public void prePersist() {
        this.writeday = LocalDate.now();
    }


}
