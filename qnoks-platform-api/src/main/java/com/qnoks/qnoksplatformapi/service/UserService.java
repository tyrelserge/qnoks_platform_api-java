package com.qnoks.qnoksplatformapi.service;

import java.util.List;

import com.qnoks.qnoksplatformapi.entity.Settings;
import com.qnoks.qnoksplatformapi.entity.Staff;
import com.qnoks.qnoksplatformapi.entity.Client;
import com.qnoks.qnoksplatformapi.entity.Role;
import com.qnoks.qnoksplatformapi.entity.User;
import com.qnoks.qnoksplatformapi.model.SettingsReq;
import com.qnoks.qnoksplatformapi.model.Login;
import com.qnoks.qnoksplatformapi.model.RoleReq;
import com.qnoks.qnoksplatformapi.model.UserReq;

public interface UserService {
    public List<Role> getRoleList();
    public Role toCreateRole(RoleReq paramRoleReq);
    public Role toUpdateRole(Integer paramInteger, RoleReq paramRoleReq);
    public List<User> getAllUsers();
    public User getUserData(Integer paramInteger);
    public Object toCreateUser(UserReq paramUserReq);
    public String addPassword(Integer paramInteger, String paramString);
    public User toLogin(Login paramLogin);
    public List<Settings> getAllSettings();
    public Settings toUpdateGeneralSettings(SettingsReq paramGSettingsReq);
    public String setUserStatus(Integer userId, String status);
    public List<User> getSearchUserByname(String byname);
    // public FCMToken toAddFCMToken(FCMTokenReq fcmToken);
    // public FCMToken getUserFCMToken(Integer userId);
    
    public List<Staff> getAllStaff();
    public Object toCreateStaff(UserReq data);
    public Staff getStaffData(Integer staffId);
    public Staff toStaffLogin(Login loginData);
    public String setStaffPassword(Integer staffId, String pwd);
    public String setStaffStatus(Integer staffId, String status);

    public List<Client> getAllClient();
    public Object toCreateClient(UserReq data);
    public Client getClientData(Integer clientId);
    public String setClientStatus(Integer clientId, String status);
    public Client toClientLogin(Login loginData);
    public String setClientPassword(Integer client, String pwd);

}
