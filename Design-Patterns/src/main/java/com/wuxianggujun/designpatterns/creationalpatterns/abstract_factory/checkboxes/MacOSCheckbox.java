package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes;

public class MacOSCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("���Ѿ������� MacOSCheckbox");
    }
}
