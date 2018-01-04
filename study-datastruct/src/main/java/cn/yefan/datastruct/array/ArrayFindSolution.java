package cn.yefan.datastruct.array;

/**
 * <p>
 * 二维数组中的查找
 * <p>
 * <p>
 * 题目描述
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * <p>
 *
 * @author yefan
 * @date 2017/12/28
 */
public class ArrayFindSolution {

    public boolean Find(int target, int[][] array) {
        boolean flag = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean find(int target, int[][] array) {
        boolean find = false;
        int rows = array.length;
        //行
        int row = 0;
        //列
        int col = array[0].length - 1;

        while (col >= 0 && row < rows) {
            if (array[row][col] == target) {
                find = true;
                break;
            } else if (array[row][col] > target) {
                --col;
            } else {
                ++row;
            }
        }
        return find;
    }

    public static void main(String[] args) {
        ArrayFindSolution solution = new ArrayFindSolution();

    }

}


