package coding.test0602;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/2 10:26
 * @Description:
 */
public class QuickSort {

    public void sort(int arr[]) {
        quickSort(arr, 0, arr.length);
    }

    private void quickSort(int arr[], int l, int r) {
        if (r - l <= 1)
            return;

        int mid = quick(arr, l, r);

        quickSort(arr, l, mid);
        quickSort(arr, mid + 1, r);
    }

    private int quick(int arr[], int l, int r) {
        int mid = arr[l];
        int left = l + 1;
        int right = r;

        while (left != right) {
            if (arr[left] < mid)
                left ++;
            else
                swap(arr, left, -- right);
        }
        swap(arr, l, left - 1);
        return left - 1;
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
