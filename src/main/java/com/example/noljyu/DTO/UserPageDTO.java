package com.example.noljyu.DTO;

import lombok.Data;

@Data
public class UserPageDTO {

    private Integer nowPage;
    private Integer startPage;
    private Integer endPage;
    private Integer total;
    private Integer cntPerPage;
    private Integer lastPage;
    private Integer start;
    private Integer end;
    private final int cntPage = 10;

    public UserPageDTO() {
        this.nowPage = 1;
        this.cntPerPage = 10;
    }

    public UserPageDTO(Integer nowPage, Integer total, Integer cntPerPage) {
        this.nowPage = (nowPage == null) ? 1 : nowPage;
        this.cntPerPage = (cntPerPage == null) ? 10 : cntPerPage;
        this.total = (total == null) ? 0 : total;

        calcLastPage(this.total, this.cntPerPage);
        calcStartEndPage(this.nowPage, cntPage);
        calcStartEnd(this.nowPage, this.cntPerPage);
    }

    public void calcLastPage(int total, int cntPerPage) {
        this.lastPage = (int) Math.ceil((double) total / cntPerPage);
    }

    public void calcStartEndPage(int nowPage, int cntPage) {
        this.endPage = ((int) Math.ceil((double) nowPage / cntPage)) * cntPage;
        if (lastPage < endPage) endPage = lastPage;
        this.startPage = endPage - cntPage + 1;
        if (startPage < 1) startPage = 1;
    }

    public void calcStartEnd(int nowPage, int cntPerPage) {
        this.end = nowPage * cntPerPage;
        this.start = end - cntPerPage + 1;
    }
}