package com.at.cache;

import java.lang.reflect.Array;

public class biSearch {

    public static int binSearch(int[] nums, int target){
        int low=0;
        int high =nums.length-1;
        while(low<=high){
            int mid =(low+high)/2;
            if(nums[mid]==target){
                System.out.println("找到目标"+ nums[mid]);
                return mid;
            }
            else if (nums[mid]>target){
                high = mid-1;
            }
            else if(nums[mid]<target){
                low=mid+1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int  array[]={1,2,3,4,12,14,16,19,22,40,43,50,66,77,85,97,99};
        int  barbary[]={12,23,32,41,12,1,13,19,20,44,43,55,6,77,65,97,99};
        binSearch(array,43);
        //bSort(barbary);
        insertSort(barbary);
    }

    private static void insertSort(int[]barbary) {
        int i,key;       //key值存储当前比较中最小的值
        for(int j=1;j<barbary.length;j++){
            key=barbary[j];
            i=j-1;                    //i=j-1表示从当前有序数组最后一个元素开始算
            while(i>=0&&barbary[i]>key){
                //当还未到达数组的起始并且当前元素大于要插入的数key,则i值与后一位进行交换,并且i值减小
                //这可以保证大于key的所有元素依次向末尾方向移动一位
                //保证，key到达的位置前面没有大于key的元素，后面也没有小于key
                //这就是插入排序算法的思想
                barbary[i+1]=barbary[i];
                i--;
            }
            barbary[i+1]=key;
        }
        for (int k=0;k<barbary.length;k++){
            System.out.print(barbary[k]+" ");
        }
    }
    private static void bSort(int[]barbary) {
        if(barbary==null)
        {
            return;
        }
        for (int i = 0; i <barbary.length ; i++) {
            for (int j = 0; j <barbary.length-1 ; j++) {
                if(barbary[j]>barbary[j+1]){
                    int tmp = barbary[j];
                    barbary[j]=barbary[j+1];
                    barbary[j+1]=tmp;
                }
            }
        }
        for (int i=0;i<barbary.length;i++){

            System.out.print(barbary[i]+" ");
        }
    }
}