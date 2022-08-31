package org.wuxianggujun.command;

public class ConcreteCommand implements org.wuxianggujun.command.Command {
    /**
     * 持有相应的接受者对象
     */
    private org.wuxianggujun.command.Receiver receiver = null;

    public ConcreteCommand(org.wuxianggujun.command.Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        //通常会转调接收者的形影方法，让接收者来真正执行能力
        receiver.action();
    }
}
