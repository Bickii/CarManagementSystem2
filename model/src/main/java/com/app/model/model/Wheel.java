package com.app.model.model;

import com.app.model.enums.WheelType;
import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString

public class Wheel {
    private String model;
    private int size;
    private WheelType wheelType;
}
