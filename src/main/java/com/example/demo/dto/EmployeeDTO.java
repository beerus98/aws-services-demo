package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDTO {

    //@JsonProperty(value = "id")
    private Long id;
    //@JsonProperty(value = "name")
    private String name;

}
