package com.example.noljyu.DTO;

import com.example.noljyu.Entity.ReportEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportDTO {
    long postreportnum;
    int postnum;
    String postid;
    String reportreason;
    String reportid;
    String reportdetail;

    public ReportEntity postreportsave() {
        return ReportEntity.builder()
                .postreportnum(postreportnum)
                .postnum(postnum)
                .postid(postid)
                .reportreason(reportreason)
                .reportid(reportid)
                .reportdetail(reportdetail)
                .build();
    }
}
