package com.spring.holaeat.service;

import com.spring.holaeat.domain.admin.Admin;
import com.spring.holaeat.domain.admin.AdminRepository;
import com.spring.holaeat.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin getAdminById(String id){
        Admin admin=adminRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 관리자입니다.")
        );
        return admin;

    }

}
