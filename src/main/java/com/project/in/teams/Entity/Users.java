package com.project.in.teams.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="gender")
    private String gender;

    @Column(name="designation")
    private String designation;

    @Column(name="email",unique = true,nullable = false)
    private String email;

    @Column(name="phone_no",unique = true,nullable = false)
    private String phone_no;

    @Column(name = "password")
    private String password;

    @Column(name = "ut_fk")
    private Long ut_fk;

}
