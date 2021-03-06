package company.facebook;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class WhatIsThis {
	public static void main(String[] args) {
		Integer[] arr = {1, 3, 2, 2, 4, 3, 1, 8, 7};
		WhatIsThis what = new WhatIsThis();
//		int res = what.findMaxRandom(arr);
//		System.out.println(res);
		
//		boolean res = what.isMonotonic(arr);
		
		List<Integer> list = Arrays.asList(arr);
		int res = what.findByPercent(list.iterator(), 0.5);
		System.out.println(res);
	}
	
	/**
	 * 给你一个unsorted integer array （array 很大，你只有一个iterator）和 一个用double表示的百分数（比如 0.4 代表40%）， 
	 * 输出：sort array后处于40%位置的那个数的值。例子：输入[1 3 2 5 4 6 7 9 8 10], 和 0.6， 你就返回6. 方法就是bucket sort嘛，
	 * 注意一些corner case， 比如输入0.3333什么的注意四舍五入
	 */
	int findByPercent(Iterator<Integer> it, double percent) {
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // No need to use TreeMap, HashMap is good enough
		int count = 0;
		
		while (it.hasNext()) {
			int now = it.next();
			count++;
			map.put(now, map.getOrDefault(now, 0) + 1);
		}
		
		int targetIndex = (int)Math.round(percent * count); // Force to an integer.
		
		int index = 0;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey();
			int numCount = entry.getValue();
			
			index += numCount;
			if (index >= targetIndex) {
				return num;
			}
		}
		
		return -1;
	}
	
	/**
	 * find index of maximum number in array, if multiple maximum numbers occurs, return a random index
	 * Reservoir Sampling
	 */
	int findMaxRandom(int[] arr){
		int max = Integer.MIN_VALUE;
		int count = 0;
		int index = -1;
		Random ran = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == max) {
				count++;
				if (ran.nextInt(count) == 0) {
					index = i;
				}
			} else if (arr[i] > max) {
				max = arr[i];
				count = 1;
				index = i;
			}
		}
		
		return index;
	}
	
	/**
	 *  是否单调: 递增或者递减 注意相等的值
	 *  1. detect an array is monotonic or not. 
		1,2,3,4 -> T
		1,1,1,4 -> T
		4,3,2,1 -> T
		1, 1 ->T
	 */
	boolean isMonotonic(int[] arr) {
		if (arr == null || arr.length <= 2) {
			return true; //corner cases
		}
		
		Boolean increasing = null;
		int preVal = arr[0];
		int pos = 1;
		
		while (pos < arr.length) {
			while (pos < arr.length && arr[pos] == preVal) {
				pos++;
			}
			
			if (pos < arr.length) {
				if (increasing == null) {
					increasing = arr[pos] - preVal > 0 ? true : false;
				} else {
					if (increasing) {
						if (arr[pos] < preVal) {
							return false;
						}
					} else {
						if (arr[pos] > preVal) {
							return false;
						}
					}
				}
				
				preVal = arr[pos];
				pos++;
			}
		}
		
		return true;
	}
}
