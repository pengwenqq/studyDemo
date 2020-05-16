package cn.pw.studyJavaDemo.JVM.JMM;

public class TestSync {
    synchronized void m() {

    }

    void n() {
        synchronized (this) {

        }
    }

    public static void main(String[] args) {

    }
}
