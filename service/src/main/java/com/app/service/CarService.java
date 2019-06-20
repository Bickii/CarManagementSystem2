package com.app.service;

import com.app.converters.CarJsonConverter;
import com.app.exceptions.MyException;
import com.app.model.enums.CarBodyType;
import com.app.model.enums.Component;
import com.app.model.enums.TypeOfEngine;
import com.app.model.enums.WheelType;
import com.app.model.model.Car;
import com.app.service.enums.SortType;
import com.app.validators.CarValidator;
import org.eclipse.collections.impl.collector.Collectors2;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class CarService {
    private final Set<Car> cars;

    public CarService(String jsonFilename) {
        cars = getFromJsonFile(jsonFilename);
    }


    private Set<Car> getFromJsonFile(String ... jsonFilenames) {

        CarValidator carValidator = new CarValidator();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        return Arrays
                .stream(jsonFilenames)
                .map(jsonFilename -> new CarJsonConverter(jsonFilename).fromJson().orElseThrow(() -> new MyException("cannont convert file " + jsonFilename + " to car")))
                .filter(car -> {
                    System.out.println(car);
                    Map<String, String> errors = carValidator.validate(car);

                    if (carValidator.hasErrors()) {
                        System.out.println("\n\n----------------------------------------------");
                        System.out.println("EXCEPTION IN CAR NO. " + atomicInteger.get());
                        errors.forEach((k, v) -> System.out.println(k + " " + v));
                        System.out.println("----------------------------------------------\n\n");
                    }
                    atomicInteger.incrementAndGet();
                    return !carValidator.hasErrors();
                }).collect(Collectors.toSet());
    }

    public List<Car> sort(SortType sortType, boolean descending) {
        if (sortType == null) {
            throw new MyException("Sort type object is null");
        }

        Stream<Car> carStream = null;

        switch (sortType) {
            case COMPONENTS:
                carStream = cars.stream().sorted(Comparator.comparingInt(c -> c.getCarBody().getComponents().size()));
                break;
            case ENGINEPOWER:
                carStream = cars.stream().sorted(Comparator.comparing(c -> c.getEngine().getPower()));
                break;
            case WHEELSIZE:
                carStream = cars.stream().sorted(Comparator.comparing(c -> c.getWheel().getSize()));
                break;
        }
        List<Car> sortedCars = carStream.collect(Collectors.toList());

        if (descending) {
            Collections.reverse(sortedCars);
        }
        return sortedCars;
    }

    List<Car> specifiedBodyWithPriceBetweenParameters(CarBodyType carBodyType, BigDecimal minPrice, BigDecimal maxPrice) {
        return cars.stream()
                .filter(car -> car.getModel().equals(carBodyType))
                .filter(car -> car.getPrice().compareTo(minPrice) > 0 && car.getPrice().compareTo(maxPrice) < 0)
                .collect(Collectors.toList());
    }

    List<Car> carModelsWithEngineType (TypeOfEngine typeOfEngine) {
        return cars.stream()
                .filter(c -> c.getEngine().getTypeOfEngine().equals(typeOfEngine))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }
    void stats() {
        System.out
                .println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMin());
        System.out
                .println(cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getMax());
        System.out.println(
                cars.stream().collect(Collectors2.summarizingBigDecimal(Car::getPrice)).getAverage());

        System.out
                .println(cars.stream().collect(Collectors.summarizingInt(Car::getMileage)).getMin());
        System.out
                .println(cars.stream().collect(Collectors.summarizingInt(Car::getMileage)).getMax());
        System.out
                .println(cars.stream().collect(Collectors.summarizingInt(Car::getMileage)).getAverage());


        System.out
                .println(cars.stream().collect(Collectors.summarizingDouble(car -> car.getEngine().getPower())).getMin());


    }
    public Map<WheelType, List<Car>> wheelTypesMatchingCars() {
        return cars.stream()
                .collect(groupingBy(car -> car.getWheel().getWheelType(), Collectors.toList()));
    }

    public Map<Car,Integer> carsWithMileages () {
        return cars
                .stream()
                .collect(Collectors.toMap(Function.identity(),car -> car.getMileage()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::max, LinkedHashMap::new));
    }
    
    public List<Car> carsWithComponents(Set<Component> components) {

        if (components == null) {
            throw new MyException("components collection is null");
        }

        return cars
                .stream()
                .filter(car -> car.getCarBody().getComponents().containsAll(components))
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());

    }

    @Override
    public String toString() {
        return cars.stream().map(car -> car.toString()).collect(Collectors.joining("\n"));
    }

}
