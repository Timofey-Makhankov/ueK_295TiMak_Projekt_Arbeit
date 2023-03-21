package ch.timofey.sb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DiscountNotFoundException extends RuntimeException{
    public DiscountNotFoundException(String message){
        super(message);
    }
}
