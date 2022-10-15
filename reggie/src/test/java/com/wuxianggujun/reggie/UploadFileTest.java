package com.wuxianggujun.reggie;

import org.junit.Test;

public class UploadFileTest {
    @Test
    public void test(){
        String fileName = "lalalla.jpg";
       String suffix= fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }
}
