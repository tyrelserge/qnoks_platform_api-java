package com.qnoks.qnoksplatformapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qnoks.qnoksplatformapi.entity.NotifyUser;

@Repository
@Transactional
public interface NotifuserRepository extends JpaRepository<NotifyUser, Integer> {

    @Query(value = "SELECT * FROM notify_user WHERE user_id = ? ORDER BY notifuser_id DESC", nativeQuery = true)
    List<NotifyUser> fetchUserNotificationsList(Integer userId);

    @Modifying
    @Query(value = "INSERT INTO notifuser(user_id, notification_id, notification_date, notification_seen, notification_opened, notification_link) VALUES (?1, ?2, NOW(), NULL, NULL, ?3)", nativeQuery = true)
    Integer saveUserNotification(Integer userId, Integer notificationId, String notificationLink);

    @Query(value = "SELECT * FROM notify_user WHERE user_id=? ORDER BY notifuser_id DESC LIMIT 1", nativeQuery = true)
    NotifyUser fetchLastUserDavingNotification(Integer userId);

    @Modifying
    @Query(value = "UPDATE notify_user SET notification_seen=NOW() WHERE user_id=?1 AND notification_seen IS NULL", nativeQuery = true)
    Integer executeSetSeenNotification(Integer userId);

    @Modifying
    @Query(value = "UPDATE notify_user SET notification_opened=NOW() WHERE notifuser_id=?1", nativeQuery = true)
    Integer executeSetOpenedNotification(Integer notifuserId);
}
