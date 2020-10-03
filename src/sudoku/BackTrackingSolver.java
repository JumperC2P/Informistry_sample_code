package sudoku;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hank Lee
 * Shared in InformisTry -- https://jumperc2p.github.io/InformisTry/
 *
 */
public class BackTrackingSolver {
	
	/**
	 * Find the blank cell and return with a map object
	 * @param sudokuGrid the sudoku grid
	 * @return two sets of data: 
	 * 			x : column
	 * 			y : row
	 */
	public Map<String, Integer> findBlankPosition(SudokuGrid sudokuGrid){
		
		Map<String, Integer> map = new HashMap<>();
		for (int y = 0; y < sudokuGrid.size; y++) {
			for (int x = 0; x < sudokuGrid.size; x++) {
				if (sudokuGrid.grid[y][x] == null) {
					map.put("y", y);
					map.put("x", x);
					return map;
				}
			}
		}
		return null;
	}
	
	public boolean solve(SudokuGrid sudokuGrid) {
    	
    	// find a blank cell to work on.
    	Map<String, Integer> currentPosition = this.findBlankPosition(sudokuGrid);
    	
    	if (currentPosition == null) {
    		return true;
    	}
    	
    	// get the current row and column values
    	int currentX = currentPosition.get("x");
    	int currentY = currentPosition.get("y");
    	
    	// try all the symbols one by one
    	for (int currentNumber : sudokuGrid.symbols) {
    		
    		// if the current number is valid, put it into the cell
    		if(sudokuGrid.basicValidate(sudokuGrid, currentNumber, currentX, currentY)) {
    			sudokuGrid.grid[currentY][currentX] = currentNumber;
    			
    			// recursively to find solutions
    			if (this.solve(sudokuGrid)) {
    				return true;
    			}
    			
    			// Once there is no symbol which is valid, recover the value of the value to null
    			sudokuGrid.grid[currentY][currentX] = null;
    		}
    	}
    	
        return false;
    }

}
