package com.qnoks.qnoksplatformapi.model;

import lombok.Data;

@Data
public class UserReq {
    private Integer roleId;
    private String lastname;
    private String firstname;
    private String civility;
    private String email;
    private String mobile;
    private String password;
}
