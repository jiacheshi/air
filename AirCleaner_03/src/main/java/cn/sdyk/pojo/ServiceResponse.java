package cn.sdyk.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component(value="serviceResponse")
public class ServiceResponse {
    @JsonProperty("ErrorCode")
    private int errorCode;
    @JsonProperty("Message")
    private String message;
    @JsonProperty("ResultNode")
    private Object resultNode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultNode() {
        return resultNode;
    }

    public void setResultNode(Object resultNode) {
        this.resultNode = resultNode;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", resultNode=" + resultNode +
                '}';
    }

}
