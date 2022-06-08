package coding.test0602;

/**
 * @Auther: zls
 * @Date: 2022/6/8 14:26
 * @Description:
 */
public class BinarySearch {

    public int binarySearch(int arr[], int val) {
        if (arr.length == 0)
            throw new ArithmeticException("The array is empty.");

        return binarySearch(arr, 0, arr.length, val);
    }

    private int binarySearch(int arr[], int l, int r, int val) {
        int mid = l + (r - l - 1) / 2;

        while (r - l >= 1) {
            if(val == arr[mid]) {
                return mid;
            } else if (val < arr[mid]) {
                r = mid;
            } else {
                l = mid + 1;
            }
            mid = l + (r - l - 1) / 2;
        }

        return -1;
    }

    public int binarySearch2(int arr[], int val) {
        if (arr.length == 0)
            throw new ArithmeticException("The array is empty.");

        return binarySearch2(arr, 0, arr.length, val);
    }

    private int binarySearch2(int arr[], int l, int r, int val) {
        if (r - l <= 0)
            return -1;

        int mid = l + (r - l - 1) / 2;

        if (val == arr[mid])
            return mid;
        else if (val < arr[mid])
            return binarySearch2(arr, l, mid, val);
        else
            return binarySearch2(arr, mid + 1, r, val);
    }

    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(new BinarySearch().binarySearch(arr, 2));
        System.out.println(new BinarySearch().binarySearch2(arr, 80));
    }

}
