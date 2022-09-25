package com.qnoks.qnoksplatformapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qnoks.qnoksplatformapi.Constants;
import com.qnoks.qnoksplatformapi.entity.Settings;
import com.qnoks.qnoksplatformapi.entity.User;
import com.qnoks.qnoksplatformapi.model.ResponseDto;
import com.qnoks.qnoksplatformapi.model.SettingsReq;
import com.qnoks.qnoksplatformapi.service.UserServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping({"/search/{byname}"})
    public ResponseEntity<ResponseDto<List<User>>> SearchUserByame(@PathVariable("byname") String byname) {
        List<User> res = userService.getSearchUserByname(byname);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<List<User>>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<List<User>>(Constants.ERROR, null));
    }

    
    //------------------------------------
    //  NOTIFICATION
    //------------------------------------


    // @GetMapping(value="/notification/messages")
    // public ResponseEntity<ResponseDto<List<Notification>>> NotificationMessagesList() {
    //   List<Notification> res = userService.getNotificationMessagesList();
    //     return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Notification>>(Constants.SUCCESS, res)) :
    //     ResponseEntity.ok(new ResponseDto<List<Notification>>(Constants.ERROR, null));
    // }

    // @GetMapping(value="/notification/message/{notificationId}")
    // public ResponseEntity<ResponseDto<Notification>> getNotification(@PathVariable("notificationId") Integer notificationId) {
    //     Notification res = userService.getNotification(notificationId);
    //     return (res != null) ? ResponseEntity.ok(new ResponseDto<Notification>(Constants.SUCCESS, res)) :
    //     ResponseEntity.ok(new ResponseDto<Notification>(Constants.ERROR, null));
    // }

    // @PostMapping(value="/notification/message")
    // public ResponseEntity<ResponseDto<Notification>> SaveNotification(@RequestBody NotificationReq noticeDate) {
    //   Notification res = userService.saveNotification(noticeDate);
    //   return (res != null) ? ResponseEntity.ok(new ResponseDto<Notification>(Constants.SUCCESS, res)) :
    //   ResponseEntity.ok(new ResponseDto<Notification>(Constants.ERROR, null));
    // }

    // @PutMapping(value="/notification/message/{notificationId}")
    // public ResponseEntity<ResponseDto<Notification>> SetNotification(
    //   @PathVariable("notificationId") Integer notificationId, @RequestBody NotificationReq noticeDate) {
    //   Notification res = userService.setUpdateNotification(notificationId, noticeDate);
    //   return (res != null) ? ResponseEntity.ok(new ResponseDto<Notification>(Constants.SUCCESS, res)) :
    //   ResponseEntity.ok(new ResponseDto<Notification>(Constants.ERROR, null));
    // }

    // @DeleteMapping(value="/notification/message/{notificationId}")
    // public ResponseEntity<ResponseDto<String>> DelNotification(@PathVariable("notificationId") Integer notificationId) {
    //     userService.setDeleteNotification(notificationId);
    //     return ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, "DONE"));
    // }

    // @GetMapping(value="/notifies/{userId}")
    // public ResponseEntity<ResponseDto<List<Notifuser>>> UserNotificationsList(@PathVariable("userId") Integer userId) {
    //   List<Notifuser> res = userService.getUserNotificationsList(userId);
    //     return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Notifuser>>(Constants.SUCCESS, res)) :
    //     ResponseEntity.ok(new ResponseDto<List<Notifuser>>(Constants.ERROR, null));
    // }

    // @GetMapping(value="/notify/{notificationId}")
    // public ResponseEntity<ResponseDto<Notifuser>> getUserNotification(@PathVariable("notificationId") Integer notificationId) {
    //   Notifuser res = userService.getUserNotification(notificationId);
    //     return (res != null) ? ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.SUCCESS, res)) :
    //     ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.ERROR, null));
    // }

    // @PostMapping(value="/notify")
    // public ResponseEntity<ResponseDto<Notifuser>> SendUserNotification(
    //   @RequestBody NotifyUserReq noticeDate) {
    //   Notifuser res = userService.sendUserNotification(noticeDate);
    //   return (res != null) ? ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.SUCCESS, res)) :
    //   ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.ERROR, null));
    // }

    // @GetMapping(value="/notify/opened/{notificationId}")
    // public ResponseEntity<ResponseDto<Notifuser>> SetOpenedUserNotification(
    //   @PathVariable("notificationId") Integer notificationId) {
    //     Notifuser res = userService.setOpenedNotification(notificationId);
    //   return (res != null) ? ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.SUCCESS, res)) :
    //   ResponseEntity.ok(new ResponseDto<Notifuser>(Constants.ERROR, null));
    // }

    // @GetMapping(value="/notify/seen/{userId}")
    // public ResponseEntity<ResponseDto<String>> SetSeenUserNotification(
    //   @PathVariable("userId") Integer userId) {
    //     String res = userService.setSeenNotification(userId);
    //   return (res != null) ? ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, res)) :
    //   ResponseEntity.ok(new ResponseDto<String>(Constants.ERROR, null));
    // }

    //------------------------------------
    //  SETTINGS
    //------------------------------------

    @GetMapping({"/settings"})
    public ResponseEntity<ResponseDto<List<Settings>>> AllSettings() {
      List<Settings> res = userService.getAllSettings();
      return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Settings>>(Constants.SUCCESS, res)) :
        ResponseEntity.ok(new ResponseDto<List<Settings>>(Constants.ERROR, null));
    }


    @PutMapping({"/settings/update"})
    public ResponseEntity<ResponseDto<Settings>> UpdateGeneralSettings(@RequestBody SettingsReq gSettingsReq) {
      Settings res = userService.toUpdateGeneralSettings(gSettingsReq);
      return (res != null) ? ResponseEntity.ok(new ResponseDto<Settings>(Constants.SUCCESS, res)) :
        ResponseEntity.ok(new ResponseDto<Settings>(Constants.ERROR, null));
    }



    // @Autowired
    // public JavaMailSender emailSender;

    // @ResponseBody
    // @PostMapping({"/sendmail"})
    // public ResponseEntity<ResponseDto<String>> SendEmail(@RequestBody MailData mailData) {

    //   SimpleMailMessage message = new SimpleMailMessage();				// Créer un MailMessage Simple

    //   message.setTo(mailData.getAddress());
    //   message.setSubject(mailData.getSubject());
    //   message.setText(mailData.getContent());

    //   emailSender.send(message);

    //   return ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, "Email Sent"));

    // }


    // @GetMapping({"/fcm-token/{userId}"})
    // public ResponseEntity<ResponseDto<FCMToken>> UserFCMToken(
    //   @PathVariable("userId") Integer userId) {
    //   FCMToken res = userService.getUserFCMToken(userId);
    //   return res!=null ? ResponseEntity.ok(new ResponseDto<FCMToken>(Constants.SUCCESS, res)):
    //       ResponseEntity.ok(new ResponseDto<FCMToken>(Constants.ERROR, null));
    // }

    // // Save and update
    // @PostMapping({"/fcm-token"})
    // public ResponseEntity<ResponseDto<FCMToken>> AddFCMToken(@RequestBody FCMTokenReq fcmToken) {
    //   FCMToken res = userService.toAddFCMToken(fcmToken);
    //   return res!=null ? ResponseEntity.ok(new ResponseDto<FCMToken>(Constants.SUCCESS, res)):
    //       ResponseEntity.ok(new ResponseDto<FCMToken>(Constants.ERROR, null));
    // }


}
