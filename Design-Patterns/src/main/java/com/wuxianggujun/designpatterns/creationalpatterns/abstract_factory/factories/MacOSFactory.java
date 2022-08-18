package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.MacOSButton;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.MacOSCheckbox;

/**
 * 每个具体的工厂扩展基本的工厂并负责创建
 * 单一品种的产品。
 */
public class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
