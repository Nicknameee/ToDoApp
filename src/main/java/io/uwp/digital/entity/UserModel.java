package io.uwp.digital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.uwp.digital.enums.Role;
import io.uwp.digital.enums.Status;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username" , nullable = false , unique = true)
    private String username;
    @JsonIgnore
    @Column(name = "password" , nullable = false)
    private String password;
    @Column(name = "role" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    @Column(name = "status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ENABLED;
    @Column(name = "timezone", nullable = false)
    private String timezone;
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<ToDoItem> toDoItems;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return status == Status.ENABLED;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return status == Status.ENABLED;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return status == Status.ENABLED;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status == Status.ENABLED;
    }
}
