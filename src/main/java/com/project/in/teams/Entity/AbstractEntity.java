package com.project.in.teams.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbstractEntity {

    private LocalDate created_at;

    private  LocalDate updated_at;

}
