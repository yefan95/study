package cn.yefan.datastruct.character;


import java.util.ArrayList;

/**
 * 打印1到最大的n位数
 *
 * @author
 * @date 2018/01/03
 */
public class PrintOneToMaxOfDigitSolution {

    public void print1ToMaxOfDigits(int n) {
        ArrayList<int[]> list = new ArrayList<>();
        int[] answer = new int[n];
        int[] num = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < n; i++) {
            // 生成二维数组
            list.add(num);
        }
        recursive(0, n, answer, list);
    }

    public void recursive(int index, int n, int[] answer, ArrayList<int[]> list) {
        if (index == n) {
            boolean beginWith0 = true;
            for (int i = 0; i < n; i++) {
                if (list.get(i)[answer[i]] != 0) {
                    beginWith0 = false;
                }
                if (!beginWith0) {
                    System.out.printf("%d", list.get(i)[answer[i]]);
                }
            }
            if (!beginWith0) {
                System.out.println();
            }
            return;
        }

        for (answer[index] = 0; answer[index] < 10; answer[index]++) {
            recursive(index + 1, n, answer, list);
        }

    }


    /**
     * @param n
     */
    void printOneToMaxOfDigits(int n) {
        if (n <= 0) {
            return;
        }
        int[] number = new int[n];

        while (!increment(number)) {
            printNumber(number);
        }

    }

    /**
     * 使用数组实现对数进行+1操作
     *
     * @param number
     * @return
     */
    public boolean increment(int[] number) {
        if (number.length < 1) {
            throw new RuntimeException("invalid length of array");
        }
        //最高位产生进位标志，则数组中的数为最大的n位整数
        boolean isOverFlow = false;
        //进位位
        int carry = 0;
        //没有产生进位的+1，循环只运行1次，产生一个进位，循环多运行一次
        for (int i = number.length - 1; i >= 0; i--) {
            int sum = number[i] + carry;
            if (i == number.length - 1) {
                sum++;//最低位+1
            }
            if (sum >= 10) {
                //最高位产生进位
                if (i == 0) {
                    isOverFlow = true;
                }
                //普通位产生进位
                else {
                    carry = 1;
                    number[i] = 0;
                    sum = 0;
                }
            } else {
                //普通位+1的结果保存到数组中，+1后程序退出循环
                number[i] = sum;
                break;
            }
        }
        return isOverFlow;
    }

    /**
     * 输出数组中的元素
     *
     * @param number
     */
    void printNumber(int[] number) {
        boolean flag = false;
        String result = "";
        for (int i = 0; i < number.length; i++) {
            if (number[i] != 0) {
                flag = true;
            }
            if (flag) {
                result += number[i];
            }

        }
        System.out.println(result);
    }


    public static void main(String[] args) {
        PrintOneToMaxOfDigitSolution solution = new PrintOneToMaxOfDigitSolution();
//        solution.printOneToMaxOfDigits(4);
        solution.print1ToMaxOfDigits(4);
    }

}
