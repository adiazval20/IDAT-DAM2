package edu.idat.eventosvirtuales.utils;

public class CustomResponse {
    private String type;
    private int rpta;
    private String message;
    private Object body;

    public CustomResponse() {
        type = "data";
        rpta = 0;
        message = "ok";
        body = new Object();
    }

    public CustomResponse(Object body) {
        type = "data";
        rpta = 0;
        message = "ok";
        this.body = body;
    }

    public CustomResponse(String type, int rpta, String message, Object body) {
        this.type = type;
        this.rpta = rpta;
        this.message = message;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRpta() {
        return rpta;
    }

    public void setRpta(int rpta) {
        this.rpta = rpta;
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
