package com.example.DTO;

import lombok.Data;

@Data
public class SearchByPrefectureCodeResponse {
    private String data;
    private String code;
    private String city;
    private String cityKana;
    private String prefecture;
    private String prefectureKana;
    private String prefectureCode;
}
