package com.met.it355pz.service;

import com.met.it355pz.exception.BadRequestException;
import com.met.it355pz.exception.NoSuchElementFoundException;
import com.met.it355pz.model.Car;
import com.met.it355pz.model.Reservation;
import com.met.it355pz.repo.CarRepo;
import com.met.it355pz.repo.ReservationRepo;
import com.met.it355pz.service.CarService;
import com.met.it355pz.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ReservationRepo reservationRepo;


    @Override
    public List<Car> getAllCars() {
        return carRepo.findAll();
    }

    @Override
    public Car getCarById(long id) {
        return carRepo.findById(id).orElseThrow(() -> new NoSuchElementFoundException(id));
    }

    @Override
    public void deleteCar(long id) {
        carRepo.deleteById(id);
    }

    @Override
    public long saveCar(Car car) {
        return carRepo.save(car).getId();
    }

    @Override
    public Car updateCar(Car car) {
        return carRepo.save(car);
    }

    @Override
    public List<Car> availableCars(String startDate, String endDate) {

        try {
            AppUtils.validateStartEndDate(startDate, endDate);
        } catch (ParseException e) {
            throw new BadRequestException("Dates are not okay");
        }

        List<Car> availableCars = new ArrayList<>();

        List<Car> allCars = carRepo.findAll();

        Date start = null;
        Date end = null;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        } catch (ParseException e) {
            throw new BadRequestException("Dates are not okay");
        }

        Date finalStart = start;
        Date finalEnd = end;
        allCars.forEach(car -> {

            List<Reservation> reservationList = reservationRepo.findAllByCar(car);

            if (reservationList.isEmpty()) {
                availableCars.add(car);
            } else {
                for (int i = 0; i < reservationList.size(); i++) {
                    if (reservationList.get(i).getStartingDate().after(finalEnd)) {
                        availableCars.add(car);
                        break;
                    } else if (reservationList.get(i).getEndingDate().before(finalStart)) {
                        availableCars.add(car);
                        break;
                    }
                }
            }
        });
        return availableCars;
    }
}
