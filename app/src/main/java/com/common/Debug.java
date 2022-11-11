package com.common;

import java.io.*;

public class Debug {
	static void log(StackTraceElement logs[] ) { 
		 
        for (int i = 0; i < logs.length; i++) 
        {
            
             File f = new File(logs[i].getClassName()+".java");
             
             try {
            System.err.println("FileAddr  : "+ f.getCanonicalPath());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            
            System.err.println("ClassName : " + logs[i].getClassName()); 
            System.err.println("MethodName: " + logs[i].getMethodName()); 
            System.err.println("LineNumber: " + logs[i].getLineNumber()+"Line"); 
            System.err.println("FileName  : " + logs[i].getFileName()); 
        } 
    }	
}
