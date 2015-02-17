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
package puzzles.arrays.triplesum;
import java.util.Arrays;

/**
 * Write java code to find out if it exists that the sum of three integers is zero among a sequence integers. 
 * @author lresende
 *
 */
public class SearchArrayForSum {

	public static Triple process(int[] array, int sum) {
		Arrays.sort(array);
		System.out.println("Array being processed:");
		for(Integer v : array) {
			System.out.print(v + ",");
		}
		System.out.println();
		Triple result = null;
		for(int i = 0; i<array.length - 2; i++) {
			for(int j= i+1; j<array.length - 1; j++ ) {
				for(int k=array.length -1; k>j; k--) {
					System.out.println("Processing " + i + "," + j + "," + k);
					Triple evaluate = new Triple(array[i], array[j], array[k]);
					System.out.println("Processing " + evaluate + " = " + evaluate.getSum());
					if(evaluate.getSum() == sum) {
						//found, stop
						result = evaluate;
						break;
					} else if(evaluate.getSum() < sum) {
						//on a sorted array, if you get a tuple that is below the expected value
						// all others are going to be even lower, as the array is being read in
						// decrescent order
						break;
					}
				}
				//optimization, stop when first occurrence of expected result
				if(result != null) {
					break;
				}
			}
			//optimization, stop when first occurrence of expected result
			if(result != null) {
				break;
			}

		}
		
		
		return result;
	}
	
	
	public static void main(String[] args) {
		//int[] numbers = {9, 3, -5, 2, -4, 7};
		int[] numbers = {10, 3, -5, 2, -4, 7};
		
		Triple result = SearchArrayForSum.process(numbers,  0);
		if(result != null) {
			System.out.println("Result found : " + result);
		} else {
			System.out.println("Could not find matching triple");
		}
	}}
