package com.qnoks.qnoksplatformapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Integer notificationId;
    @Column(name = "notification_subject")
    private String notificationSubject;
    @Column(name = "notification_details")
    private String notificationDetails;
    @Column(name = "notification_link")
    private String notificationLink;
}
