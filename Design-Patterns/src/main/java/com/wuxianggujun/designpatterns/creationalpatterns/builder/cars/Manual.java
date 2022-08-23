package com.wuxianggujun.designpatterns.creationalpatterns.builder.cars;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Engine;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.GPSNavigator;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Transmission;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.TripComputer;

public class Manual {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;


    public Manual(CarType type, int seats, Engine engine, Transmission transmission, TripComputer tripComputer, GPSNavigator gpsNavigator) {
        this.carType = type;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        this.gpsNavigator = gpsNavigator;
    }

    public String print() {
        StringBuilder info = new StringBuilder();
        info.append("Type of car:").append(carType).append("\n");
        info.append("Count of seats :").append(seats).append("\n");
        info.append("Engine: volume - ").append(engine.getVolume()).append("; ").append("mileage - ").append(engine.getMileage()).append("\n");
        info.append("Transmission: ").append(transmission).append("\n");
        if (this.tripComputer != null) {
            info.append("Trip Computer: Functional").append("\n");
        } else {
            info.append("Trip Computer:N/A").append("\n");
        }
        if (this.gpsNavigator != null) {
            info.append("GPSNavigator: Functional").append("\n");
        } else {
            info.append("GPS Navigator: N/A").append("\n");
        }
        return info.toString();
    }
    
}
