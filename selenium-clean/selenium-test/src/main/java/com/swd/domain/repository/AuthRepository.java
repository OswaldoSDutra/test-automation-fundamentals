package com.swd.domain.repository;

import com.swd.domain.entity.User;

public interface AuthRepository {
    <T extends Iterable<T>> T getAuthCookies();
    String getHtmlAntiForgeryToken();
    <T> T registerUser(User user);
}
