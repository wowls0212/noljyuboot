package com.example.noljyu.Controller;

import com.example.noljyu.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value="error", required=false) String error,
                            @RequestParam(value="logout", required=false) String logout,
                            Model model) {
        if(error != null) {
            model.addAttribute("msg", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        if(logout != null) {
            model.addAttribute("msg", "로그아웃 되었습니다.");
        }
        return "login/login";
    }

    @GetMapping("/passwordfind")
    public String passwordFindPage() {
        return "login/passwordFind";
    }


    @GetMapping("/passwordReset")
    public String passwordResetPage(@RequestParam String id, Model model) {
        model.addAttribute("id", id);
        return "login/passwordReset";
    }

    @PostMapping("/passwordfindProcess")
    public String passwordFindProcess(@RequestParam String id,
                                      @RequestParam String petName,
                                      RedirectAttributes redirectAttributes) {
        System.out.println("비밀번호 찾기 시도: id=" + id + ", petName=" + petName);

        if (userService.checkUserPetName(id, petName)) {
            redirectAttributes.addAttribute("id", id);
            return "redirect:/passwordReset";
        } else {
            redirectAttributes.addFlashAttribute("msg", "입력하신 정보와 일치하는 사용자가 없습니다.");
            return "redirect:/passwordfind";
        }
    }


    @PostMapping("/passwordresetProcess")
    public String passwordResetProcess(@RequestParam String id,
                                       @RequestParam String newPassword,
                                       Model model) {
        if(newPassword == null || newPassword.trim().isEmpty()) {
            model.addAttribute("msg", "새 비밀번호를 입력해주세요.");
            model.addAttribute("id", id);
            return "login/passwordReset";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPw = encoder.encode(newPassword);
        userService.updatePassword(id, encodedPw);
        return "redirect:/login";
    }
}
