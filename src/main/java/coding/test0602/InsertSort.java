package coding.test0602;

import org.junit.Test;

/**
 * @Auther: zls
 * @Date: 2022/6/2 08:47
 * @Description:
 */
public class InsertSort {

    public void sort(int arr[]) {
        insertSort(arr);
    }

    private void insertSort(int arr[]) {
        for (int i = 1;i < arr.length;i ++) {
            int cur = arr[i];
            int j = i;
            while (j > 0 && cur < arr[j - 1])
                arr[j] = arr[-- j];

            arr[j] = cur;
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
