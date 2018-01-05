package cn.yefan.algorithm.sort;

/**
 * 快速排序
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
        printData(array);
    }

    void printData(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
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
        int[] array = {2, 1, 4, 3, 8, 5, 10, 9};
        solution.quickSort(array, 0, array.length - 1);
        System.out.println("排序后的数组： ");
        solution.printData(array);
    }

}
