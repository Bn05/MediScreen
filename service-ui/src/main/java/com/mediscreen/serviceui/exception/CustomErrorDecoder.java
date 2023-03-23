package com.mediscreen.serviceui.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotAllowedException;
import org.springframework.stereotype.Component;

@Component
public class CustomErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();
    @Override
    public Exception decode(String s, Response response) {

        if(response.status()==400){
            return new BadRequestException(s);
        }

        if(response.status() ==404){
           return new NotFoundException(s);
       }

       if(response.status()==405){
           return new NotAllowedException(s);
       }

       return defaultErrorDecoder.decode(s, response);
    }
}
