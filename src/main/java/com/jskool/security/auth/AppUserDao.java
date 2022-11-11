package com.jskool.security.auth;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> selectAppUserByUsername(String username);
}
