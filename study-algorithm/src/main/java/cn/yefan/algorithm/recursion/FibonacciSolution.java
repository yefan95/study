package cn.yefan.algorithm.recursion;

/**
 * <p>
 * 斐波那契数列
 * <p>
 * <p>
 * 题目描述
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 * <p>
 *
 * @author yefan
 * @date 2017/12/31
 */
public class FibonacciSolution {

    public int Fibonacci(int n) {
        int[] result = {0, 1};
        if (n < 2) {
            return result[n];
        }
        int fibNOne = 1;
        int fibNTwo = 0;
        int fibN = 0;

        for (int i = 2; i <= n; i++) {
            fibN = fibNOne + fibNTwo;
            fibNTwo = fibNOne;
            fibNOne = fibN;
        }
        return fibN;
    }


}
