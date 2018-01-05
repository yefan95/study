package cn.yefan.thread.atomic;

import cn.yefan.thread.atomic.bean.Performance;
import cn.yefan.thread.atomic.bean.Student;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class TestAtomicReferenceFieldUpdater {

    public static void main(String[] args) {

        Student student = new Student("admin");
        Performance newPerformance = new Performance();
        newPerformance.setPerformance(80);
        // 注意，这样student中的performance属性
        // 就是用了乐观机制，保证了操作的线程安全性
        student.setPerformance(newPerformance);

        // 再设置一次
        Performance otherPerformance = new Performance();
        otherPerformance.setPerformance(100);
        student.setPerformance(otherPerformance);
    }

}
