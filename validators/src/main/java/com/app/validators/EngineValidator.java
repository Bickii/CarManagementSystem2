package com.app.validators;



import com.app.model.model.Engine;

import java.util.Map;

public class EngineValidator extends AbstractValidator<Engine> {

    @Override
    public Map<String, String> validate(Engine engine) {
        errors.clear();

        if (engine == null) {
            errors.put("car", "object is null");
        }

        if (!isPowerValid(engine)) {
            errors.put("engine power", "is not valid: " + engine.getPower());
        }

        if (!isEngineTypeValid(engine)) {
            errors.put("engine type", "is not valid: " + engine.getTypeOfEngine());
        }

        return errors;
    }

    private boolean isPowerValid(Engine engine) {
        return engine.getPower() >= 0;
    }

    private boolean isEngineTypeValid(Engine engine) {
        return engine.getTypeOfEngine() != null;
    }
}
