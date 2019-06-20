package com.app.service;

import com.app.exceptions.MyException;

import com.app.model.enums.CarBodyType;
import com.app.model.enums.TypeOfEngine;
import com.app.model.enums.WheelType;
import com.app.model.model.Car;
import com.app.service.enums.SortType;

import java.math.BigDecimal;
import java.util.*;

public class MenuService {

    private final CarService carService;
    private final UserDataService userDataService;

    public MenuService(String jsonFilename) {
        this.carService = new CarService(jsonFilename);
        this.userDataService = new UserDataService();
    }

    public void mainMenu() {
        do {

            try {

                printMenu();
                int option = userDataService.getInt("Choose option");

                switch (option) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
                        break;
                    case 4:
                        option4();
                        break;
                    case 5:
                        option5();
                        break;
                    case 6:
                        option6();
                        break;
                    case 7:
                        option7();
                        break;
                    case 8:
                        option8();
                        break;

                }

            } catch (MyException e) {
                System.out.println("\n\n------------------- EXCEPTION ---------------");
                System.out.println(e.getMessage());
                System.out.println("---------------------------------------------\n\n");
            }

        } while (true);
    }

    private void printMenu() {
        System.out.println("\n\n-----------------------------------------");
        System.out.println("1 - sorted carService");
        System.out.println("2 - specified body ");
        System.out.println("3 - car models with engine type  ");
        System.out.println("4 - stats ");
        System.out.println("5 - wheel types matching cars");
        System.out.println("6 - cars with mielages ");
        System.out.println("7 - cars with components  ");
        System.out.println("8 - show cars");
    }

    private void option1() {
        SortType sortType = userDataService.getSortType();
        boolean descending = userDataService.getBoolean("Descending?");
        carService
                .sort(sortType, descending)
                .forEach(car -> System.out.println(car));
    }

    private void option2() {
        List<Car> l1 = new ArrayList<>(carService.specifiedBodyWithPriceBetweenParameters(CarBodyType.COMBI, new BigDecimal(123412), new BigDecimal(200000)));
        System.out.println(l1);
    }

    private void option3() {
        List<Car> l1 = new ArrayList<>(carService.carModelsWithEngineType(TypeOfEngine.DIESEL));
        System.out.println(l1);
    }

    private void option4() {
        carService.stats();
    }

    private void option5() {
        Map<WheelType,List<Car>> m1 = new HashMap<>(carService.wheelTypesMatchingCars());
        System.out.println(m1);
    }
    private void option6() {
        Map<Car,Integer> m1 = new HashMap<>(carService.carsWithMileages());
        System.out.println(m1);
    }
    private void option7() {
        List<Car> l1 = new ArrayList<>(carService.carsWithComponents(new HashSet<>()));
        System.out.println(l1);
    }
    private void option8() {
        System.out.println(carService);
    }

}