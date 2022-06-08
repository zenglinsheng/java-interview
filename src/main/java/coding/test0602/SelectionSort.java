package coding.test0602;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/2 09:13
 * @Description:
 */
public class SelectionSort {

    public void sort(int arr[]) {
        selectionSort(arr);
    }

    private void selectionSort(int arr[]) {
        for (int i = arr.length - 1;i >= 0;i --) {
            int cur = arr[i];
            int maxIndex = i;
            for (int j = i;j >= 0;j --) {
                if (cur < arr[j]) {
                    cur = arr[j];
                    maxIndex = j;
                }
            }
            swap(arr, i, maxIndex);
        }

    }

    private void swap(int arr[], int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    @Test
    public void test() {
        int arr[] = {100,2,6,3,1,4,5,9,7,8};
        sort(arr);
        for (int i: arr)
            System.out.print(i + " ");
    }

}
