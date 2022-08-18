package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;

/**
 * ���󹤳��˽����еĲ�Ʒ����
 */
public interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}
