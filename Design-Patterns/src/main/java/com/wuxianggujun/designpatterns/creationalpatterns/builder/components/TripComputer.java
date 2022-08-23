package com.wuxianggujun.designpatterns.creationalpatterns.builder.components;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.Car;

public class TripComputer {
    private Car car;
    public void setCar(Car car){
        this.car = car;
    }
    public void showFuelLevel(){
        System.out.println("FUel level:"+car.getFuel());
    }
    
    public void showStatus(){
        if (this.car.getEngine().isStarted()){
            System.out.println("Car is started");
        }else {
            System.out.println("Cao isn`t started");
        }
    }
}
