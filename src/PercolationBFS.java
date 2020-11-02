import java.util.*;
public class PercolationBFS extends PercolationDFSFast
{

    /**
     * Initialize a grid so that all cells start off blocked
     * @parameter n is the size of the grid
     */
    public PercolationBFS(int n)
    {
        super(n);
    }
    /**
     * Uses a breadth-first search approach
     * Extends the fast depth-first search approach
     */
    @Override
    public void dfs(int row, int col) //variables are same as other class
    {
        int sz = myGrid.length;
        Queue<Integer> qp = new LinkedList<>();
        myGrid[row][col]=FULL;
        qp.add(row*sz + col);
        while(qp.size() !=0)
        {
            Integer r = qp.remove();
            int nr = r/sz; //new row
            int nc = r%sz; //new column
            if (nr>0)
            {
                if (myGrid[nr-1][nc]==OPEN)
                {
                    myGrid[nr-1][nc]=FULL;
                    qp.add((nr-1)*sz + nc);
                }
            }
            if (nc>0)
            {
                if (myGrid[nr][nc-1]==OPEN)
                {
                    myGrid[nr][nc-1]=FULL;
                    qp.add(nr*sz + nc-1);
                }
            }
            if (nr<myGrid.length-1)
            {
                if (myGrid[nr+1][nc]==OPEN)
                {
                    myGrid[nr+1][nc]=FULL;
                    qp.add((nr+1)*sz + nc);
                }
            }
            if (nc<myGrid[0].length -1)
            {
                if (myGrid[nr][nc+1]==OPEN)
                {
                    myGrid[nr][nc+1]=FULL;
                    qp.add(nr*sz + nc+1);
                }
            }
        }
        return;
    }
}
