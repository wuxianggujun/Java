package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes;

public class WindowsCheckbox implements Checkbox{
    @Override
    public void paint() {
        System.out.println("���Ѿ������� WindowsCheckbox");
    }
}
