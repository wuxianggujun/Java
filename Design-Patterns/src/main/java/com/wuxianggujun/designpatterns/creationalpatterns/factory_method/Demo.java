package com.wuxianggujun.designpatterns.creationalpatterns.factory_method;

import com.wuxianggujun.designpatterns.creationalpatterns.factory_method.factory.Dialog;
import com.wuxianggujun.designpatterns.creationalpatterns.factory_method.factory.HtmlDialog;
import com.wuxianggujun.designpatterns.creationalpatterns.factory_method.factory.WindowsDialog;

public class Demo {
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
    }

    /**
     * The concrete factory is usually chosen depending on configuration or
     * environment options.
     */
    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }
}
