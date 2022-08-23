package com.wuxianggujun.designpatterns.creationalpatterns.builder.builders;

import com.wuxianggujun.designpatterns.creationalpatterns.builder.cars.CarType;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Engine;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.GPSNavigator;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.Transmission;
import com.wuxianggujun.designpatterns.creationalpatterns.builder.components.TripComputer;

/**
 * Builder接口 定义全部产品参数
 */
public interface Builder {
    void setCarType(CarType carType);
    void setSeats(int seats);
    void setEngines(Engine engine);
    void setTransmissions(Transmission transmission);
    void setTripComputer(TripComputer tripComputer);
    void setGPSNavigator(GPSNavigator navigator);
}
