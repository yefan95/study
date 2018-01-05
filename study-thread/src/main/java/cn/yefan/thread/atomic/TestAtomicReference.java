package cn.yefan.thread.atomic;

import cn.yefan.thread.atomic.bean.Student;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class TestAtomicReference {

    public static void main(String[] args) {
        Student student = new Student("admin", 80);
        AtomicReference<Student> reference = new AtomicReference<Student>(student);

        student = new Student("admin", 70);
        Student oldStudent = reference.getAndSet(student);

        System.out.println("isEqual= " + (oldStudent.hashCode() == student.hashCode()));
    }

}
