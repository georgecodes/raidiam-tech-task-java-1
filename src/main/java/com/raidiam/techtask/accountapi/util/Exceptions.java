package com.raidiam.techtask.accountapi.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Exceptions {

    public static ResponseStatusException notFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

}
