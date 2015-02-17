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
package puzzles.multiples.sum;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
 *
 * @author lresende
 *
 */
public class MultiplesSum {
	public static Result sumMultiples(int limit) {
		Result result = new Result();
		for(int i = 1; i<limit; i++) {
			if(i % 3 == 0 || i % 5 == 0) {
				result.addNumber(i);
			}
		}
		
		return result;
	}
	
	
	
	public static void main(String[] args) {
		Result result = MultiplesSum.sumMultiples(1000);
		for(Integer i : result.getNumbers()) {
			System.out.println(">>> " + i);
		}
		System.out.println("Total " + result.getSum());
	}
	
	
}
