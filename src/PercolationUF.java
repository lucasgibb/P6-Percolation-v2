import java.util.*;
public class PercolationUF implements IPercolate
{
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    /** Constructor for PercolationUF initializes the class variables above.
     *the IUnionFind object passed as a parameter is initialized; the IUnionFind object in the class is assigned that
     *the myGrid object is initialized with all the n squared cells as false.
     *VTOP and VBOTTOM are assigned a fixed value which will not be the same as the map of any legitimate row, col pair.
     *@parameter finder used to assign the value of myFinder
     *@parameter size, an integer which provides the size of the grid
     */

    PercolationUF (IUnionFind finder, int size)
    {
        finder.initialize((size * size) + 2);
        myFinder = finder;
        myGrid = new boolean[size][size];
        for (int i=0; i<size; i++)
        {
            for (int j=0; j<size; j++)
            {
                myGrid[i][j]=false;
            }
        }
        myOpenCount=0;
        VTOP=size*size;
        VBOTTOM=size*size + 1; //makes these out of gird
    }

    /** Checks if the row and col values passed are valid indices or not.
     *@parameter row, the row index
     *@parameter col, the column index
     */

    public boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }
    /**
     * Returns true if site (row, col) is OPEN
     * @parameter row index in range [0,N-1]
     * @parameter column index in range [0,N-1]
     */
    @Override
    public boolean isOpen(int row, int col)
    {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) is not inbounds", row,col));
        }
        return (myGrid[row][col]);
    }

    /**
     * Returns true if site (row, col) is FULL
     * @parameter row index in range [0,N-1]
     * @parameter column index in range [0,N-1]
     * @return whether the referenced cell is full or not
     */
    @Override
    public boolean isFull (int row, int col)
    {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) is not inbounds", row,col));
        }
        int d = row*(myGrid.length) + col; //the single integer value
        return (myFinder.connected(d,VTOP));
    }
    /**
     * Return true if the simulated percolation percolates
     */
    @Override
    public boolean percolates()
    {
        return (myFinder.connected(VTOP,VBOTTOM));
    }
    /**
     * Returns the number of distinct sites that have been opened in this
     * simulation stored in myCount
     */
    @Override
    public int numberOfOpenSites(){
    return myOpenCount;
    }

    /**
     * @parameter row index in range [0,N-1]
     * @parameter col index in range [0,N-1]
     */
    @Override
    public void open (int row, int col)
    {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) is not inbounds", row,col));
        }
        if (myGrid[row][col]) return;
        int d = row*(myGrid.length) + col; //single integer value
        myGrid[row][col]=true;
        myOpenCount=myOpenCount+1;
        if (row==0) myFinder.union(d, VTOP);

        if (row==myGrid.length - 1) myFinder.union(d, VBOTTOM);
        if (row>0)
        {
            if (myGrid[row-1][col])
            {
                int q = (row-1)*(myGrid.length) + col;
                myFinder.union(d,q);
            }
        }
        if (col>0)
        {
            if (myGrid[row][col-1])
            {
                int q = row*(myGrid.length) + col-1;
                myFinder.union(d,q);
            }
        }
        if (row<myGrid.length -1)
        {
            if (myGrid[row+1][col])
            {
                int q = (row+1)*(myGrid.length) + col;
                myFinder.union(d,q);
            }
        }
        if (col<myGrid[0].length - 1)
        {
            if (myGrid[row][col+1])
            {
                int q = row*(myGrid.length) + col+1;
                myFinder.union(d,q);
            }
        }
        return; //nothing needed here
    }
}