package com.company.syugai.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException(){
        super();
    }

    public ApplicationException(String message){
        super(message);
    }

    public ApplicationException(String message, Throwable throwable){
        super(message, throwable);
    }

    public ApplicationException(Throwable throwable){
        super(throwable);
    }
}
