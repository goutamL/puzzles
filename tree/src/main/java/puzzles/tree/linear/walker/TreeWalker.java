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
package puzzles.tree.linear.walker;

import java.util.ArrayList;
import java.util.List;


/**
 * Given a tree like :
 * 
 *          A
 *      B         C
 *   D    E    F     G
 *  H I  J K  L M   N O
 *   
 * Print
 * 
 *   A
 *   B C
 *   D E F G
 *   H I J K L M N O
 *   
 * @author lresende
 *
 */
public class TreeWalker {
	
	public static void walk(Node tree) {
		System.out.println(tree.getValue());
		
		walkNextLevel(getChildren(tree));
	}
	
	private static void walkNextLevel(List<Node> children) {
		if(children.isEmpty()) {
			return;
		}
		
		List<Node> grandChildren = new ArrayList<Node>();
		for(Node node : children) {
			System.out.print(node.getValue());
			grandChildren.addAll(getChildren(node));
		}
		
		System.out.println();
		walkNextLevel(grandChildren);
	}
	
	
	public static List<Node> getChildren(Node node) {
		List<Node> children = new ArrayList<Node>();
		if(node != null) {
			if(node.getLeftChild() != null) {
				children.add(node.getLeftChild());
			}
			
			if(node.getRightChild() != null) {
				children.add(node.getRightChild());
			}
		}
		
		return children;
	}
	
	public static void main(String[] args) {
		Node tree = new Node.Builder("A")
			.leftChild(
				new Node.Builder("B")
					.leftChild(new Node.Builder("D")
							.leftChild(new Node.Builder("H").build())
							.rightChild(new Node.Builder("I").build())
							.build())
					.rightChild(new Node.Builder("E").build())
					.build()
			 )
			 .rightChild(
						new Node.Builder("C")
						.leftChild(new Node.Builder("F").build())
						.rightChild(new Node.Builder("G").build())
						.build()
			 )
			 .build();
		
		
		TreeWalker.walk(tree);
	}

}
