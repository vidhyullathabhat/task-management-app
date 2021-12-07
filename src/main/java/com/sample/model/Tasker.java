package com.sample.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tasker")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tasker {

    @Id
    @JsonProperty("name")
    private String name;

    @JsonProperty("date")
    private LocalDate date;

}
