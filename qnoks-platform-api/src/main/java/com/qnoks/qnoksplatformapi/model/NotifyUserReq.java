package com.qnoks.qnoksplatformapi.model;

import lombok.Data;

@Data
public class NotifyUserReq {
    private Integer userId;
    private Integer notificationId;
    private String notificationLink;
}
