package com.spring.holaeat.domain.admin;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Entity
@Table(name="admin")
public class Admin {
    @Id
    private String id;
    private String password;

}
