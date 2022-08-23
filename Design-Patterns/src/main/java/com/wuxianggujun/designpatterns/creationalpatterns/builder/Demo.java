package com.wuxianggujun.designpatterns.creationalpatterns.builder;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.builders.CarBuilder;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.builders.CarManualBuilder;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.Car;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.Manual;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.director.Director;

public class Demo {
    public static void main(String[] args) {
        Director director = new Director();
        CarBuilder builder = new CarBuilder();
        director.constructSportsCar(builder);


        Car car = builder.getResult();
        System.out.println("car.getCarType() = " + car.getCarType());


        CarManualBuilder manualBuilder = new CarManualBuilder();
        director.constructSportsCar(manualBuilder);
        Manual manual = manualBuilder.getResult();
        System.out.println("manual.print() = " + manual.print());
    }
}
