package com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.factories;

import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.buttons.MacOSButton;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.Checkbox;
import com.wuxianggujun.designpatterns.creationalpatterns.abstract_factory.checkboxes.MacOSCheckbox;

/**
 * ÿ������Ĺ�����չ�����Ĺ��������𴴽�
 * ��һƷ�ֵĲ�Ʒ��
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
