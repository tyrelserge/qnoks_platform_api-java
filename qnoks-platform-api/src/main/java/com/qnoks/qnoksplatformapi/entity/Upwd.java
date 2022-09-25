package com.qnoks.qnoksplatformapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "upwd")
public class Upwd {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upwd_id")
    private Integer upwdId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "pass_string")
    private String passString;
    @Column(name = "created_on")
    private Date createdOn;
    private String status;
}
