package com.sparta.model;

public enum UserRoleEnum {
    USER(Authority.USER),  // 사용자 권한
    ADMIN(Authority.ADMIN);  // 관리자 권한

    public String getAuthorithy() {
        return authorithy;
    }

    private final String authorithy;

    UserRoleEnum(String authorithy){
        this.authorithy = authorithy;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
