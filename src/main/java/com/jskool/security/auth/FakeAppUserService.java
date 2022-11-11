package com.jskool.security.auth;

import com.google.common.collect.Lists;
import com.jskool.security.security.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.jskool.security.security.UserRole.ADMIN;
import static com.jskool.security.security.UserRole.STUDENT;

@Repository("fake")
public class FakeAppUserService implements AppUserDao{

    private final PasswordEncoder passwordEncoder;

    public FakeAppUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<AppUser> selectAppUserByUsername(String username) {
        return getAppUsers().stream().filter(appUser -> username.equals(appUser.getUsername()))
                .findFirst();
    }

    public List<AppUser> getAppUsers() {
        List<AppUser> appUsers = Lists.newArrayList(
                new AppUser(STUDENT.getSimpleGrantedAuthorities()
                ,passwordEncoder.encode("password")
                ,"sema",true,
                        true
                ,true,
                        true),
                new AppUser(ADMIN.getSimpleGrantedAuthorities()
                        ,passwordEncoder.encode("pass123")
                        ,"juyel",true,
                        true
                        ,true,
                        true),
                new AppUser(STUDENT.getSimpleGrantedAuthorities()
                        ,passwordEncoder.encode("pass123")
                        ,"sakil",true,
                        true
                        ,true,
                        true));
        return appUsers;
    }


}
