package coding.test0602;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Auther: zls
 * @Date: 2022/6/2 09:31
 * @Description:
 */
public class MergeSort {

    public void sort(int arr[]) {
        mergeSort(arr, 0, arr.length);
    }

    private void mergeSort(int arr[], int a, int b) {
        if (b - a <= 1)
            return;

        int mid = (a + b) / 2;
        mergeSort(arr, a, mid);
        mergeSort(arr, mid, b);

        merge(arr, a, b, mid);
    }

    private void merge(int arr[], int a, int b, int mid) {
        int[] leftArr = Arrays.copyOfRange(arr, a, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid, b + 1);
        leftArr[leftArr.length - 1] = rightArr[rightArr.length - 1] = Integer.MAX_VALUE;

        int c = 0;
        int d = 0;
        for (int i = a;i < b;i ++) {
            if (leftArr[c] < rightArr[d]) {
                arr[i] = leftArr[c ++];
            } else {
                arr[i] = rightArr[d ++];
            }
        }
    }

    @Test
    public void test() {
        int arr[] = {100,2,6,3,1,4,5,9,7,8};
        sort(arr);
        for (int i: arr)
            System.out.print(i + " ");
    }

}
