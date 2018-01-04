package cn.yefan.algorithm.recursion;

/**
 * 打印1到最大的n位数
 *
 * @author
 * @date 2018/01/03
 */
public class Print1ToMaxN {

    public static void main(String[] args) {
        int n = 3;
        print1ToMaxN(n);
    }

    private static void print1ToMaxN(int n) {
        char number[] = new char[n];
        recursivePrint(0, number, n);
    }

    private static void recursivePrint(int index, char[] number, int n) {
        // TODO Auto-generated method stub
        if (index == n) {
            boolean beginWith0 = true;

            for (int i = 0; i < n; i++) {

                if (number[i] != '0') {
                    beginWith0 = false;
                }
                if (!beginWith0) {
                    System.out.print(number[i]);

                }
            }

            if (!beginWith0) {
                System.out.println();

            }

            return;
        }

        for (int i = 0; i < 10; i++) {
            number[index] = (char) ('0' + i);
            recursivePrint(index + 1, number, n);
        }

    }

}
