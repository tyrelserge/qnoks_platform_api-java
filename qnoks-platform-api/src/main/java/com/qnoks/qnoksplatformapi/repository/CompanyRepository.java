package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    
}
