package com.example.DTO;

import lombok.Data;

@Data
public class SearchByPostCodeResponse {
    private String data;
    private String code;
    private String city;
    private String cityKana;
    private String prefecture;
    private String prefectureKana;
    private String prefectureCode;
    private String area;
    private String areaKana;
    private String multiPostArea;
    private String koazaArea;
    private String chomeArea;
    private String oldPostCode;
    private String postCode;
    private String multiArea;
    private String updateShow;
    private String changeReason;
}
