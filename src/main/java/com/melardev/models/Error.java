package com.melardev.models;

import org.springframework.http.HttpStatus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Error {
    public final String error;
    public final String message;
    public final int status;
    public final String date;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public Error(Throwable throwable, String message, HttpStatus status) {
        this.error = "";//ExceptionUtil.getRootMessage(throwable);
        this.message = message;
        this.date = dateFormat.format(new Date());
        this.status = status.value();
    }

    @Override
    public String toString() {
        return "ErrorInfo [status="+status+", error=" + error + ", date=" + date + "]";
    }
}
