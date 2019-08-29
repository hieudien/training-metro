package com.example.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "tbl_city")
public class City {
    @Id
    private String cityId;
    private String code;
    private String cityKana;
    private String city;
    private String prefectureId;


}
