package cn.yefan.thread.pool.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class MergeSort {

    private static int MAX = 100;

    private static int[] inits = new int[MAX];

    static {
        Random r = new Random();
        for (int index = 1; index <= MAX; index++) {
            inits[index - 1] = r.nextInt(MAX);
        }
    }

    static class MergeSortTask extends RecursiveTask<int[]> {

        private int[] source;

        public MergeSortTask(int[] source) {
            this.source = source;
        }

        @Override
        protected int[] compute() {
            int sourceLen = source.length;
            if (sourceLen > 2) {
                int minIndex = sourceLen / 2;
                MergeSortTask task1 = new MergeSortTask(Arrays.copyOf(source, minIndex));
                task1.fork();
                MergeSortTask task2 = new MergeSortTask(Arrays.copyOfRange(source, minIndex, sourceLen));
                task2.fork();

                int[] result1 = task1.join();
                int[] result2 = task2.join();
                int[] merge = joinInts(result1, result2);

                return merge;
            } else {
                if (sourceLen == 1 || source[0] < source[1]) {
                    return source;
                } else {
                    int[] tmp = new int[sourceLen];
                    tmp[0] = source[1];
                    tmp[1] = source[0];
                    return tmp;
                }
            }
        }

        private int[] joinInts(int[] array1, int[] array2) {
            int[] destInts = new int[array1.length + array2.length];
            int array1Len = array1.length;
            int array2Len = array2.length;
            int destLen = destInts.length;

            // 只需要以新的集合destInts的长度为标准，遍历一次即可
            for (int index = 0, array1Index = 0, array2Index = 0; index < destLen; index++) {
                int value1 = array1Index >= array1Len ? Integer.MAX_VALUE : array1[array1Index];
                int value2 = array2Index >= array2Len ? Integer.MAX_VALUE : array2[array2Index];
                // 如果条件成立，说明应该取数组array1中的值
                if (value1 < value2) {
                    array1Index++;
                    destInts[index] = value1;
                }
                // 否则取数组array2中的值
                else {
                    array2Index++;
                    destInts[index] = value2;
                }
            }

            return destInts;
        }
    }

    public static void main(String[] args) {
        long beginTime = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<int[]> taskFuture = pool.submit(new MergeSortTask(inits));
        int[] results = null;
        try {
            results = taskFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("耗时=" + (endTime - beginTime) + "ms | " + Arrays.toString(results));
    }

}
