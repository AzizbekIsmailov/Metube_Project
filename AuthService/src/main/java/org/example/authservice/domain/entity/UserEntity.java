package org.example.authservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jdk.jfr.Enabled;
import lombok.*;
import metube.com.dto.BaseEntity;
import org.example.authservice.domain.entity.enumerators.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Builder
public class UserEntity extends BaseEntity implements UserDetails {

    private String username;
    private String password;
    private String email;
    private String picturePath;
    private UUID videoId;
    private UUID subscriptionId;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}


