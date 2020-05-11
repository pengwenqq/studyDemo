package studyJavaDemo.JMH;

import cn.pw.studyJavaDemo.JMH.MyJMHPS;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
//Scope有三种：
//    Scope.Thread：默认的State，每个测试线程分配一个实例；
//    Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
//    Scope.Group：每个线程组共享一个实例；
@State(Scope.Benchmark)
public class MyJMHTest {
    //测试哪一段代码
    @Benchmark

    //预热，iterations表示预热过程中的迭代个数，time为每个迭代执行多少次
    @Warmup(iterations = 1 , time = 3)

    //value表示该benchMark执行多少次，warmups表示fork多少个进程来执行。
    @Fork(value = 1, warmups = 2)

    //指定时间单位
    @OutputTimeUnit(TimeUnit.SECONDS)

    //基准测试的模式(Mode.Throughput:给定时间测试运行次数,
    // Mode.AverageTime:调用的平均时间,
    // Mode.SampleTime:随机取样，最后输出取样结果的分布,
    // Mode.SingleShotTime:只会执行一次，通常用来测试冷启动时候的性能,
    // Mode.All:所有的benchmark mode)
    @BenchmarkMode(Mode.All )

    //方法迭代个数以及运行次数
    @Measurement(iterations = 1 , time = 3)

    public void JMHTestForEach(){
        MyJMHPS.foreach();
    }
    @Benchmark
    public void JMHTestParallel(){
        MyJMHPS.parallel();
    }

}
