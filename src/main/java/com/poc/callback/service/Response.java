package com.poc.callback.service;


public class Response {

    private int id;
    private String parameter;

    public Response(int id, String parameter) {
        this.id = id;
        this.parameter = parameter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
