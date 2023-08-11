package com.spring.holaeat.domain.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin,String> {


    @Query(nativeQuery = true,value = "SELECT * FROM admin WHERE id = ?1 and password = ?2")
    public List<Admin> findAdminByIdAndPassword(String id,String pwd);

}
