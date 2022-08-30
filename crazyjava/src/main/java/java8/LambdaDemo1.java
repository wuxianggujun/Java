package java8;

public class LambdaDemo1 {
    interface Printer {
        void printer(String message);
    }

    public void pringSomething(String something, Printer printer) {
        printer.printer(something);
    }

    public static void main(String[] args) {
        LambdaDemo1 lambdaDemo1 = new LambdaDemo1();
        String some = "Wuxianggujun";
//
//        Printer printer = new Printer() {
//            @Override
//            public void printer(String message) {
//                System.out.println(message);
//            }
//        };

        lambdaDemo1.pringSomething(some, value ->
                System.out.println(value));
    }
}
