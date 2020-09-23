package com.project.in.teams.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.jws.soap.SOAPBinding;
import javax.persistence.*;

@Data
@Entity
@Table(name = "managers")
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Team.class)
    @JoinColumn(name = "id")
    private Team team;

    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "id")
    private Users users;

}
