package com.sl.guardianbackend.security.model;

public enum RoleEnum {
    ROLE_SUPERADMIN("ROLE_SUPERADMIN"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_GUARDIAN("ROLE_GUARDIAN"),
    ROLE_SPONSOR("ROLE_SPONSOR");

    private RoleEnum(final String role) {
        this.role = role;
    }

    private final String role;

    public String getRole() {
        return role;
    }
}
