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
package puzzles.arrays.bits;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BitVectorTest {
	private BitVector bitVector;
	
	@Before
	public void setup() {
		bitVector = new BitVector();
	}
	
	@Test
	public void adding_one_true_bit_properly_increments_size() {
		bitVector.add(true);
		
		assertThat(bitVector.length()).isEqualTo(1);
		assertThat(bitVector.get(0)).isTrue();
	}

	@Test
	public void adding_one_false_bit_properly_increments_size() {
		bitVector.add(false);
		
		assertThat(bitVector.length()).isEqualTo(1);
		assertThat(bitVector.get(0)).isFalse();
	}
	

	@Test
	public void adding_one_bit_properly_return_its_position() {
		int position = bitVector.add(false);

		assertThat(position).isEqualTo(0);
	}

	@Test
	public void adding_multiple_bits_properly_return_its_position() {
		bitVector.add(false);
		bitVector.add(false);
		int position = bitVector.add(true);

		assertThat(position).isEqualTo(2);
	}

	
	@Test(expected=NegativeArraySizeException.class)
	public void fails_to_retrieve_below_zero_index() {
		bitVector.get(-1);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void fails_to_retrieve_out_of_bound_index() {
		bitVector.add(true);
		bitVector.get(2);
	}
	
	@Test
	public void adding_one_false_bit_then_changing_to_true_properly_return_new_value() {
		int position = bitVector.add(false);
		bitVector.setBit(position, true);
		
		assertThat(bitVector.length()).isEqualTo(1);
		assertThat(bitVector.get(0)).isTrue();
	}
	
	@Test
	public void adding_one_true_bit_then_changing_to_false_properly_return_new_value() {
		int position = bitVector.add(true);
		bitVector.setBit(position, false);
		
		assertThat(bitVector.length()).isEqualTo(1);
		assertThat(bitVector.get(0)).isFalse();
	}
	
	@Test
	public void removing_elements_properly_shift_bits() {
		bitVector.add(true);
		int position = bitVector.add(false);
		bitVector.add(true);
		
		bitVector.remove(position);
		
		assertThat(bitVector.length()).isEqualTo(2);
		assertThat(bitVector.get(0)).isTrue();
		assertThat(bitVector.get(1)).isTrue();
	}
}
