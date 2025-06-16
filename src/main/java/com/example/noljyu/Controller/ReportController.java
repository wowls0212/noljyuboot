package com.example.noljyu.Controller;

import com.example.noljyu.DTO.ReportDTO;
import com.example.noljyu.Entity.ReportEntity;
import com.example.noljyu.Service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    ReportService reportService;
    @PostMapping(value = "postreportsave")
    public String postsave(ReportDTO dto, HttpServletResponse response) throws IOException {
        ReportEntity pentity = dto.postreportsave();
        reportService.rsave(pentity);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<script type='text/javascript'>");
        out.print("window.opener.location.reload();"); // 부모 창 새로고침
        out.print("window.close();"); // 현재 창 닫기
        out.print("</script>");
        out.flush();
        return null;
    }
    @GetMapping(value = "postreportout")
    public String postreportout(Model model){
        List<ReportEntity> list = reportService.rout();
        model.addAttribute("list",list);
        return "report/postreportout";
    }


}
