package engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Long id;

    @Email(message = "The email must have a valid format (with @ and .)", regexp = "^\\w+@\\w+\\.\\w+$")
    @JsonProperty("email")
    private String username;
    @Size(min = 5, message = "The password must have at least five characters.")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
