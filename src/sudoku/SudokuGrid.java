package sudoku;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class SudokuGrid {
	
	public Integer size = 0;
	public Integer[] symbols = null;
	public Integer[][] grid = null;
	public Integer sqrt = 0;
	
	public boolean validate() {
    	
    	for (int currentY = 0; currentY < size; currentY++) {
    		for (int currentX = 0; currentX < size; currentX++) {
    			int currentNumber = grid[currentY][currentX];
    			if (!this.basicValidate(this, currentNumber, currentX, currentY))
    				return false;
    		}
    	}
		
		return true;
    }

	
	/**
	 * To do the basic validation of sudoku grid.
	 * In the method, it only checks basic 3 constraints, 
	 * which are box-value constraints, row-value constraints and column-value constraints
	 * 
	 * @param sudokuGrid the sudoku grid
	 * @param currentNumber the current number to check
	 * @param currentX the current column of the cell
	 * @param currentY the current row of the cell
	 * @return true: valid, false: invalid
	 */
	public boolean basicValidate(SudokuGrid sudokuGrid, int currentNumber, int currentX, int currentY) {
		

		// compare each number in one row
		for (int x = 0; x < sudokuGrid.size; x++) {
			if (currentX != x && sudokuGrid.grid[currentY][x] != null && currentNumber == sudokuGrid.grid[currentY][x])
				return false;
		}
		
		// compare each number in one column
		for (int y = 0; y < sudokuGrid.size; y++) {
			if (currentY != y && sudokuGrid.grid[y][currentX] != null && currentNumber == sudokuGrid.grid[y][currentX])
				return false;
		}
		
		// compare each number in one sub-grid
		int modX = currentX / sudokuGrid.sqrt;
		int modY = currentY / sudokuGrid.sqrt;
		
		for (int y = (modY*sudokuGrid.sqrt); y<((modY+1)*sudokuGrid.sqrt); y++) {
			for (int x = (modX*sudokuGrid.sqrt); x<((modX+1)*sudokuGrid.sqrt); x++) {
				if (currentX != x && currentY != y && sudokuGrid.grid[y][x] != null && sudokuGrid.grid[y][x] == currentNumber)
					return false;
			}
		}
		
		return true;
		
	}
}
