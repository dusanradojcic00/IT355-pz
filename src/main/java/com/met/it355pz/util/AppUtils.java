package com.met.it355pz.util;

import com.met.it355pz.exception.BadRequestException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static boolean validateStartEndDate(String startDate, String endDate) throws ParseException {
        LocalDate start = formatDate(startDate);
        LocalDate end = formatDate(endDate);
        if (start.isAfter(end) || start.isBefore(LocalDate.now())) {
            throw new BadRequestException("Dates are not okay");
        } else {
            return true;
        }
    }

    public static LocalDate formatDate(String date) throws ParseException {
        return LocalDate.parse(date);
    }

}
