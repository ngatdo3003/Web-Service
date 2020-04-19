package com.ngatdo.soap.endpoint;

import com.ngatdo.soap.request.*;
import com.ngatdo.soap.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    @Autowired
    private UserService userService;


    @PayloadRoot(namespace = "http://ngatdo.com/soapws",
            localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUserRequest(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.getUsers(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = "http://ngatdo.com/soapws",
            localPart = "addUserRequest")
    @ResponsePayload
    public GetUserResponse addUserRequest(@RequestPayload AddUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.addUser(request.getUser()));
        return response;
    }

    @PayloadRoot(namespace = "http://ngatdo.com/soapws",
            localPart = "updateUserRequest")
    @ResponsePayload
    public GetUserResponse updateUserRequest(@RequestPayload UpdateUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.updateUser(request.getUser()));
        return response;
    }

    @PayloadRoot(namespace = "http://ngatdo.com/soapws",
            localPart = "deleteUserRequest")
    @ResponsePayload
    public GetUserResponse deleteUserRequest(@RequestPayload DeleteUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.deleteUser(request.getName()));
        return response;
    }
}
