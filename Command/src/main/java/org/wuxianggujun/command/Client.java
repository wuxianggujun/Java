package org.wuxianggujun.command;

public class Client {
    public static void main(String[] args) {
        //创建接收者
        org.wuxianggujun.command.Receiver receiver = new org.wuxianggujun.command.Receiver();
        //创建命令对象，设定其接受者
        org.wuxianggujun.command.Command command = new org.wuxianggujun.command.ConcreteCommand(receiver);
        //创建请求者，把命令对象设置进去
        org.wuxianggujun.command.Invoker invoker = new org.wuxianggujun.command.Invoker(command);
        //执行方法
        invoker.action();

    }
}
