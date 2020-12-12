package com.travelers.exceptions;

import org.apache.log4j.Logger;

public class NoSuchDriverException extends Throwable {

    private final Logger log = Logger.getLogger(NoSuchDriverException.class);

    public NoSuchDriverException() {
        log.error("Not found such driver");
    }
}
