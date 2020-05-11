package cn.pw.studyJavaDemo.JMH;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyJMHPS {
    //定义一个Integer类型的集合
    static List<Integer> nums = new ArrayList<>();
    //往集合中添加1万个10万-20万之间的整数
    static {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) nums.add(1000000 + r.nextInt(1000000));
    }

    //普通增强for循环遍历集合
    public static void foreach() {
        nums.forEach(v->isPrime(v));
    }

    //并行流式编程遍历集合
    public static void parallel() {
        nums.parallelStream().forEach(MyJMHPS::isPrime);
    }

    //判断一个数是不是质数的方法
    public static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

}
