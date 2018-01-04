package cn.yefan.datastruct.binary;

/**
 * <p>
 * 二进制中1的个数
 * <p>
 * <p>
 * 题目描述
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * <p>
 *
 * @author yefan
 * @date 2018/01/01
 */
public class NumberOfOne {

    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            ++count;
            n = (n - 1) & n;
        }
        return count;
    }

    public int NumberOfOne(int n) {
        int count = 0;
        int flag = 1;
        while (flag <= n) {
            if ((n & flag) == 1) {
                count++;
            }
            flag = flag << 1;
        }
        return count;
    }

}
