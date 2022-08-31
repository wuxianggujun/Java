package org.wuxianggujun.command;

public class Invoker {
    /**
     * 持有命令对象
     */
    private org.wuxianggujun.command.Command command = null;

    public Invoker(org.wuxianggujun.command.Command command) {
        this.command = command;
    }
    
    public void action() {
        command.execute();
    }
}
