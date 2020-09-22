package com.project.in.teams.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@JsonInclude
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="team_description")
    private String description;

    @OneToMany(targetEntity = Users.class,cascade = CascadeType.ALL)
    @JoinColumn(name="ut_fk",referencedColumnName = "id")
    private List<Users> usersList;

    @OneToMany(targetEntity = Services.class,cascade = CascadeType.ALL)
    @JoinColumn(name="st_fk",referencedColumnName = "id")
    private List<Services> servicesList;

    @Column(name = "password")
    private String password;



//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public List<Service> getServiceList() {
//        return serviceList;
//    }
//
//    public void setServicesList(List<Service> serviceList) {
//        this.serviceList = serviceList;
//    }
//
//    public void setUsersList(List<User> userList) {
//        this.userList = userList;
//    }


}
