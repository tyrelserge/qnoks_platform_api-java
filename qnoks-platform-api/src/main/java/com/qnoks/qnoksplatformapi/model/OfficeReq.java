package com.qnoks.qnoksplatformapi.model;

import lombok.Data;

@Data
public class OfficeReq {
    private Integer userId;
    private Integer departmentId;
    private Integer profileId;
    private String officeName;
}
