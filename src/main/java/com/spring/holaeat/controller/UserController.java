package com.spring.holaeat.controller;

import com.spring.holaeat.domain.user.User;
import com.spring.holaeat.domain.user.UserRequestDto;
import com.spring.holaeat.domain.user.UserRepository;
import com.spring.holaeat.service.UserCaloriesService;
import com.spring.holaeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping(value = "/join", consumes = "application/json")
    public Map<String, Object> join(@RequestBody UserRequestDto userDto) {
        JSONObject response = new JSONObject();

        try {
            userService.getUserById(userDto.getUserId());
            System.out.println("join fail");
            response.put("result", false);
        } catch (IllegalArgumentException e) {
            userService.createUser(userDto);
            System.out.println("join success");
            response.put("result", true);
        }
        return response.toMap();
    }

}
