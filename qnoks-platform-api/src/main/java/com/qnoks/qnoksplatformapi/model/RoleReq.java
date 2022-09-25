package com.qnoks.qnoksplatformapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleReq {
    private Integer userId;
    private String roleName;
    private String roleLevel;
}
