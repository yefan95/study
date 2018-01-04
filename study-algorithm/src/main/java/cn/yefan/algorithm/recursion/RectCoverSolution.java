package cn.yefan.algorithm.recursion;

/**
 * <p>
 * 矩形覆盖问题
 * <p>
 * <p>
 * 题目描述
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 * <p>
 *
 * @author yefan
 * @date 2017/12/31
 */
public class RectCoverSolution {

    public int RectCover(int target) {

        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
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

    public int RectCover1(int target) {

        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return RectCover1(target - 1) + RectCover1(target - 2);
        }

    }


}
