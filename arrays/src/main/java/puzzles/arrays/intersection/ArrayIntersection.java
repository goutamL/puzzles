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
package puzzles.arrays.intersection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given two list of friends, find the common friends 
 * in an efficient way.
 * 
 * @author lresende
 *
 */

public class ArrayIntersection {
	private static String[] MY_FRIENDS = {"John", "Peter", "Frank", "Paul"};
	private static String[] HIS_FRIENDS = {"Alan", "Mike", "Frank", "Peter", "Michael"};
	
	public static List<String> getIntersection(String[] l1, String[] l2) {
		List<String> common = new ArrayList<String>();
		int size = l1.length + l2.length;
		int p1 = 0;
		int p2 = 0;
		for(int n=0; n<size; n++) {
			String e1 = l1[p1];
			String e2 = l2[p2];
			if(e1.equalsIgnoreCase(e2)) {
				// intersection
				common.add(e1);
				p1++;
				p2++;
			} if(e1.compareTo(e2) < 0 ) {
				p1++;
			} if(e1.compareTo(e2) > 0) {
				p2++;
			}
			
			if(p1 >= l1.length || p2 >= l2.length) {
				break;
			}
		}
		
		return common;
	}
	
	public static void printList(String title, List<String> list) {
		String[] array = new String[list.size()];
		array = list.toArray(array);
		
		printList(title, array);
	}
	
	public static void printList(String title, String[] list) {
		System.out.println(title);
		for(String e : list) {
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	public static void main(String[] args) {
		Arrays.sort(MY_FRIENDS);
		Arrays.sort(HIS_FRIENDS);
		
		ArrayIntersection.printList("My friends:", MY_FRIENDS);
		ArrayIntersection.printList("His Friends", HIS_FRIENDS);

		ArrayIntersection list = new ArrayIntersection();
		List<String> common = ArrayIntersection.getIntersection(MY_FRIENDS, HIS_FRIENDS);

		ArrayIntersection.printList("Common elements :", common);

	}
	
}
