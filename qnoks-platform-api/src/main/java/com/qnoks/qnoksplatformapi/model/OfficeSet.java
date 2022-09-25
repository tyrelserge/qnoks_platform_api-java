package com.qnoks.qnoksplatformapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeSet {
    private Integer userId;
    private Integer[] officeIds;
}
