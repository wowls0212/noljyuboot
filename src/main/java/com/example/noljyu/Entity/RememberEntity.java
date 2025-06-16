package com.example.noljyu.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="\"remember\"")
@Builder
public class RememberEntity {
    @Id
    @Column(name = "\"id\"")
    private String id;

    @Column(name = "\"petname\"")
    private String petname;


    @Column(name = "\"deadday\"")
    private LocalDate deadday;

    @Column(name = "\"petimg\"")
    private String petimg;
}
