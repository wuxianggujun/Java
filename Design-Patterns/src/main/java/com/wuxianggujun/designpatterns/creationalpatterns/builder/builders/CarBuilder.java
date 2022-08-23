package com.wuxianggujun.designpatterns.creationalpatterns.builder.builders;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.Car;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.CarType;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Engine;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.GPSNavigator;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Transmission;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.TripComputer;

public class CarBuilder implements Builder {
    private CarType type;
    private int seats;
    private Engine engine;
    private Transmission transmission;
    private TripComputer tripComputer;
    private GPSNavigator gpsNavigator;

    @Override
    public void setCarType(CarType carType) {
        this.type = carType;
    }

    @Override
    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public void setEngines(Engine engine) {
        this.engine = engine;
    }

    @Override
    public void setTransmissions(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public void setTripComputer(TripComputer tripComputer) {
        this.tripComputer = tripComputer;
    }

    @Override
    public void setGPSNavigator(GPSNavigator navigator) {
        this.gpsNavigator = navigator;
    }

    public Car getResult() {
        return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
    }
}
