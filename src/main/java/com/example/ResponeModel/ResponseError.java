package com.example.ResponeModel;

import lombok.Data;

@Data
public class ResponseError {
    /**
     * Constructor
     *
     * @param error           error code
     * @param errorDesciption error detail description
     */
    public ResponseError(String error, String errorDesciption) {
        this.error = error;
        this.errorDesciption = errorDesciption;
    }

    /**
     * Constructor
     */
    public ResponseError() {
    }

    private String error;
    private String errorDesciption;
}
