package com.springboot.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.configuration.JwtRequestFilter;
import com.springboot.dao.RoleRepository;
import com.springboot.dao.UserDao;
import com.springboot.entity.ChangePassword;
import com.springboot.entity.Role;
import com.springboot.entity.User;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleRepository roleRepository;
	
//	public User registerNewUser(User user) {
//		
//		Role role = roleRepository.findById("User").get();
//		
//		Set<Role> roles = new HashSet<>();
//		roles.add(role);
//		user.setRole(roles);
//		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
//
//		return userDao.save(user);
//	}
	public User registerNewUser(User user) {
	Role role =	roleRepository.findById("User").get();
	Set<Role> roleSet = new HashSet<>();
	roleSet.add(role);
	
	user.setRole(roleSet);
	
	user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		
	return	userDao.save(user);
	}
	
    @PostConstruct
	public void initRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin Role");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created role");
		roleRepository.save(userRole);
		
        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUserPassword(passwordEncoder.encode("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);
        
        User user= new User();
        user.setUserName("adu12");
        user.setUserFirstName("aadarsh");
        user.setUserLastName("bhosale");
        user.setUserPassword(passwordEncoder.encode("ad@pass"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
        
		
//		
//		
	}

	public User getUser() {
	
	
User user = null;
	
	String username = JwtRequestFilter.CURRENT_USER;
	if(username != null) {
		user = userDao.findById(username).get();
	}
	
	return user;
	}
	public boolean changePassword(ChangePassword changePassword) {
        String username = JwtRequestFilter.CURRENT_USER;
        
        if (username != null) {
            User user = userDao.findById(username).orElse(null);
            if (user != null) {
                String storedPasswordHash = user.getUserPassword();
                String originalPassword = changePassword.getOriginalPassword();
                if (passwordEncoder.matches(originalPassword, storedPasswordHash)) {
                    
                    String newPassword = changePassword.getNewPassword();
                    String newPasswordHash = passwordEncoder.encode(newPassword);
                    user.setUserPassword(newPasswordHash);
                    userDao.save(user);
                    return true;
                }
            }
        }
        return false; 
    }
}