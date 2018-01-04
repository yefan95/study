package cn.yefan.algorithm.sort;

/**
 * 快速排序
 * <p>
 * <p>
 * 快速排序
 * </p>
 *
 * @author yefan
 * @date 2017/12/30
 */
public class QuickSortSolution {

    public void quickSort(int[] array, int lo, int hi) {
        if (lo > hi) {
            return;
        }
        int index = partition(array, lo, hi);
        quickSort(array, lo, index - 1);
        quickSort(array, index + 1, hi);
    }

    private int partition(int[] array, int lo, int hi) {
        int key = array[lo];

        while (lo < hi) {
            //从后半部分向前扫描
            while (array[hi] >= key && hi > lo) {
                hi--;
            }
            array[lo] = array[hi];
            //从前半部分向后扫描
            while (array[lo] <= key && hi > lo) {
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void main(String[] args) {
        QuickSortSolution solution = new QuickSortSolution();
        int[] array = {6, 3, 7, 4, 1};
        solution.quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

}
