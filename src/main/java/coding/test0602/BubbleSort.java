package coding.test0602;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/2 09:24
 * @Description:
 */
public class BubbleSort {

    public void sort(int arr[]) {
        bubbleSort(arr);
    }

    private void bubbleSort(int arr[]) {
        for (int i = arr.length - 1;i >= 0;i --) {
            for (int j = 0;j < i;j ++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void swap(int arr[], int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    @Test
    public void test() {
        int arr[] = {100,2,6,3,1,4,5,9,7,8,-1,11};
        sort(arr);
        for (int i: arr)
            System.out.print(i + " ");
    }

}
