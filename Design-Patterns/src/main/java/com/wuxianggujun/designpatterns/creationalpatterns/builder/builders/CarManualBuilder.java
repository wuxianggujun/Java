package com.wuxianggujun.designpatterns.creationalpatterns.builder.builders;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.CarType;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.Manual;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Engine;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.GPSNavigator;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Transmission;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.TripComputer;

/**
 * Unlike other creational patterns, Builder can construct unrelated products,
 * which don't have the common interface.
 *
 * In this case we build a user manual for a car, using the same steps as we
 * built a car. This allows to produce manuals for specific car models,
 * configured with different features.
 */

public class CarManualBuilder implements Builder {
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
    public Manual getResult(){
        return new Manual(type,seats,engine,transmission,tripComputer,gpsNavigator);
    }
}
