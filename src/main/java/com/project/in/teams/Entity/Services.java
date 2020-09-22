package com.project.in.teams.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="services")
public class Services {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="service_description")
    private String service_description;

//    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "services")
//    private team team;

}
