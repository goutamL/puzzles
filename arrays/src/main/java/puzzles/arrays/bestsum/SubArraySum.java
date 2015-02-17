/* 
 * Copyright 2014 Luciano Resende
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package puzzles.arrays.bestsum;
import java.util.Arrays;


/**
 * Given an array with positive and negative integers, 
 * Write code that finds the sub array that provides the largest sum
 *  
 * @author lresende
 *
 */
public class SubArraySum {

	public static int[] process(int[] array) {
		int p1 = 0;
		int p2 = 0;
		
		int sum = 0;
		int bestsum = 0;
		for(int i = 0; i<array.length; i++) {
			sum += array[i];
			if(sum == 0) {
				p1 = i + 1;
				p2 = i + 1;
				sum = 0;
				bestsum=0;
			} else if(sum > bestsum) {
				p2 = i + 1;
				bestsum = sum;
			}
		}
		
		return Arrays.copyOfRange(array, p1, p2);

	}
	
	
	public static void main(String[] args) {
		int[] numbers = {2, 2, -4, 3, 4, -10, 5};
		
		int[] result = SubArraySum.process(numbers);
		
		int sum = 0;
		System.out.print("[");
		for(int i = 0; i<result.length; i++) {
			System.out.print(result[i] + " ");
			sum += result[i];
		}
		System.out.print("] = " + sum);
	}

}
