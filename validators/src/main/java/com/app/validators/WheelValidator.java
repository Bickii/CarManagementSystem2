package com.app.validators;



import com.app.model.model.Wheel;

import java.util.Map;

public class WheelValidator extends AbstractValidator<Wheel> {

    @Override
    public Map<String, String> validate(Wheel wheel) {

        errors.clear();

        if (wheel == null) {
            errors.put("car", "object is null");
        }

        if (!isModelValid(wheel)) {
            errors.put("wheel model", "is not valid: " + wheel.getModel());
        }

        if (!isSizeValid(wheel)) {
            errors.put("wheel size", "is not valid: " + wheel.getSize());
        }

        if (!isTypeValid(wheel)) {
            errors.put("wheel type", "is not valid: " + wheel.getWheelType());
        }

        return errors;

    }

    private boolean isModelValid(Wheel wheel) {
        return wheel.getModel() != null && wheel.getModel().matches("[A-Z]+");
    }

    private boolean isSizeValid(Wheel wheel) {
        return wheel.getSize() > 0;
    }

    private boolean isTypeValid(Wheel wheel) {
        return wheel.getWheelType() != null;
    }
}
