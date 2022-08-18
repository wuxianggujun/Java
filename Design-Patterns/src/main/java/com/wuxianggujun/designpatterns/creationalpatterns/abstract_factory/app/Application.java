package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.app;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories.GUIFactory;
/**
 * Factory users don't care which concrete factory they use since they work with
 * factories and products through abstract interfaces.
 */

public class Application {
    private Button button;
    private Checkbox checkbox;


    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }
}
