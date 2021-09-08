package com.met.it355pz.util;

import com.met.it355pz.exception.BadRequestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AppUtilsTest {

    @Test
    @DisplayName("Both dates are valid")
    void validaStartEndDate() throws DateTimeParseException {
        String startDate = "2021-09-15";
        String endDate = "2021-09-20";
        boolean result = AppUtils.validateStartEndDate(startDate, endDate);
        assertTrue(result);
    }

    @Test
    @DisplayName("Start date is invalid")
    void invalidaStartDate() throws DateTimeParseException {
        String startDate = "2021-09-100";
        String endDate = "2021-09-10";
        assertThrows(DateTimeParseException.class, () -> AppUtils.validateStartEndDate(startDate, endDate));
    }

    @Test
    @DisplayName("End date is invalid")
    void invalidaEndDate() {
        String startDate = "2021-09-10";
        String endDate = "2021-25-07";
        assertThrows(DateTimeParseException.class, () -> AppUtils.validateStartEndDate(startDate, endDate));
    }

    @Test
    @DisplayName("End date is before start date")
    void endBeforeStart() {
        String startDate = "2021-09-12";
        String endDate = "2021-09-10";
        BadRequestException exception = assertThrows(BadRequestException.class, () -> AppUtils.validateStartEndDate(startDate, endDate));
        assertTrue(exception.getMessage().contains("Start date must be before end date"));
    }

    @Test
    @DisplayName("Start date is in the past")
    void startDateInPast(){
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth() + 1);
        LocalDate yesterday = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth() - 1);
        String startDate = yesterday.toString();
        String endDate = tomorrow.toString();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> AppUtils.validateStartEndDate(startDate, endDate));
        assertTrue(exception.getMessage().contains("Start date can't be in the past"));
    }

    @Test
    @DisplayName("Start date is today")
    void startDateToday(){
        LocalDate now = LocalDate.now();
        String startDate = now.toString();
        LocalDate tomorrow = LocalDate.of(now.getYear(), now.getMonth(), now.getDayOfMonth() + 1);
        String endDate = tomorrow.toString();
        assertTrue(AppUtils.validateStartEndDate(startDate, endDate));
    }

    @Test
    @DisplayName("Start date and end date are same")
    void sameDates(){
        LocalDate now = LocalDate.now();
        String startDate = now.toString();
        String endDate = now.toString();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> AppUtils.validateStartEndDate(startDate, endDate));
        assertTrue(exception.getMessage().contains("Start date must be before end date"));
    }

    @Test
    @DisplayName("Date is in format yyyy-MM-dd")
    void formatDate() {
        String date = "2021-09-12";
        LocalDate localDate = LocalDate.of(2021, 9, 12);
        assertEquals(localDate, AppUtils.formatDate(date));
    }

    @Test
    @DisplayName("Date is not in format yyyy-MM-dd")
    void formatDateWrongFormat() {
        String date = "1-1-2021";
        assertThrows(DateTimeParseException.class, () -> {
            AppUtils.formatDate(date);
        });
    }
}