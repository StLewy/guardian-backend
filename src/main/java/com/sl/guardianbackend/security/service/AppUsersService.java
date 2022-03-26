package com.sl.guardianbackend.security.service;

import com.sl.guardianbackend.security.model.AppUser;
import com.sl.guardianbackend.security.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AppUsersService {
    AppUser saveUser(AppUser appUser);

    Role saveRole(Role role);

    void addRoleToAppUser(String username, String roleName);

    AppUser getUser(String username);

    List<AppUser> getUsers();

    void deleteAppUser(String username);

    String[] getRoleFromRequest(HttpServletRequest request);

    String getUsernameFromRequest(HttpServletRequest request);
}
