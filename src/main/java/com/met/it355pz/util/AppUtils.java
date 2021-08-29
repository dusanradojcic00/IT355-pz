package com.met.it355pz.util;

import com.met.it355pz.exception.BadRequestException;
import org.springframework.http.HttpStatus;

public class AppUtils {
    public static void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number < 0");
        }

        if (size < 0) {
            throw new BadRequestException("Size number < 0");
        }

        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page number > " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
