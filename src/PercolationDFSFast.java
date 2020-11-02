import java.util.*;
public class PercolationDFSFast extends PercolationDFS
{

    /**
     * Initializes a grid so that all cells are blocked
     * parameter n is the size of the grid
     */
    public PercolationDFSFast(int n) {
        super(n);
    }

    @Override
    public void updateOnOpen(int row, int col) //variables are pretty self explanatory
    {
        boolean flag=false;
        if (row==0) flag=true;
        if (row>0)
        {
            if (myGrid[row-1][col]==FULL) flag=true;
        }
        if (row<myGrid.length-1)
        {
            if (myGrid[row+1][col]==FULL) flag=true;
        }

        if (col>0)
        {
            if (myGrid[row][col-1]==FULL) flag=true;
        }
        if (col<myGrid[0].length-1)
        {
            if (myGrid[row][col+1]==FULL) flag=true;
        }
        if(flag) dfs(row,col);
        return; //nothing needed here
    }
}