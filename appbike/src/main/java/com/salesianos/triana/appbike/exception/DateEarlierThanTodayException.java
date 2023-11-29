package com.salesianos.triana.appbike.exception;

public class DateEarlierThanTodayException extends RuntimeException{
    public DateEarlierThanTodayException(String message){super(message);}
}
