package com.qnoks.qnoksplatformapi.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundExceptionMessage {
    private String statusCode;
    private Date timestamp;
    private String message;
    private String source;
}
