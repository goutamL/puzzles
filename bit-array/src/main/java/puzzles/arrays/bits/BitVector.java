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

/**
 * Vector of Bits that grows as needed. By default, all bits are initialized as false.
 * 
 * @author lresende
 *
 */
public class BitVector {
	private static int BITS_PER_LONG = 64;
	private static int DEFAULT_ARRAY_SIZE = 10;
	
	private volatile long bitArray[];
	private volatile int capacity;
	private volatile int size;
	
	public BitVector() {
		bitArray = new long[DEFAULT_ARRAY_SIZE];
		capacity = bitArray.length * BITS_PER_LONG;
		size = 0;
	}

	
	/**
	 * Return the number of bits stored in the bit vector
	 * 
	 * @return the length of the bit array
	 */
	public int length() {
		return size;
	}
	

	/**
	 * Retrieve the bit as boolean on the specified position
	 * @param position the bit array position that is being retrieved
	 * @return the bit value as boolean
	 */
	public boolean get(int position) {
		validatePosition(position);
		
		int arrayPosition = getArrayPosition(position);
		int bitPosition = getBitPosition(position);
		
		return (bitArray[arrayPosition] >> bitPosition & 0x01) == 1 ? true : false;		
	}
	
	public int add(boolean value) {
		if(capacity == size) {
			synchronized (bitArray) {
				long newBitArray[] =  new long[bitArray.length * 2];
				int newCapacity = newBitArray.length * BITS_PER_LONG;
				
				System.arraycopy(bitArray, 0, newBitArray, 0, bitArray.length);
				
				this.bitArray = newBitArray;
				this.capacity = newCapacity;
			}
		}
		
		int arrayPosition = 0;
		int bitPosition = 0;
		synchronized (bitArray) {
			arrayPosition = getArrayPosition(size);
			bitPosition = getBitPosition(size);

			if(value) {
				//set bit
				bitArray[arrayPosition] = (bitArray[arrayPosition] | (1 << bitPosition));
			} else {
				//unset bit
				bitArray[arrayPosition] = (bitArray[arrayPosition] & ~(1 << bitPosition));
			}
			
			size++;
		}
		
		return arrayPosition + bitPosition;
	}

	public void setBit(int position, boolean value) {
		validatePosition(position);

		int arrayPosition = 0;
		int bitPosition = 0;

		arrayPosition = getArrayPosition(position);
		bitPosition = getBitPosition(position);

		if(value) {
			//set bit
			bitArray[arrayPosition] = (bitArray[arrayPosition] | (1 << bitPosition));
		} else {
			//unset bit
			bitArray[arrayPosition] = (bitArray[arrayPosition] & ~(1 << bitPosition));
		}			
	}
	
	public boolean remove(int position) {
		validatePosition(position);
		
		boolean value = get(position);
		
		synchronized (bitArray) {
			//shift the array elements one position to the left
			//starting on next element from the element that is
			//being removed
			for(int p = position+1; p<size; p++) {
				setBit(p-1, get(p));
			}
			
			//reduce size
			size--;
		}
		
		return value;
	}
	
	
	public void clear() {
		synchronized (bitArray) {
			initializeBitArray(bitArray);	
		}
	}
	
	
	private void validatePosition(int position) {
		if(position < 0) {
			throw new NegativeArraySizeException("Bit position [" + position + "] < 0");
		}
		
		if(position >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	/**
	 * Initialize the bit array to have all bits as false
	 * @param bitArray
	 */
	private void initializeBitArray(long[] bitArray) {
		for(int i=0; i<bitArray.length; i++) {
			for(int b=0; b<64; b++) {
				bitArray[i] = (bitArray[i] & ~(1 << b));
			}
		}
	}
	
	/**
	 * Maps the bit position to where in the long array it's located.
	 * Note that each long can store 64 bits, so bit 70 will go on the 2nd long.
	 * @param pos
	 * @return
	 */
	private int getArrayPosition(int pos) {
		return pos / BITS_PER_LONG;
	}
	
	/**
	 * Maps the bit position to which bit in the long number it's located.
	 * Note that each long can store 64 bits, so bit 70 will go on the 2nd long in the 6th bit position.
	 * @param pos
	 * @return
	 */
	private int getBitPosition(int pos) {
		return pos % BITS_PER_LONG;
	}
}
