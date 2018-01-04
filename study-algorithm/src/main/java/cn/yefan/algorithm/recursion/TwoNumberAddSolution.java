package cn.yefan.algorithm.recursion;

/**
 * 实现两个整数的任意加法
 *
 * @author yefan
 * @date 2018/01/04
 */
public class TwoNumberAddSolution {

    public void add(int[] a, int[] b) {
        if (a == null || b == null || a.length <= 0 || b.length <= 0) {
            return;
        }

        int[] tmp = a;
        if (a.length < b.length) {
            a = b;
            b = tmp;
        }
        int lengthA = a.length;
        int lengthB = b.length;
        int count = lengthA > lengthB ? lengthA : lengthB;
        int min = lengthA > lengthB ? lengthB : lengthA;

        int carry = 0;

        for (int i = count - 1; i >= 0; i--) {
            int sum = 0;
            if (min <= 0) {
                sum = a[i] + carry;
                carry = 0;
            } else {
                sum = a[i] + b[min - 1] + carry;
                carry = 0;
            }
            if (sum >= 10) {
                if (i == 0) {
                    a[i] = sum;
                } else {
                    carry = 1;
                    a[i] = sum % 10;
                }
            } else {
                a[i] = sum;
                sum = 0;
            }
            min--;
        }

        printResult(a);

    }

    public void printResult(int[] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%d ", result[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 6};
        int[] b = {3, 4, 5};
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i] + "");
        }
        TwoNumberAddSolution solution = new TwoNumberAddSolution();
        solution.add(b, a);
    }


}
