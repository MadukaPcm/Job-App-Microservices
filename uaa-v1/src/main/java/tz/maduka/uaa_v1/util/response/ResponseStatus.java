package tz.maduka.uaa_v1.util.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResponseStatus {

    private String id;
    private boolean status;
    private String code;
    private String message;

    public ResponseStatus(String id, boolean status, String code, String message) {
        this.id = id;
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
