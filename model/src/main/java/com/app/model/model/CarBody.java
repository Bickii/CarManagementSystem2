package com.app.model.model;

import com.app.model.enums.CarBodyColour;
import com.app.model.enums.CarBodyType;
import com.app.model.enums.Component;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

public class CarBody {
    private CarBodyColour carBodyColour;
    private CarBodyType carBodyType;
    private List<Component> components;
}
