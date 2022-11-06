package com.jskool.security.security;

public enum UserPermission {

    STUDENT_READ("student_read"),
    STUDENT_WRITE("student_write"),
    COURSE_READ("coarse_read"),
    COURSE_WRITE("coarse_write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
