package cn.yefan.algorithm.recursion;

/**
 * <p>
 * 跳台阶
 * <p>
 * <p>
 * 题目描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * <p>
 * 题目描述
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * <p>
 *
 * @author yefan
 * @date 2017/12/31
 */
public class JumpFloorSolution {

    public int JumpFloor(int target) {
        int[] result = {1, 2};
        if (target <= 2) {
            return result[target - 1];
        }
        int fibNOne = 1;
        int fibNTwo = 2;
        int fibN = 0;
        for (int i = 3; i <= target; i++) {
            fibN = fibNOne + fibNTwo;
            fibNOne = fibNTwo;
            fibNTwo = fibN;
        }
        return fibN;
    }

    public int JumpFloorII(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int fibNTwo = 2;
        int fibN = 1;
        for (int i = 3; i <= target; i++) {
            fibN = 2 * fibNTwo;
            fibNTwo = fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {

    }

}
