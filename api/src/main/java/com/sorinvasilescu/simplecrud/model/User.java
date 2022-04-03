package com.sorinvasilescu.simplecrud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // no role support for the moment
        return null;
    }

    public boolean isAccountNonExpired() {
        // no expiry support for now, all accounts are not expired
        return true;
    }

    public boolean isAccountNonLocked() {
        // no account lock support for now, all accounts are not locked
        return true;
    }

    public boolean isCredentialsNonExpired() {
        // no expiry support for now, all credentials are not expired
        return true;
    }

    public boolean isEnabled() {
        // no disable support for now, all accounts are enabled
        return true;
    }
}
