package cn.yefan.datastruct.array;

/**
 * <p>
 * 旋转数组的最小数字
 * <p>
 * <p>
 * 题目描述
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 *
 * @author yefan
 * @date 2017/12/30
 */
public class RotateArraySolution {

    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length <= 0) {
            return 0;
        }
        int start = 0;
        int end = array.length - 1;
        int mid = start;

        while (array[start] >= array[end]) {
            if (end - start == 1) {
                mid = end;
                break;
            }
            mid = (start + end) / 2;

            if (array[start] == array[mid] && array[mid] == array[end]) {
                return MinInOrder(array, start, end);
            }
            if (array[mid] >= array[start]) {
                start = mid;
            } else if (array[mid] <= array[end]) {
                end = mid;
            }
        }
        return array[mid];
    }

    private int MinInOrder(int[] array, int start, int end) {
        int result = array[start];

        for (int i = start + 1; i <= end; i++) {
            if (result > array[i]) {
                result = array[i];
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        int[] array1 = {1, 2, 3, 4, 5};
    }


}
