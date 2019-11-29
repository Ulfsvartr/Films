package com.tstu.utils;

import javax.ws.rs.core.Response;


public class ResponseUtil {

    public static Response badRequestResponse(Exception e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(e.getMessage())
                .build();
    }

    public static Response unauthorizedResponse(String string) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(string)
                .build();
    }

    public static Response successResponse(Object object) {
        return Response
                .status(Response.Status.OK)
                .entity(object)
                .build();
    }


    public static Response notFoundResponse(Exception e){
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(e.getMessage())
                .build();
    }
}
