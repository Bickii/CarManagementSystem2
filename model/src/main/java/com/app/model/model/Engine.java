package com.app.model.model;


import com.app.model.enums.TypeOfEngine;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

public class Engine {
    private TypeOfEngine typeOfEngine;
    private double power;
}
