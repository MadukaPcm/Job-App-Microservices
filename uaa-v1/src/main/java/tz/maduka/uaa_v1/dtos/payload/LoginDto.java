package tz.maduka.uaa_v1.dtos.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginDto {

    private String username;
    private String password;

}