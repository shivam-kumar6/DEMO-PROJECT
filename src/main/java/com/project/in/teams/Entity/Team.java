package com.project.in.teams.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="team_description")
    private String description;

    public List<Users> getUsersList() {
        return usersList;
    }

    public List<Services> getServicesList() {
        return servicesList;
    }

    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @OneToMany(targetEntity = Users.class,cascade = CascadeType.ALL)
    @JoinColumn(name="ut_fk",referencedColumnName = "id")
    private List<Users> usersList = new ArrayList<>( );

    @OneToMany(targetEntity = Services.class,cascade = CascadeType.ALL)
    @JoinColumn(name="st_fk",referencedColumnName = "id")
    private List<Services> servicesList = new ArrayList<>( );


}
