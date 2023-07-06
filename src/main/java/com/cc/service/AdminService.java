package com.cc.service;

public interface AdminService {
    public Boolean verifyUser(String id, String password);
    public Boolean addUser(String id, String password);
}
