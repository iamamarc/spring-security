package com.mark.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.mark.demo.security.ApplicationUserPermission.*;

public enum  ApplicationUserRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRoles(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
