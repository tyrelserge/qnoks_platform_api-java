package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {}
