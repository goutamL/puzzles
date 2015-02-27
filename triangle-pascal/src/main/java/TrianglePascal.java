

/**
 * 
 *      1             1
 *    1   1           1  1
 *   1  2   1         1  2  1
 *  1  3  3  1        1  3  3  1
 * 1  4  6  4  1      1  4  6  4  1
 * 
 * 
 * @author lresende
 *
 */
public class TrianglePascal {

	 
	/**
	 * [1]  [l1c1]        f1=1
	 * [2]  [l2c1]        f2=2 
	 *      [l2c2]
	 * [4]  [l3c1]        f3=4
	 *      [l3c2]
	 *      [l3c3]
	 * [7]  [l4c1]        f4=7
	 *      [l4c2]
	 *      [l4c3]
	 *      [l4c4]
	 * [11][l5c1]       f5=11
	 * 
	 *                  fn=f(n-1) + n - 1
	 *       
	 */
    private static int getPosition(int level, int column) {
    	
        if(level < 0) {
           throw new IllegalArgumentException("level should be at positive integer");
        }
        
        if(column < 0 || column > level) {
            throw new IllegalArgumentException("column should be positive integer and no greater then level");
        }
        
        int i =  getInitialPosition(level);
        
        //System.out.println("level[" + level + "]=" + i);
        //System.out.println("level[" + level + "][" + column + "]=" + (i+column-1));
        
        return (i + column - 2); // -2 to accomodate 0 based array
        
    }
    
    private static int getInitialPosition(int level) {
    	if(level == 1) {
    		return 1;
    	} else {
    		return getInitialPosition(level-1) + level - 1 ;
    	}
    }
    
    public static void buildPascalTriangle(int levels) {
        if(levels < 1) {
            throw new IllegalArgumentException("Levels should be at least 1");
        }
    
       int[] triangle = new int[levels * levels];
       for(int l=1; l<=levels; l++) { 
          for(int c=1; c<=l; c++) {
              int v = 1;
              if(l > 2 ) {
                 if(c!=1 && c!=l) {
                    v = triangle[getPosition(l-1, c-1)] + triangle[getPosition(l-1,c)];
                 } 
              }
              
              triangle[getPosition(l,c)] = v;
              
              System.out.print(v + " ");
          }
          System.out.println(" ");
       }
    }
    
    public static void main(String[] args) {    	
		TrianglePascal.buildPascalTriangle(1);
		System.out.println();
		System.out.println();
		
		TrianglePascal.buildPascalTriangle(2);
		System.out.println();
		System.out.println();
		
		TrianglePascal.buildPascalTriangle(3);
		System.out.println();
		System.out.println();
		
		TrianglePascal.buildPascalTriangle(4);
		System.out.println();
		System.out.println();
	}
}
