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

public class Node {
	private String value;
	private Node lChild;
	private Node rChild;

	protected Node() {

	}

	protected Node(Builder builder) {
		this.value = builder.value;
		this.lChild = builder.lChild;
		this.rChild = builder.rChild;
	}

	public String getValue() {
		return value;
	}

	public Node getLeftChild() {
		return lChild;
	}

	public Node getRightChild() {
		return rChild;
	}

	@Override
	public String toString() {
		return "Node [value=" + value + "]";
	}

	public static class Builder {
		private String value;
		private Node lChild;
		private Node rChild;

		public Builder() {

		}

		public Builder(String value) {
			this.value = value;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		public Builder leftChild(Node lChild) {
			this.lChild = lChild;
			return this;
		}

		public Builder rightChild(Node rChild) {
			this.rChild = rChild;
			return this;
		}

		public Node build() {
			return new Node(this);
		}
	}
}
