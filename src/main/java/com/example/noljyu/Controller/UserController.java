package com.example.noljyu.Controller;

import com.example.noljyu.DTO.UserDTO;
import com.example.noljyu.DTO.UserPageDTO;
import com.example.noljyu.Entity.UserEntity;
import com.example.noljyu.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor

public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final String path = "C:\\MBC12AI\\springboot\\noljyu\\src\\main\\resources\\static\\image";

    @GetMapping("/userinput")
    public String userInputPage() {
        return "user/userinput";
    }

    @PostMapping("/idcheck2")
    @ResponseBody
    public String idCheck(@RequestParam String id) {
        int count = userService.idcount(id.trim());
        System.out.println(">>> [ID 중복확인 요청] 입력값: " + id + " → count: " + count);
        return count > 0 ? "no" : "ok";
    }

    @PostMapping("/nicknamecheck")
    @ResponseBody
    public String nicknameCheck(@RequestParam String nickname) {
        int count = userService.nicknamecount(nickname.trim());
        System.out.println(">>> [닉네임 중복확인 요청] 입력값: " + nickname + " → count: " + count);
        return count > 0 ? "no" : "ok";
    }



    @PostMapping("/usersave")
    public String userSave(@ModelAttribute UserDTO dto,
                           MultipartHttpServletRequest request,
                           HttpSession session,
                           Model model) throws IOException {

        if ("admin".equals(dto.getAdmin())) {
            String inputAdminPw = request.getParameter("adminPass");
            String encodedAdminPw = userService.getAdminPassword();

            if (!passwordEncoder.matches(inputAdminPw, encodedAdminPw)) {
                model.addAttribute("msg", "관리자용 비밀번호가 올바르지 않습니다.");
                return "user/userinput";
            }
        }

        dto.setPw(passwordEncoder.encode(dto.getPw()));

        MultipartFile mf = request.getFile("uploadfile");
        String photoName;
        if (mf != null && !mf.isEmpty()) {
            photoName = UUID.randomUUID() + "-" + mf.getOriginalFilename();
            mf.transferTo(new File(path + "//" + photoName));
        } else {
            photoName = "default.jpg";
        }
        dto.setPhoto(photoName);

        UserEntity uentity = dto.toEntity();
        userService.insertq(uentity);

        session.setAttribute("msg", "환영합니다! 회원가입이 완료되었습니다.");
        return "redirect:/main";
    }




    @GetMapping("/userout")
    public String userOut(Model model) {
        List<UserEntity> list = userService.allout();

        int total = list.size();
        UserPageDTO paging = new UserPageDTO(1, total, 10);

        model.addAttribute("list", list);
        model.addAttribute("paging", paging);
        return "user/userout";
    }


    @PostMapping("/admindelete")
    public String adminDelete(@RequestParam String id, HttpSession session) {
        userService.delete2(id);
        session.setAttribute("msg", "회원 삭제가 완료되었습니다.");
        return "redirect:/userout";
    }

    @GetMapping("/usersearch")
    public String userSearchPage() {

        return "user/scearch";
    }


    @GetMapping("/scearch2")
    public String userSearchResult(HttpServletRequest request, Model model, UserPageDTO dto) {
        String cate = request.getParameter("cate");
        String keyword = request.getParameter("scearch");
        String nowPage = request.getParameter("nowPage");
        String cntPerPage = request.getParameter("cntPerPage");

        int total = userService.usersearchtotal();
        nowPage = (nowPage == null) ? "1" : nowPage;
        cntPerPage = (cntPerPage == null) ? "10" : cntPerPage;

        dto = new UserPageDTO(Integer.parseInt(nowPage), total, Integer.parseInt(cntPerPage));
        List<UserEntity> list = userService.searcha(cate, keyword);

        model.addAttribute("list", list);
        model.addAttribute("paging", dto);
        return "user/scearchout";
    }

    @GetMapping("/mypage")
    public String myPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();

        model.addAttribute("myinfo", userService.getuser(loginId));
        model.addAttribute("myPosts", userService.getmyposts(loginId));
        model.addAttribute("goodtotal", userService.getgoodtotal(loginId));
        return "user/mypageout";
    }


    @GetMapping("/modify1")
    public String modifyForm(HttpSession session, Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null || !"ok".equals(session.getAttribute("pwcheck"))) {
            return "redirect:/pwcheck?type=modify";
        }

        String id = userDetails.getUsername();
        model.addAttribute("dto", userService.modify1(id));

        session.removeAttribute("pwcheck");
        return "user/modify1";
    }


    @PostMapping("/modifysave")
    public String modifySave(MultipartHttpServletRequest request, HttpSession session) throws IOException {
        String id = request.getParameter("id");
        String pwInput = request.getParameter("pw");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String nickname = request.getParameter("nickname");
        String myanimal = request.getParameter("myanimal");
        String petName = request.getParameter("petName");

        UserDTO dto = userService.getuser(id);
        String pw = (pwInput != null && !pwInput.trim().isEmpty()) ? passwordEncoder.encode(pwInput) : dto.getPw();

        MultipartFile mf = request.getFile("uploadfile");
        String photo = (mf != null && !mf.isEmpty()) ? UUID.randomUUID() + "-" + mf.getOriginalFilename() : dto.getPhoto();
        if (mf != null && !mf.isEmpty()) mf.transferTo(new File(path + "/" + photo));

        petName = (petName == null || petName.trim().isEmpty()) ? dto.getPetname() : petName;

        userService.modifyWithPhoto(id, pw, name, address, phone, nickname, myanimal, petName, photo);
        session.setAttribute("msg", "회원정보가 수정되었습니다.");
        return "redirect:/main";
    }

    @GetMapping("/delete1")
    public String deletePage(HttpSession session, Model model,
                             @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null || !"ok".equals(session.getAttribute("pwcheck"))) {
            return "redirect:/pwcheck?type=delete";
        }

        String id = userDetails.getUsername();
        model.addAttribute("dto", userService.delete1(id));

        session.removeAttribute("pwcheck");
        return "user/deleteview";
    }


    @PostMapping("/delete2")
    public String deleteConfirm(@AuthenticationPrincipal UserDetails userDetails,
                                HttpServletRequest request, HttpSession session) {
        if (userDetails == null) return "redirect:/login";

        String id = userDetails.getUsername();
        UserDTO dto = userService.getuser(id);

        if (!"default.jpg".equals(dto.getPhoto())) {
            File file = new File(path + "/" + dto.getPhoto());
            if (file.exists()) file.delete();
        }

        userService.delete2(id);
        session.invalidate();
        request.getSession().setAttribute("msg", "회원 탈퇴 완료되었습니다.");
        return "redirect:/main";
    }


    @GetMapping("/pwcheck")
    public String pwCheckPage(@RequestParam String type, Model model) {
        model.addAttribute("type", type);
        return "user/pwcheck";
    }

    @PostMapping("/pwcheckdo")
    public String pwCheckDo(@RequestParam String pw,
                            @RequestParam String type,
                            HttpSession session,
                            Model model,
                            @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) return "redirect:/login";
        String id = userDetails.getUsername();

        UserDTO dto = userService.getuser(id);
        if (dto != null && passwordEncoder.matches(pw, dto.getPw())) {
            session.setAttribute("pwcheck", "ok");
            if ("modify".equals(type)) return "redirect:/modify1";
            else if ("delete".equals(type)) return "redirect:/delete1";
        }

        model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
        model.addAttribute("type", type);
        return "user/pwcheck";
    }
}