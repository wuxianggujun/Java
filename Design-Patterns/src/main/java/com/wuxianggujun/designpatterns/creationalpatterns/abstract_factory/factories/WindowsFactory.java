package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.WindowsButton;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.WindowsCheckbox;

public class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
