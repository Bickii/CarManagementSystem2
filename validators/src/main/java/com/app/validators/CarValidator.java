package com.app.validators;


import com.app.model.model.Car;

import java.math.BigDecimal;
import java.util.Map;

public class CarValidator extends AbstractValidator<Car> {

    public final Map<String, String> validate(Car car) {
        errors.clear();

        if (car == null) {
            errors.put("car", "object is null");
        }
        if (!isModelValid(car)) {
            errors.put("car model", "is not valid: " + car.getModel());
        }
        if (!isPriceValid(car)) {
            errors.put("price", "is not valid: " + car.getPrice());
        }
        if (!isMileageValid(car)){
            errors.put("mileage","is not valid: " + car.getMileage());
        }

        errors.putAll(new EngineValidator().validate(car.getEngine()));
        errors.putAll(new WheelValidator().validate(car.getWheel()));
        errors.putAll(new CarBodyValidator().validate(car.getCarBody()));

        return errors;
    }

    private boolean isModelValid(Car car) { return car.getModel() != null && car.getModel().matches("^[A-Z\\s]+$"); }
    private boolean isPriceValid(Car car) { return car.getPrice() != null && car.getPrice().compareTo(BigDecimal.ZERO) > 0; }
    private boolean isMileageValid(Car car) { return car.getMileage() >= 0; }
}
