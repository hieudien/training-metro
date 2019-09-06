package com.example.DTO;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@EntityScan
public class SearchByPrefectureCodeResponse {
    private String data;
    private String code;
    private String city;
    private String cityKana;
    private String prefecture;
    private String prefectureKana;
    private String prefectureCode;
}
