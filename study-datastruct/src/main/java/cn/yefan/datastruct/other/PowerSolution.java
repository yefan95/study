package cn.yefan.datastruct.other;

/**
 * <p>
 * 数值的整数次方
 * <p>
 * <p>
 * 题目描述
 * 给定一个double类型的浮点数base和int类型的整数exponent。
 * 求base的exponent次方。
 * <p>
 *
 * @author yefan
 * @date 2018/01/02
 */
public class PowerSolution {

    public double Power(double base, int exponent) {
        if (base == 0 && exponent < 0) {
            return 0;
        }
        if (exponent == 0) {
            return 1;
        }
        int n = Math.abs(exponent);
        double result = 1.0;

        for (int i = 1; i <= n; i++) {
            result *= base;
        }
        if (exponent < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    public double power(double base, int exponent) {
        if (exponent == 0) {
            return 0;
        }
        if (exponent == 1) {
            return base;
        }

        double result = power(base, exponent >> 1);

        result *= result;

        if ((exponent & 0x1) == 1) {
            System.out.println("=======");
            result *= base;
        }

        return result;
    }

    public static void main(String[] args) {
        PowerSolution solution = new PowerSolution();
        double result;
//        result = solution.power(2, 6);
//        System.out.println("result= " + result);
        result = solution.power(2, 7);
        System.out.println("result= " + result);
    }


}
