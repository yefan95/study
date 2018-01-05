package cn.yefan.thread.atomic.bean;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Student Entity
 *
 * @author yefan
 * @date 2018/01/05
 */
public class Student {

    private volatile Performance performance;

    private AtomicReferenceFieldUpdater<Student, Performance> performance_update = AtomicReferenceFieldUpdater.newUpdater(Student.class, Performance.class, "performance");


    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, Integer performance) {
        this.name = name;
        this.performance = new Performance();
        this.performance.setPerformance(performance);
    }


    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        performance_update.set(this, performance);
//        this.performance = performance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
