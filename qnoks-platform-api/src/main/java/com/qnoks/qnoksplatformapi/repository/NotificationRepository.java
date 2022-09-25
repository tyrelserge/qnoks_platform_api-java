package com.qnoks.qnoksplatformapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qnoks.qnoksplatformapi.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}
