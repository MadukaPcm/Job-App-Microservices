package tz.maduka.uaa_v1.util.response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResponseDto<T> {

    private ResponseStatus response;
    private T data;
    private List<T> dataList;

    // Constructor for single object data
    public ResponseDto(ResponseStatus response, T data) {
        this.response = response;
        this.data = data;
        this.dataList = null;
    }

    // Constructor for list of objects data
    public ResponseDto(ResponseStatus response, List<T> dataList) {
        this.response = response;
        this.data = null;
        this.dataList = dataList;
    }

    // Constructor for response only, no data
    public ResponseDto(ResponseStatus response) {
        this.response = response;
        this.data = null;
        this.dataList = null;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "response=" + response +
                ", data=" + data +
                ", dataList=" + dataList +
                '}';
    }
}
