package edu.idat.semana10.api;

public class CustomResponse {
    private String type;
    private String message;
    private Object body;

    public CustomResponse() {
    }

    public CustomResponse(String type, String message, Object body) {
        this.type = type;
        this.message = message;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
