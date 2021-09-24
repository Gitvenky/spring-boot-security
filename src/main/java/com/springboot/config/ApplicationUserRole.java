package com.springboot.config;

import java.util.Set;

import com.google.common.collect.Sets;
import static com.springboot.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),ADMIN(Sets.newHashSet(COURSE_READ,STUDENT_READ,COURSE_WRITE,COURSE_READ));
	
	private Set<ApplicationUserPermission> permissions;
	
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions=permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}

}
