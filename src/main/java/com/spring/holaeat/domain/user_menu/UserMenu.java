package com.spring.holaeat.domain.user_menu;

import com.spring.holaeat.domain.menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user_menu")
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "menu_no")
    private Long menuNo;


    public void setMenuNo(Long menuNo) {
        this.menuNo = menuNo;
    }

    // Constructors, getters, setters, etc.
}