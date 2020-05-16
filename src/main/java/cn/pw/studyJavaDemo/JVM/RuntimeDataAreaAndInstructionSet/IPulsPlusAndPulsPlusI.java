package cn.pw.studyJavaDemo.JVM.RuntimeDataAreaAndInstructionSet;

public class IPulsPlusAndPulsPlusI {
    public static void main(String[] args) {
        int i = 8;
        //i = i++;
        i = ++i;
        System.out.println(i);
    }
}
