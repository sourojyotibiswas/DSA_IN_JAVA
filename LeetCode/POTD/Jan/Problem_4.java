import java.io.*;
import java.util.*;

public class Problem_4 {
    /** Not clean solution... */
    public static int sumFourDivisors(int[] nums) {
        int n = nums.length;
        int resSum = 0;
        
        for(int i = 0; i < n; i++) {
            int range = (int)Math.sqrt(nums[i]);
            if(range*range < nums[i]){
                int numOfDevisors = 2;
                int tempSumOfDivisors = nums[i] + 1;
                int j = 2;
                while(j <= range) {
                    if(nums[i] % j == 0) {
                        numOfDevisors = numOfDevisors+2;
                        tempSumOfDivisors = tempSumOfDivisors + (j + (nums[i]/j));
                    }
                    j++;
                }
                if(numOfDevisors == 4) resSum += tempSumOfDivisors;
            }
            else if(range*range == nums[i]) {
                int numOfDevisors = 3;
                int tempSumOfDivisors = nums[i]+1+range;
                int j = 2;
                while(j < range) {
                    if(nums[i] % j == 0) {
                        numOfDevisors = numOfDevisors+2;
                        tempSumOfDivisors = tempSumOfDivisors + (j + (nums[i]/j));
                    }
                    j++;
                }
                if(numOfDevisors == 4) resSum += tempSumOfDivisors;
            }
        }
        return resSum;
    }
    public static void main(String[] args) throws Exception {
        System.out.print(sumFourDivisors(new int[]{21,4,7}));
    }
}