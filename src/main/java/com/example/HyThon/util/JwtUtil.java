package com.example.HyThon.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil implements InitializingBean {

    private String secret;
    private static SecretKey secretKey;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public String createAccessToken(Long id, String userName, Object o) {
        return null;
    }
}
