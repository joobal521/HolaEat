package com.spring.holaeat.controller;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes("authority")
@Controller
public class AdminController {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }



    @PostMapping("gainpower")
    public String gainPower(@RequestParam("adminid") String id, @RequestParam("adminpwd") String pwd, HttpSession session) {
        List<Admin> admin = adminRepository.findAdminByIdAndPassword(id, pwd);

        if (admin.isEmpty()) {
            System.out.println("관리자 로그인 실패");
            return "gainpower"; // 실패 시 해당 뷰로 반환

        } else {
            session.setAttribute("authority", "POWERED");
            System.out.println("관리자 로그인");
            return "admin"; // 성공 시 admin 페이지로 리다이렉트
        }
    }


}
