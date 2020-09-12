package com.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(new Car("Лада", "седан"));
        cars.add(new Car("Лада", "хэтчбек"));
        cars.add(new Car("Мерседес", "седан"));
        cars.add(new Car("Бмв", "кроссовер"));
        cars.add(new Car("Форд", "хэтчбек"));
        cars.add(new Car("Пежо", "кроссовер"));
        cars.add(new Car("Тойота", "седан"));

        HashMap<String, ArrayList<String>> carsMap = new HashMap<>();

        for (Car car : cars) {
            if (!carsMap.containsKey(car.type)) {
                carsMap.put(car.type, new ArrayList<>());
            }
            carsMap.get(car.type).add(car.model);
        }

        Set<String> types = carsMap.keySet();

        for (String type : types) {
            System.out.println(type + " " + carsMap.get(type));
        }
    }
}
