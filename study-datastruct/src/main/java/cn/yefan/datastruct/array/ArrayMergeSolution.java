package cn.yefan.datastruct.array;

/**
 * 数组合并问题
 *
 * @author yefan
 * @date 2017/12/28
 */
public class ArrayMergeSolution {


    public int[] merge(int[] a, int[] b, int available) {
        if (a.length <= 0 || b.length <= 0 || available < b.length) {
            return null;
        }
        int last = a.length - available - 1;
        System.out.println("last " + last);
        int indexOfb = b.length - 1;
        int indexOfa = a.length - available + b.length - 1;
        System.out.println("indexOfa " + indexOfa);

        while (indexOfb >= 0 && indexOfa >= 0 && last >= 0) {
            if (a[last] > b[indexOfb]) {

                System.out.println("+++++" + b[indexOfb]);
                System.out.println("+++++" + a[last]);
                System.out.println("=====" + indexOfa);
                a[indexOfa] = a[last];
                if (indexOfa == 1) {
                    a[--indexOfa] = b[indexOfb];
                }
                --last;
            } else if (a[last] < b[indexOfb]) {
                a[indexOfa] = b[indexOfb];
                System.out.println("-----" + b[indexOfb]);
                System.out.println("-----" + a[last]);
                --indexOfb;
            }
            --indexOfa;
        }
        return a;
    }

    public static void main(String[] args) {
        ArrayMergeSolution solution = new ArrayMergeSolution();
        int[] a = {0, 4, 6, 8, 10, -1, -1, -1, -1};
        int[] b = {1, 3, 5};
        int[] result = solution.merge(a, b, 4);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
