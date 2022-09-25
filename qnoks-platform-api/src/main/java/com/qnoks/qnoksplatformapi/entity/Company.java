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
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "billing_plan_id")
    private Integer billingPlanId;
    @Column(name = "company_shortname")
    private String companyShortname;
    @Column(name = "company_fullname")
    private String companyFullname;
    @Column(name = "company_tag_path")
    private String companyTag_path;
    @Column(name = "company_address")
    private String companyAddress;
    @Column(name = "company_postbox")
    private String companyPostbox;
    @Column(name = "company_phone")
    private String companyPhone;
    @Column(name = "company_fax")
    private String companyFax;
    @Column(name = "company_email")
    private String companyEmail;
    @Column(name = "company_website")
    private String companyWebsite;
    @Column(name = "company_rc")
    private String companyRc;
    @Column(name = "company_cc")
    private String companyCc;
    @Column(name = "created_on")
    private String createdOn;
    @Column(name = "updated_on")
    private String updatedOn;
    private String status;
}
