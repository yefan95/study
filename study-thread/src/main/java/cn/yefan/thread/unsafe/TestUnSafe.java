package cn.yefan.thread.unsafe;

import cn.yefan.thread.unsafe.bean.User;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class TestUnSafe {

    public static void main(String[] args) {
        Field field;
        Unsafe unsafe = null;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            //获取Unsafe类的实例
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        User user = new User();
        user.setUserName("admin");
        user.setPassWord("1234");
        user.setAge(29);

        Field userName = null;
        Field passWord = null;
        Field age = null;
        try {
            userName = User.class.getDeclaredField("userName");
            passWord = User.class.getDeclaredField("passWord");
            age = User.class.getDeclaredField("age");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        userName.setAccessible(true);
        passWord.setAccessible(true);
        age.setAccessible(true);

        long userNameOffset = unsafe.objectFieldOffset(userName);
        long passWordOffset = unsafe.objectFieldOffset(passWord);
        long ageOffset = unsafe.objectFieldOffset(age);


        System.out.println("userName offset = " + userNameOffset);
        System.out.println("passWord offset = " + passWordOffset);
        System.out.println("age offset = " + ageOffset);


        boolean flag = unsafe.compareAndSwapObject(user, userNameOffset, user.getUserName(), "张三");

        if (flag) {
            System.out.println("更改成功");
            System.out.println(user.toString());
        } else {
            System.out.println("更改失败");
            System.out.println(user.toString());
        }

        System.out.println("=========================================================");

        unsafe.putInt(user, ageOffset, 22);
        System.out.println(user.toString());

    }

}
