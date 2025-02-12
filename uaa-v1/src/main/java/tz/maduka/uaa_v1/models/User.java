package tz.maduka.uaa_v1.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;


@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false,updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    // Other fields or methods can be added as needed
    public User() {

    }
    public User(String id, String userName, String password) {
        super();
        this.id = id;
        this.username = userName;
        this.password = password;
    }
    public User( String userName, String password) {
        super();
        this.username = userName;
        this.password = password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        // TODO Auto-generated method stub
        this.id = id;
    }

    // Methods required by Spring Security for user details
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
