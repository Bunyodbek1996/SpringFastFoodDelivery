package uz.bprodevelopment.base.util;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorResponse{

    private Date timestamp;
    private Integer status = 500;
    private String message = "";

    public ErrorResponse() {}

    public ErrorResponse(String message){
        timestamp = new Date();
        this.message = message;
    }

    public ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
