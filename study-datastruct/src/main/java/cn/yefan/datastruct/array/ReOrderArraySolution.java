package cn.yefan.datastruct.array;


/**
 * <p>
 * 调整数值顺序使奇数位于偶数前面
 * <p>
 * <p>
 * 题目描述
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * <p>
 *
 * @author yefan
 * @date 2018/01/02
 */
public class ReOrderArraySolution {

    public void reOrderArray(int[] array) {
        if (array == null || array.length <= 0) {
            return;
        }
        int[] tmp = array.clone();
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (tmp[i] % 2 != 0) {
                array[index] = tmp[i];
                index++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (tmp[i] % 2 == 0) {
                array[index] = tmp[i];
                index++;
            }
        }
    }

    public void reOrderArray1(int[] array) {
        if (array == null || array.length <= 0) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] % 2 == 0 && array[j + 1] % 2 == 1) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

}
