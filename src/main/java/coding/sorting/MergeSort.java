package coding.sorting;

import java.util.Arrays;

public class MergeSort implements IMutableSorter {
    @Override
    public void sort(int[] A) {
        mergeSort(A, 0, A.length);
    }

    private void mergeSort(int[] A, int l, int r) {

        // stack overflow
        if(r - l <= 1) {
            return;
        }

        int mid = (l + r + 1) / 2;
        mergeSort(A, l, mid);
        mergeSort(A, mid, r);

        merge1(A, l, mid, r);
    }

    private void merge(int[] A, int l, int mid, int r) {
        int[] B = Arrays.copyOfRange(A, l, mid + 1);
        int[] C = Arrays.copyOfRange(A, mid, r + 1);


        B[B.length - 1] = C[C.length - 1] = Integer.MAX_VALUE;

        int i = 0, j = 0;

        for(int k = l; k < r; k ++) {
            if(B[i] < C[j]) {
                A[k] = B[i ++];
            } else {
                A[k] = C[j ++];
            }
        }

    }

    public static void merge1(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right + 1);

        leftArr[leftArr.length - 1] = rightArr[rightArr.length - 1] = Integer.MAX_VALUE;

        int i = 0, j = 0;

        while (left < right) {
            if (leftArr[i] < rightArr[j])
                arr[left] = leftArr[i ++];
            else
                arr[left] = rightArr[j ++];
            left ++;
        }
//        for(int k = left; k < right; k ++) {
//            if(leftArr[i] < rightArr[j]) {
//                arr[k] = leftArr[i ++];
//            } else {
//                arr[k] = rightArr[j ++];
//            }
//        }

    }

}
