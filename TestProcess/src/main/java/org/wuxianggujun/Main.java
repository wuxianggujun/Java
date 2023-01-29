package org.wuxianggujun;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author WuXiangGuJun
 * @create ${YEAR}-${MONTH}-${DAY} ${TIME}
 **/
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        File workDir = new File("tmp");
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            Process process = Runtime.getRuntime().exec("/bin/bash",null,workDir);

            PrintWriter printWriter = new PrintWriter(process.getOutputStream());
            printWriter.println(str);
            printWriter.close();

            //阻塞当前Java线程直到子进程执行完成
            process.waitFor();
            process.getInputStream().transferTo(System.out);
            process.getErrorStream().transferTo(System.out);

            //销毁子进程
            process.destroy();
        }

    }
}