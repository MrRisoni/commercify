package pojo;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonValue;

public class JsonStringResponse {
    @JsonValue
    @JsonRawValue
    private String value;

    public JsonStringResponse(String value) {
        this.value = value;
    }
}
