package com.mediscreen.serviceui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {
       if(response.status() ==404){
           return new NotFoundException("s");
       }

       return defaultErrorDecoder.decode(s, response);
    }
}
