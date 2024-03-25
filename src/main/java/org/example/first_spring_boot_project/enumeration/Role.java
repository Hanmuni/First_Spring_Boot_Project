package org.example.first_spring_boot_project.enumeration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {
    USER(Right.TODO_READ, Right.TODO_READ_ALL, Right.TODO_READ_DONE, Right.TODO_READ_COUNT_DONE, Right.TODO_READ_OPEN
            , Right.TODO_READ_COUNT_OPEN, Right.TODO_CREATE, Right.TODO_UPDATE, Right.TODO_DELETE),
    ANALYST(Right.TODO_READ, Right.TODO_READ_DONE, Right.TODO_READ_COUNT_DONE, Right.TODO_READ_OPEN,
            Right.TODO_READ_COUNT_OPEN
            // USER LESEN
    ),
    ADMIN(Right.TODO_READ, Right.TODO_READ_ALL, Right.TODO_READ_DONE, Right.TODO_READ_COUNT_DONE,
          Right.TODO_READ_OPEN, Right.TODO_READ_COUNT_OPEN, Right.TODO_CREATE, Right.TODO_UPDATE, Right.TODO_DELETE
          // USER LESEN UND SCHREIBEN
    );

    private List<Right> rights = new ArrayList<>();

    Role(Right... rights) {
        Collections.addAll(this.rights, rights);
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        return rights.stream().map(right -> new SimpleGrantedAuthority("ROLE_" + right)).collect(Collectors.toList());
    }


}
