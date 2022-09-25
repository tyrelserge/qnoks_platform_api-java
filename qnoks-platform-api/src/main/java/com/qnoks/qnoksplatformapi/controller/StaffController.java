package com.qnoks.qnoksplatformapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qnoks.qnoksplatformapi.Constants;
import com.qnoks.qnoksplatformapi.entity.Role;
import com.qnoks.qnoksplatformapi.entity.Staff;
import com.qnoks.qnoksplatformapi.model.Login;
import com.qnoks.qnoksplatformapi.model.PwdReq;
import com.qnoks.qnoksplatformapi.model.ResponseDto;
import com.qnoks.qnoksplatformapi.model.RoleReq;
import com.qnoks.qnoksplatformapi.model.UserReq;
import com.qnoks.qnoksplatformapi.model.uStatus;
import com.qnoks.qnoksplatformapi.service.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping("/staff")
public class StaffController {
    
    @Autowired
    private UserService userService;

    
    //------------------------------------
    //  RÔLE
    //------------------------------------


    @GetMapping({"/roles"})
    public ResponseEntity<ResponseDto<List<Role>>> Roles() {
      List<Role> res = userService.getRoleList();
      return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Role>>(Constants.SUCCESS, res)) :
        ResponseEntity.ok(new ResponseDto<List<Role>>(Constants.ERROR, null));
    }

    @PostMapping({"/role/create"})
    public ResponseEntity<ResponseDto<Role>> CreateRole(@RequestBody RoleReq roleReq) {
        Role res = userService.toCreateRole(roleReq);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Role>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Role>(Constants.ERROR, null));
    }

    @PutMapping({"/role/{roleId}"})
    public ResponseEntity<ResponseDto<Role>> UpdateRole(
        @RequestBody RoleReq roleData, @PathVariable("roleId") Integer roleId) {
        Role res = userService.toUpdateRole(roleId, roleData);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Role>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Role>(Constants.ERROR, null));
    }

    
    //------------------------------------
    //  STAFF
    //------------------------------------


    @GetMapping(value="/all")
    public ResponseEntity<ResponseDto<List<Staff>>> AllStaff() {
        List<Staff> res = userService.getAllStaff();
        return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Staff>>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<List<Staff>>(Constants.ERROR, null));
    }

    @PostMapping({"/create"})
    public ResponseEntity<ResponseDto<Object>> CreateStaff(@RequestBody UserReq data) {
        Object res = userService.toCreateStaff(data);
        return (res != null) ? !res.equals(Constants.EXISTS) ?
            ResponseEntity.ok(new ResponseDto<Object>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Object>(Constants.EXISTS, null)) :
            ResponseEntity.ok(new ResponseDto<Object>(Constants.ERROR, null));
    }    
    
    @GetMapping({"/{staffid}"})
    public ResponseEntity<ResponseDto<Staff>> StaffData(@PathVariable("staffid") Integer staffId) {
        Staff res = userService.getStaffData(staffId);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Staff>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Staff>(Constants.ERROR, null));
    }

    @PutMapping({"/{staffId}/set-status"})
    public ResponseEntity<ResponseDto<String>> ActivateStaff(
        @PathVariable("staffId") Integer staffId, @RequestBody uStatus status) {
            String res = userService.setStaffStatus(staffId, status.getStatus());
        return (res != null) ? ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<String>(Constants.ERROR, null));
    }

    @PostMapping({"/login"})
    public ResponseEntity<ResponseDto<Staff>> StaffLogin(@RequestBody Login LoginData) {
        Staff res = userService.toStaffLogin(LoginData);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Staff>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Staff>(Constants.ERROR, null));
    }

    @PostMapping({"/{staffId}/setpassword"})
    public ResponseEntity<ResponseDto<String>> UpdateStaffPwd(
        @PathVariable("staffId") Integer staffId, @RequestBody PwdReq staff) {
        String res = userService.setStaffPassword(staffId, staff.getPwd());
        return (res != null) ? ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<String>(Constants.ERROR, null));
    }


    // @GetMapping({"/search/{byname}"})
    // public ResponseEntity<ResponseDto<List<User>>> SearchUserByame(@PathVariable("byname") String byname) {
    //     List<User> res = userService.getSearchUserByname(byname);
    //     return (res != null) ? ResponseEntity.ok(new ResponseDto<List<User>>(Constants.SUCCESS, res)) :
    //         ResponseEntity.ok(new ResponseDto<List<User>>(Constants.ERROR, null));
    // }
}
