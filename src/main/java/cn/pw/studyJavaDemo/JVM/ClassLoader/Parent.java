package cn.pw.studyJavaDemo.JVM.ClassLoader;

public class Parent {
    private static PWClassLoader parent = new PWClassLoader();

    private static class MyLoader extends ClassLoader {
        public MyLoader() {
            super(parent);
        }
    }
}
