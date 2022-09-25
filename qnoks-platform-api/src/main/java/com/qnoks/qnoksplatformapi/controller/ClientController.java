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
import com.qnoks.qnoksplatformapi.entity.Client;
import com.qnoks.qnoksplatformapi.model.Login;
import com.qnoks.qnoksplatformapi.model.PwdReq;
import com.qnoks.qnoksplatformapi.model.ResponseDto;
import com.qnoks.qnoksplatformapi.model.UserReq;
import com.qnoks.qnoksplatformapi.model.uStatus;
import com.qnoks.qnoksplatformapi.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping(value="/all")
    public ResponseEntity<ResponseDto<List<Client>>> AllClient() {
        List<Client> res = userService.getAllClient();
        return (res != null) ? ResponseEntity.ok(new ResponseDto<List<Client>>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<List<Client>>(Constants.ERROR, null));
    }

    @PostMapping({"/create"})
    public ResponseEntity<ResponseDto<Object>> CreateClient(@RequestBody UserReq data) {
        Object res = userService.toCreateClient(data);
        return (res != null) ? !res.equals(Constants.EXISTS) ?
            ResponseEntity.ok(new ResponseDto<Object>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Object>(Constants.EXISTS, null)) :
            ResponseEntity.ok(new ResponseDto<Object>(Constants.ERROR, null));
    }    
    
    @GetMapping({"/{clientId}"})
    public ResponseEntity<ResponseDto<Client>> ClientData(@PathVariable("clientId") Integer clientId) {
        Client res = userService.getClientData(clientId);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Client>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Client>(Constants.ERROR, null));
    }

    @PutMapping({"/{clientId}/set-status"})
    public ResponseEntity<ResponseDto<String>> ActivateClient(
        @PathVariable("clientId") Integer clientId, @RequestBody uStatus status) {
            String res = userService.setClientStatus(clientId, status.getStatus());
        return (res != null) ? ResponseEntity.ok(new ResponseDto<String>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<String>(Constants.ERROR, null));
    }

    @PostMapping({"/login"})
    public ResponseEntity<ResponseDto<Client>> ClientLogin(@RequestBody Login LoginData) {
        Client res = userService.toClientLogin(LoginData);
        return (res != null) ? ResponseEntity.ok(new ResponseDto<Client>(Constants.SUCCESS, res)) :
            ResponseEntity.ok(new ResponseDto<Client>(Constants.ERROR, null));
    }

    @PostMapping({"/{clientId}/setpassword"})
    public ResponseEntity<ResponseDto<String>> UpdateClientPwd(
        @PathVariable("clientId") Integer clientId, @RequestBody PwdReq client) {
        String res = userService.setClientPassword(clientId, client.getPwd());
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
