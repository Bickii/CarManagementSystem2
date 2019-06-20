package com.app.main;

import com.app.service.MenuService;

public class App {
    public static void main(String[] args) {
        final String JSON_FILE_FOLDER = "C:\\Programowanie\\CarsManagementSystem2\\main\\src\\main\\resources\\";
        final String jsonFilename = JSON_FILE_FOLDER + "cars.json";
        MenuService menuService = new MenuService(jsonFilename);
        menuService.mainMenu();

    }
}
