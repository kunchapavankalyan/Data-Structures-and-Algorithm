import java.util.*;

public class ArrayProblems 
{
    /*
     * 🔹 Basics
        ✅ Traversal & Operations
        ✅ Insert/Delete in an Array
        ✅ Reverse an Array
        ✅ Find Min/Max in an Array

        🔹 Searching & Sorting
        ✅ Binary Search and Linear Search
        ✅ Bubble, Selection, Quick, Merge Sort
        ✅ Counting Sort (for large numbers)

        🔹 Two-Pointer & Sliding Window
        ✅ Two Sum Problem
        ✅ Find Pair with Given Sum
        ✅ Longest Subarray with Sum K
        ✅ Maximum Consecutive Ones

        🔹 Prefix Sum & Kadane's Algorithm
        ✅ Maximum Subarray Sum
        ✅ Subarray Sum Equals K
        ✅ Equilibrium Index

        🔹 Advanced: Hashing + Arrays
        ✅ Count Distinct Elements
        ✅ Largest Subarray with Zero Sum
        ✅ Longest Consequence Sequence

        🔹 Matrix & Special Problems
        ✅ Rotate Matrix (90 degrees)
        ✅ Spiral Traversal
        ✅ Search in a Sorted Matrix
    * 
    */
    // 1. Traversal & Operations
    public static void traverseArray(int[] arr) 
    {
        for (int num : arr) 
        {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // 2. Insert/Delete in an Array
    public static int[] insertElement(int[] arr, int element, int position) 
    {
        int[] newArr = new int[arr.length + 1];
        for (int i = 0, j = 0; i < newArr.length; i++) 
        {
            if (i == position) newArr[i] = element;
            else newArr[i] = arr[j++];
        }
        return newArr;
    }

    public static int[] deleteElement(int[] arr, int position) 
    {
        int[] newArr = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) 
        {
            if (i != position) newArr[j++] = arr[i];
        }
        return newArr;
    }

    // 3. Reverse an Array
    public static void reverseArray(int[] arr) 
    {
        int left = 0, right = arr.length - 1;
        while (left < right) 
        {
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
        //we can also use reverse for aswell
    }

    // 4. Find Min/Max in an Array
    public static int findMin(int[] arr) 
    {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]<min)
            {
                min=arr[i];
            }
        }
        return  min;
        //we can simply use return Arrays.stream(arr).min().getAsInt();
    }

    public static int findMax(int[] arr) 
    {
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]>max)
            {
                max=arr[i];
            }
        }
        return  max;
        //we can simply use return Arrays.stream(arr).max().getAsInt();
    }

    // 5. Binary Search
    public static int binarySearch(int[] arr, int target) 
    {
        int left = 0, right = arr.length - 1;
        while (left <= right) 
        {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    //linear Search
    public static int linearSearch(int[] arr,int target)
    {
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==target)
            {
                return i;
            }
        }
        return -1;
    }

    // 6. Sorting Algorithms
    public static void bubbleSort(int[] arr) 
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (arr[j] > arr[j + 1]) 
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selection_sort(int[] arr)
    {
        int n=arr.length;
        for(int i=0;i<n-1;i++)
        {
            int min=i;
            for(int j=i+1;j<n;j++)
            {
                if(arr[j]<arr[min]) 
                {
                    min=j;
                }
            }
            int temp=arr[i];
            arr[i]=arr[min];
            arr[min]=temp;
        }
    }

    public static void insertionsort(int[] arr)
    {
        for(int i=1;i<arr.length-1;i++)
        {
            int key=arr[i];
            int j=i-1;
            while(j>=0 && key<arr[j] )
            {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }

    public static void mergesort(int[] arr)
    {
        if(arr.length<2)
        {
            return ;
        }
        int mid=arr.length/2;
        int left[]=new int[mid];
        int right[]=new int[arr.length-mid];
        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length-mid);
        mergesort(left);
        mergesort(right);
        merge(arr,left,right);

    }
    public static void merge(int[] arr,int[] left,int[] right)
    {
        int k=0;
        int i=0;
        int j=0;
        while(i<left.length && j<right.length)
        {
            if(left[i]<=right[j])
            {
                arr[k++]=left[i++];
            }
            else
            {
                arr[k++]=right[j++];
            }
            
        }
        while(i<left.length)
        {
            arr[k++]=left[i++];
        }
        while(j<right.length)
        {
            arr[k++]=right[j++];
        }
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) 
        {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) 
    {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) 
        {
            if (arr[j] < pivot) 
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // 7. Kadane's Algorithm for Maximum Subarray Sum
    public static int maxSubarraySum(int[] arr) 
    {
        int maxSum = arr[0], currentSum = arr[0];
        for (int i = 1; i < arr.length; i++) 
        {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    // 8. Rotate Matrix by 90 degrees
    public static void rotateMatrix(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1; j < k; j++, k--) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][k];
                matrix[i][k] = temp;
            }
        }
    }

    // Main Function for Testing
    public static void main(String[] args) {
        int[] arr = {3, 1, 4, 1, 5, 9, 2};
        System.out.println("Original Array:");
        traverseArray(arr);

        arr = insertElement(arr, 7, 3);
        System.out.println("After Insertion of 7:");
        traverseArray(arr);

        arr = deleteElement(arr, 2);
        System.out.println("After Deletion in position 2:");
        traverseArray(arr);

        reverseArray(arr);
        System.out.println("Reversed Array:");
        traverseArray(arr);

        System.out.println("Minimum: " + findMin(arr));
        System.out.println("Maximum: " + findMax(arr));
        int[] t={1, 1, 2, 3, 5, 7, 9};
        //System.out.println(t);
        System.out.println("Find element 9 with Binary Search   "+binarySearch(t, 7));
        System.out.println("Find elment 9 with linear Search  "+linearSearch(t, 7));
        String sort="mergesort";
        switch(sort)
        {
            case "selection":
            {
                selection_sort(arr);
                System.out.println("Sorted with selection sort :");
                System.out.println(Arrays.toString(arr));
            }
            break;

            case "bubble":
            {
                bubbleSort(arr);
                System.out.println("Sorted with bubble sort :");
                System.out.println(Arrays.toString(arr));
            }
            break;

            case "insertion":
            {
                insertionsort(arr);
                System.out.println("Sorted with insersion sort :");
                System.out.println(Arrays.toString(arr));
            }
            break;

            case "mergesort":
            {
                mergesort(arr);
                System.out.println("Sorted with merge sort :");
                System.out.println(Arrays.toString(arr));
            }
            break;

            case "quicksort":
            {
                quickSort(arr, 0, arr.length);
                System.out.println("Sorted with merge sort :");
                System.out.println(Arrays.toString(arr));
            }
            break;
            
            default:
            {
                System.out.println("no proper option selected");
            }
        }
        
        
        //traverseArray(arr);

        System.out.println("Max Subarray Sum: " + maxSubarraySum(arr));
    }
}
