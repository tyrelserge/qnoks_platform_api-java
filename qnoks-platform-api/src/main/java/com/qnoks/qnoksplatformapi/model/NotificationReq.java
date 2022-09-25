package com.qnoks.qnoksplatformapi.model;

import lombok.Data;

@Data
public class NotificationReq {
    private Integer userId;
    private String notificationSubject;
    private String notificationDetails;
}
