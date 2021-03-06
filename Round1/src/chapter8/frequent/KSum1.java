package chapter8.frequent;
/**
 * Given n distinct positive integers, integer k (k <= n) and a number target.

Find k numbers where sum is target. Calculate how many solutions there are?

Have you met this question in a real interview? Yes
Example
Given [1,2,3,4], k = 2, target = 5.

There are 2 solutions: [1,4] and [2,3].

Return 2.
 * @author Lei
 *
 */
public class KSum1 {
    public int kSum(int A[], int k, int target) {
        int[][][] hash = new int[A.length + 1][k + 1][target + 1];
        
        // How to initialize 这个题我们没有把impossible的情况置为MIN_VALUE,因为我们没有用min, max() 只是计算方案个数。 不可能就是0个呗
        for (int i = 0; i <= A.length; i++) {
            hash[i][0][0] = 1; // 从前i个数中，取0个数，组成target为0的方案数量
        }
        
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    hash[i][j][t] = hash[i - 1][j][t];
                    if (t - A[i - 1] >= 0) { // 前i - 1个数中，取j - 1个数，组成结果为t - A[i - 1]的方案数。 本质就是想利用一下A[i - 1]
                        hash[i][j][t] += hash[i - 1][j - 1][t - A[i - 1]];
                    }
                }
            }
        }
        
        return hash[A.length][k][target];
    }
}
