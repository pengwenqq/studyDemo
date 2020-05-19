package cn.pw.studyJavaDemo.GC;

import java.util.LinkedList;
import java.util.List;

public class Hello_Old {
        public static void main(String[] args) {
            System.out.println("HelloGC!");
            List list = new LinkedList();
            for(;;) {
                byte[] b = new byte[1024*1024];
                list.add(b);
            }
        }
    }
