package ch.timofey.sb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIdPlacementException extends Exception{
    public InvalidIdPlacementException(String message){
        super(message);
    }
}
