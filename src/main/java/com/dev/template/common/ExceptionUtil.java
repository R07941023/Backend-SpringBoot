package com.dev.template.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

public class ExceptionUtil {

    private ExceptionUtil(){
    }

    public static String getStackTracetring(Throwable e){
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(byteArrayInputStream);
        e.printStackTrace(printWriter);
        printWriter.close();
        return byteArrayInputStream.toString();
    }
}
