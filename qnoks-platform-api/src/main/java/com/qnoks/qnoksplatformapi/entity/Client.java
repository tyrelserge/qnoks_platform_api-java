package com.qnoks.qnoksplatformapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(targetEntity = Company.class)
    @JoinTable(name = "client_company", joinColumns = {
    @JoinColumn(name = "client_id")}, inverseJoinColumns = {
    @JoinColumn(name = "company_id")})
    private List<Company> companies;

    private String status;
}
