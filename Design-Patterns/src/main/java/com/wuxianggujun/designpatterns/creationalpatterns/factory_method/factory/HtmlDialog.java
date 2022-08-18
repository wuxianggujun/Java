package com.wuxianggujun.designpatterns.creationalpatterns.factory_method.factory;

import com.wuxianggujun.designpatterns.creationalpatterns.factory_method.buttons.Button;
import com.wuxianggujun.designpatterns.creationalpatterns.factory_method.buttons.HtmlButton;

public class HtmlDialog extends Dialog{
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
