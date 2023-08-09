package com.spring.holaeat.domain.user_detail;

import com.spring.holaeat.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user_detail")
public class UserDetail {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private int age;

    @Column(name = "weight")
    private int weight;

    @Column(name = "height")
    private int height;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "rec_calories")
    private double recCalories;

    // Getters and setters
}

