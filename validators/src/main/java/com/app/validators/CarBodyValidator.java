package com.app.validators;


import com.app.model.model.CarBody;

import java.util.Map;
import java.util.Objects;

public class CarBodyValidator extends AbstractValidator<CarBody> {

    @Override
    public Map<String, String> validate(CarBody element) {
        errors.clear();

        if (element == null) {
            errors.put("car body", "object is null");
        }
        if (!isCarBodyColour(element)) {
            errors.put("car body color", "is not valid: " + element.getCarBodyColour());
        }

        if (!isCarBodyType(element)) {
            errors.put("car body type", "is not valid: " + element.getCarBodyType());
        }

        if (!isCarBodyComponents(element)) {
            errors.put("car body components", "is not valid: " + element.getComponents());
        }

        return errors;
    }

    private boolean isCarBodyColour(CarBody carBody) {
        return carBody.getCarBodyColour() != null;
    }

    private boolean isCarBodyType(CarBody carBody) {
        return carBody.getCarBodyType() != null;
    }

    private boolean isCarBodyComponents(CarBody carBody) {
        return carBody.getComponents() != null && carBody.getComponents().stream().noneMatch(Objects::isNull);
    }
}
