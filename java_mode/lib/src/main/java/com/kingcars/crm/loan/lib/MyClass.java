package com.kingcars.crm.loan.lib;

public class MyClass {


    public static  int[] twoSum(int[] nums, int target) {
        Integer[] filter = new Integer[4096];
        int length = nums.length;
        int diff = 0;
        for(int i=0;i<length;i++){
            diff = target - nums[i];
            Integer index = filter[diff&4095];
            if(index != null){
                return new int[]{index,i};
            }
            filter[nums[i]&4095] = i;
        }
        return null;
    }


    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    //    public main
    public static void main(String[] args) {

        int[] nums = new int[]{2, 8,78,7, 11, 15};
        int target = 9;
        int[] ss = twoSum(nums,target);
        for (int s : ss) {
            System.out.println(s);
        }
        System.out.println(reverse(-2147483647));

    }
}
