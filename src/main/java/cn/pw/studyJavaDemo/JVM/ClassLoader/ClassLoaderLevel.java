package cn.pw.studyJavaDemo.JVM.ClassLoader;

public class ClassLoaderLevel {
    public static void main(String[] args) {
        //BootstrapClassLoader
        System.out.println(String.class.getClassLoader());
        //BootstrapClassLoader
        System.out.println(sun.awt.HKSCS.class.getClassLoader());
        //ExtClassLoader
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());
        //AppClassLoader
        System.out.println(ClassLoaderLevel.class.getClassLoader());


        System.out.println(ClassLoader.getSystemClassLoader());
    }

}
