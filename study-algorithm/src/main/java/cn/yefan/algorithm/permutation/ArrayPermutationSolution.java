package cn.yefan.algorithm.permutation;

/**
 * 数组的全排列问题
 *
 * @author
 * @date 2018/01/04
 */
public class ArrayPermutationSolution {

    /**
     * @param array
     * @param k
     * @param m
     */
    public void perm(int[] array, int k, int m) {
        if (k == m - 1) {
            for (int i = 0; i < m; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println("\t");
        } else {
            for (int i = k; i < m; i++) {
                swap(array, k, i);
                perm(array, k + 1, m);
                swap(array, k, i);
            }
        }
    }

    private void swap(int[] array, int s, int i) {
        int t = array[s];
        array[s] = array[i];
        array[i] = t;
    }

    public static void main(String[] args) {
        ArrayPermutationSolution solution = new ArrayPermutationSolution();
        int[] array = {1, 3, 4};
        solution.perm(array, 0, 3);
    }


}
