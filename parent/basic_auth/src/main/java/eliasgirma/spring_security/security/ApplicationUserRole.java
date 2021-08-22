package eliasgirma.spring_security.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static eliasgirma.spring_security.security.applicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT (Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,COURSE_WRITE,STUDENT_READ,STUDENT_WRITE )),
    ADMINTRAINEE(Sets.newHashSet(COURSE_READ,STUDENT_READ ));


    private Set<applicationUserPermission> permissionSet;

    ApplicationUserRole(Set<applicationUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<applicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthorities()
    {
        Set<SimpleGrantedAuthority> permissions = getPermissionSet().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_ "+ this.name()));
        return permissions;
    }
}
