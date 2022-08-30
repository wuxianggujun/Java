package org.wuxianggujun.command;

public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;

    public Invoker(Command command) {
        this.command = command;
    }
    
    public void action() {
        command.execute();
    }
}
