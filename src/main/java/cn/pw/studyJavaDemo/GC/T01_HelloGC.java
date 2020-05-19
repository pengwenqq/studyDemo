package cn.pw.studyJavaDemo.GC;

//-XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -XX:+PrintFlagsFinal -XX:+PrintVMOptions -
public class T01_HelloGC {
    public static void main(String[] args) {

        for(int i=0; i<10000; i++) {
            byte[] b = new byte[1024 * 1024];
        }
        System.out.println("Hello GC");
    }
}
