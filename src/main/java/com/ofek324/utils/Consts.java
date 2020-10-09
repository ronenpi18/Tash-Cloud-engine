package com.ofek324.utils;

import java.util.Optional;

public class Consts {
    public static String DB_NAME = "ofek";
    public static String MIDDLEWARE_ACTOR_NAME = "MiddlewareActor";
    public static String REQUESTS_ACTOR_NAME = "RequestsActor";
    public static String CLOUFSYS_ACTOR_NAME = "cloudSystem";
    public static String SERVICES_COLLECTION_NAME = "Services";
    public static String FLOW_COLLECTION_NAME = "Flows";
    public static final int PORT = Integer.parseInt(Optional.ofNullable(System.getenv("APP_HTTP_PORT")).orElse("8080"));
    public static String MSG_RSP_SRV_EXISTS = "This Action Name is Taken. Please choose another one";
    public static String MSG_RSP_UNKNOWN_ERROR = "Unknown error, call admin";
    public static String MSG_RSP_SUCCESS = "success";

}
