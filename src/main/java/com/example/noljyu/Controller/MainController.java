package com.example.noljyu.Controller;

import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping({"/", "/main"})
    public String main(Model model) {
        PostEntity hdto = postService.hbest();
        PostEntity fdto = postService.fbest();
        PostEntity cdto = postService.cbest();

        model.addAttribute("hdto", hdto);
        model.addAttribute("fdto", fdto);
        model.addAttribute("cdto", cdto);

        return "main";
    }
}
