package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    
}
