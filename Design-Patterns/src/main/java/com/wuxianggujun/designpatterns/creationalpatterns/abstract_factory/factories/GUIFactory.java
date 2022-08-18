package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;

/**
 * 抽象工厂了解所有的产品类型
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
