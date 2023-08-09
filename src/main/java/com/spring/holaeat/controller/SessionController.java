package com.spring.holaeat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SessionController {

    @GetMapping("/getSessionValues")
    @ResponseBody
    public ResponseEntity<Map> getSessionValues(HttpSession session) {
        Map sessionValues = new HashMap<>();
        sessionValues.put("userAge", session.getAttribute("userAge"));
        sessionValues.put("userHeight", session.getAttribute("userHeight"));
        sessionValues.put("userWeight", session.getAttribute("userWeight"));
        sessionValues.put("userAllergy", session.getAttribute("userAllergy"));
        sessionValues.put("userRecCalories", session.getAttribute("userRecCalories"));

        return ResponseEntity.ok(sessionValues);
    }
}