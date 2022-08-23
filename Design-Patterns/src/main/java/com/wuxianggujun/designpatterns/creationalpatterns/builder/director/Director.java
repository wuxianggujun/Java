package com.wuxianggujun.designpatterns.creationalpatterns.builder.director;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.builders.Builder;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.CarType;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Engine;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.GPSNavigator;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Transmission;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.TripComputer;

public class Director {
    public void constructSportsCar(Builder builder){
        builder.setCarType(CarType.SPORT_CAR);
        builder.setSeats(2);
        builder.setEngines(new Engine(3.0,0));
        builder.setTransmissions(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    } 
    public void constructCityCar(Builder builder){
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngines(new Engine(1.2,0));
        builder.setTransmissions(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
    } 
    public void constructSUV(Builder builder){
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngines(new Engine(2.5,0));
        builder.setTransmissions(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
    }
    
    
}
