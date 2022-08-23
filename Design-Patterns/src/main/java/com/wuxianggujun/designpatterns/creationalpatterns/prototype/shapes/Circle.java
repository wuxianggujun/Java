package com.wuxianggujun.designpatterns.creationalpatterns.prototype.shapes;

import java.util.Objects;

public class Circle extends Shape{
    public int radius;
    
    public Circle(){
        
    }
    
    public Circle(Circle target){
        super(target);
        if (target!=null){
            this.radius = target.radius;
        }
    }
    
    @Override
    public Shape clone() {
        return new Circle(this);
    }
    
    @Override
    public boolean equals(Object object){
        if (!(object instanceof Circle)) return false;
        Circle circle = (Circle) object;
        return circle.radius == radius;
    }
}
