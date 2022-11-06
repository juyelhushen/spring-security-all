package com.jskool.security.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(UserPermission.STUDENT_READ,UserPermission.STUDENT_WRITE,UserPermission.COURSE_WRITE
    ,UserPermission.COURSE_READ)),
    ADMINTRAINEE(Sets.newHashSet(UserPermission.STUDENT_READ,UserPermission.COURSE_READ));

    private final Set<UserPermission> permissions;


    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }


    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permission = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }
}
