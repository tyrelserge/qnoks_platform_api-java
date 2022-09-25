package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, String> {

  Settings findByStatus(String status);

}
