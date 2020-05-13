package cn.pw.studyJavaDemo.JVM.ClassLoader;

public class ParentAndChild {
    public static void main(String[] args) {
        System.out.println(ParentAndChild.class.getClassLoader());
        System.out.println(ParentAndChild.class.getClassLoader().getClass().getClassLoader());
        System.out.println(ParentAndChild.class.getClassLoader().getParent());
        System.out.println(ParentAndChild.class.getClassLoader().getParent().getParent());
        //System.out.println(T004_ParentAndChild.class.getClassLoader().getParent().getParent().getParent());

    }
}
