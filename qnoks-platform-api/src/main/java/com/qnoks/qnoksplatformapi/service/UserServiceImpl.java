package com.qnoks.qnoksplatformapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qnoks.qnoksplatformapi.Constants;
import com.qnoks.qnoksplatformapi.entity.Settings;
import com.qnoks.qnoksplatformapi.entity.Staff;
import com.qnoks.qnoksplatformapi.entity.Client;
import com.qnoks.qnoksplatformapi.entity.Role;
import com.qnoks.qnoksplatformapi.entity.Upwd;
import com.qnoks.qnoksplatformapi.entity.User;
import com.qnoks.qnoksplatformapi.exception.ResourceNotFoundException;
import com.qnoks.qnoksplatformapi.model.SettingsReq;
import com.qnoks.qnoksplatformapi.model.Login;
import com.qnoks.qnoksplatformapi.model.RoleReq;
import com.qnoks.qnoksplatformapi.model.UserReq;
import com.qnoks.qnoksplatformapi.repository.SettingsRepository;
import com.qnoks.qnoksplatformapi.repository.StaffRepository;
import com.qnoks.qnoksplatformapi.repository.ClientRepository;
import com.qnoks.qnoksplatformapi.repository.RoleRepository;
import com.qnoks.qnoksplatformapi.repository.UpwdRepository;
import com.qnoks.qnoksplatformapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UpwdRepository userPasswordRepository;

  @Autowired
  private SettingsRepository generalSettingRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public Object toCreateUser(UserReq userData) {

    Optional<User> optional = userRepository.findByEmail(userData.getEmail().trim());
    if (optional.isPresent()) {
      return optional.get();
    } else {
      optional = userRepository.findByMobile(userData.getMobile().trim());
      if (optional.isPresent()) {
        return optional.get();
      }
    }

    User user = new User(
      null,
      userData.getLastname(),
      userData.getFirstname(),
      userData.getCivility()!=null ? userData.getCivility().equalsIgnoreCase("M") ? "H" : "F" : null,
      StringUtils.capitalize(userData.getCivility().toLowerCase()),
      userData.getEmail(),
      userData.getMobile(),
      new Date(),
      null,
      null);

    User savedUser = userRepository.save(user);
    addPassword(savedUser.getUserId(), userData.getPassword());

    return savedUser;
  }

  
  public User getUserData(Integer userId) {
    User user = (User)userRepository.findById(userId).orElseThrow(
      () -> new ResourceNotFoundException("The user id " + userId + " is not found"));
    return user;
  }


  public List<Role> getRoleList() {
    List<Role> roles = roleRepository.findAll();
    return roles;
  }

  public String addPassword(Integer userId, String pwd) {
    userPasswordRepository.setOldPassword(userId);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordHash = encoder.encode(pwd);

    Upwd upwd = new Upwd();
    upwd.setUserId(userId);
    upwd.setPassString(passwordHash);
    upwd.setCreatedOn(new Date());
    upwd.setStatus("active");

    userPasswordRepository.save(upwd);
    return "DONE";
  }

  public User toLogin(Login loginData) {

    if(loginData.getUsername()==null || loginData.getPassword()==null)
      return null;

    Optional<User> optional;

    Optional<User> optionalEmail = userRepository.findByEmail(loginData.getUsername().trim());

    if (optionalEmail.isPresent()) {
      optional=optionalEmail;
    } else {
      Optional<User> optionalMobile = userRepository.findByMobile(loginData.getUsername().trim());
      if (!optionalMobile.isPresent()) 
        return null;
      optional = optionalMobile;
    }

    User user = optional.get();

    String loginPwd = loginData.getPassword();
    String currentPwd = userPasswordRepository.fetchUserCurrentPwd(user.getUserId());

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (!encoder.matches(loginPwd, currentPwd)) return null;

    return user;
  }

  @Override
  public List<Settings> getAllSettings() {
    return generalSettingRepository.findAll();
  }

  public Settings toUpdateGeneralSettings(SettingsReq gSettingsReq) {
    Settings currentSetting = generalSettingRepository.findByStatus(Constants.ACTIVE.toLowerCase());

    Settings setting = new Settings();

    setting.setSettingsId(currentSetting.getSettingsId());
    setting.setUserId(gSettingsReq.getUserId());
    setting.setCurrency(gSettingsReq.getCurrency());
    setting.setCreatedOn(currentSetting.getCreatedOn());
    setting.setUpdatedOn(new Date());
    setting.setStatus(currentSetting.getStatus());

    return (Settings) generalSettingRepository.save(setting);
  }


  public Role toCreateRole(RoleReq roleData) {
    Role role = new Role();
    role.setRoleName(roleData.getRoleName());
    role.setRoleLevel(roleData.getRoleLevel());
    role.setCreatedOn(new Date());

    return (Role)roleRepository.save(role);
  }


  public Role toUpdateRole(Integer roleId, RoleReq roleData) {
    Optional<Role> currentRole = roleRepository.findById(roleId);
    if (!currentRole.isPresent()) {
      return null;
    }
    Role role = new Role();
    role.setRoleId(roleId);
    role.setRoleName(roleData.getRoleName());
    role.setRoleLevel(roleData.getRoleLevel());
    role.setCreatedOn(((Role)currentRole.get()).getCreatedOn());
    role.setUpdatedOn(new Date());

    return (Role)roleRepository.save(role);
  }


  @Override
  public String setUserStatus(Integer userId, String status) {
    return userRepository.executeSetUserStatus(userId, status)!=1 ? null : Constants.DONE;
  }


  public List<User> getSearchUserByname(String byname) {
    String lastname = byname;
    String firstname = byname;
    List<User> users = userRepository.fetchUserbyFilds(lastname, firstname);

    return users;
  }



  //------------------------------------
  //  STAFF SERVICES
  //------------------------------------


  @Autowired
  private StaffRepository staffRepository;


  @Override
  public List<Staff> getAllStaff() {
    return staffRepository.findAll();
  }


  @Override
  public Object toCreateStaff(UserReq data) {

    Object UserRes = toCreateUser(data);
    
    if (UserRes==null) return null;
    User user = (User) UserRes; 

    String status = "linked";
    
    if (user.getStatus()!=null && user.getStatus().equalsIgnoreCase(status))
      return Constants.EXISTS;

    if (staffRepository.saveStaff(data.getRoleId(), user.getUserId())!=1) 
      return null;

    if (!setUserStatus(user.getUserId(), status).equalsIgnoreCase(Constants.DONE))
      return null;

    return staffRepository.fetchLastSavingStaff();
  }


  @Override
  public Staff getStaffData(Integer staffId) {
    return staffRepository.findById(staffId).orElseThrow(
      () -> new ResourceNotFoundException("The staff id " + staffId + " is not found !"));
  }
  

  @Override
  public String setStaffStatus(Integer staffId, String status) {
    return staffRepository.executeSetStaffStatus(staffId, status)!=1 ? null : Constants.DONE;
  }


  @Override
  public Staff toStaffLogin(Login loginData) {
    
    User user = toLogin(loginData);
    if (user==null) return null;
    
    return staffRepository.findStaffByUser(user);
  }


  @Override
  public String setStaffPassword(Integer staffId, String pwd) {
    Staff staff = staffRepository.findById(staffId).orElseThrow(
      () -> new ResourceNotFoundException("The staff id " + staffId + " is not found !"));
    
    User user = staff.getUser();

    return addPassword(user.getUserId(), pwd);
  }


  //------------------------------------
  //  CLIENT SERVICES
  //------------------------------------


  @Autowired
  private ClientRepository clientRepository;


  @Override
  public List<Client> getAllClient() {
    return clientRepository.findAll();
  }

  @Override
  public Object toCreateClient(UserReq data) {

    Object UserRes = toCreateUser(data);
    
    if (UserRes==null) return null;
    User user = (User) UserRes; 

    String status = "linked";
    
    if (user.getStatus()!=null && user.getStatus().equalsIgnoreCase(status))
      return Constants.EXISTS;

    if (clientRepository.saveClient(user.getUserId())!=1) 
      return null;

    if (!setUserStatus(user.getUserId(), status).equalsIgnoreCase(Constants.DONE))
      return null;

    return clientRepository.fetchLastSavingClient();
  }

  @Override
  public Client getClientData(Integer clientId) {
    return clientRepository.findById(clientId).orElseThrow(
      () -> new ResourceNotFoundException("The staff id " + clientId + " is not found !"));
  }

  @Override
  public String setClientStatus(Integer clientId, String status) {
    return clientRepository.executeSetClientStatus(clientId, status)!=1 ? null : Constants.DONE;
  }

  @Override
  public Client toClientLogin(Login loginData) {
    
    User user = toLogin(loginData);
    if (user==null) return null;
    
    return clientRepository.findClientByUser(user);
  }

  @Override
  public String setClientPassword(Integer clientId, String pwd) {

    Client client = clientRepository.findById(clientId).orElseThrow(
      () -> new ResourceNotFoundException("The staff id " + clientId + " is not found !"));
    
    User user = client.getUser();

    return addPassword(user.getUserId(), pwd);
  }
  


  // @Autowired
  // private FCMTokenRepository fcmTokenRepository;

  // @Override
  // public FCMToken toAddFCMToken(FCMTokenReq tokenData) {
  //   Optional<FCMToken> optional = fcmTokenRepository.findByUserId(tokenData.getUserId());

  //   FCMToken fcmToken = new FCMToken();

  //   if (optional.isPresent()) {
  //     fcmToken.setFcmTokenId(optional.get().getFcmTokenId());
  //     fcmToken.setUserId(optional.get().getUserId());
  //     fcmToken.setFcmTokenAccess(tokenData.getFcmTokenAccess());
  //     fcmToken.setFcmTokenUpdatedOn(new Date());
  //   } else {
  //     fcmToken.setUserId(tokenData.getUserId());
  //     fcmToken.setFcmTokenAccess(tokenData.getFcmTokenAccess());
  //     fcmToken.setFcmTokenUpdatedOn(new Date());
  //   }

  //   return fcmTokenRepository.save(fcmToken);
  // }


  // @Override
  // public FCMToken getUserFCMToken(Integer userId) {
  //   return fcmTokenRepository.findByUserId(userId).orElseThrow(
  //     ()-> new ResourceNotFoundException("Token non trouvé"));
  // }

}
