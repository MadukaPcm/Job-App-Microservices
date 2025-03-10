package tz.maduka.uaa_v1.dtos.payload;
import lombok.NoArgsConstructor;import lombok.Setter;


@NoArgsConstructor
public class RegisterDto {

    private String username;
    private String password;

    public RegisterDto(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
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
}