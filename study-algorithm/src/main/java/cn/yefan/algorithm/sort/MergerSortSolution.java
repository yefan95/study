package cn.yefan.algorithm.sort;


/**
 * 归并排序
 *
 * @author yefan
 * @date 2018/01/05
 */
public class MergerSortSolution {

    public void merge(int[] data) {
        sort(data, 0, data.length - 1);
    }

    public void sort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        //找出中间索引
        int mid = (left + right) / 2;
        //对左边数组进行递归
        sort(data, left, mid);
        //对右边数组进行递归
        sort(data, mid + 1, right);
        //合并
        merge(data, left, mid, right);
        printData(data);
    }

    /**
     * @param data   数组对象
     * @param left   左数组的第一个元素的索引
     * @param center 左数组的最后一个元素的索引，center+1是右数组第一个元素的索引
     * @param right  右数组最后一个元素的索引
     */
    private void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[right - left + 1];
        //右数组第一个元素的索引
        int mid = center + 1;
        // 记录临时数组的索引
        int index = 0;
        //缓存左数组第一个元素的索引
        int indexOfLeft = left;

        while (left <= center && mid <= right) {
            //从两个数组中取出最小的放入零时数组
            if (data[left] <= data[mid]) {
                tmpArr[index++] = data[left++];
            } else {
                tmpArr[index++] = data[mid++];
            }
        }
        //剩余部分依次放入临时数组(实际上只有一个while会执行)
        while (mid <= right) {
            tmpArr[index++] = data[mid++];
        }

        while (left <= center) {
            tmpArr[index++] = data[left++];
        }
        //将临时数字的内容拷贝回原数组(left-right范围的内容被赋值回原数组)
        for (int i = 0; i < tmpArr.length; i++) {
            data[indexOfLeft + i] = tmpArr[i];
        }
    }

    public void printData(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] data = {2, 1, 4, 3, 8, 5, 10, 9};
        MergerSortSolution solution = new MergerSortSolution();
        solution.merge(data);
        System.out.println("排序后的数组：");
        solution.printData(data);
    }

}
