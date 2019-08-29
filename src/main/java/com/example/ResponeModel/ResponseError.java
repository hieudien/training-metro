package com.example.ResponeModel;

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

    private String error;
    private String errorDesciption;
}
