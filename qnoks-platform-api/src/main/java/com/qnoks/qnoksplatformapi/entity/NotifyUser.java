package com.qnoks.qnoksplatformapi.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "notify_user")
public class NotifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notifuser_id")
    private Integer notifuserId;
    @Column(name = "notification_date")
    private Date notificationDate;
    @Column(name = "notification_seen")
    private Date notificationSeen;
    @Column(name = "notification_opened")
    private Date notificationOpened;
    @Column(name = "notification_link")
    private String notificationLink;

    @ManyToOne(targetEntity = Notification.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "notification_id", referencedColumnName = "notification_id")
    private Notification notification;
}
