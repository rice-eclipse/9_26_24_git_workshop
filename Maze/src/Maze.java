import java.awt.Color;
import java.io.*;
import java.util.*;

public class Maze
{
  private static final Color WALL_COLOR = new Color(0, 0, 0);
  private static final Color PATH_COLOR = new Color(255, 255, 255);
  private static final Color VISIT_COLOR = new Color(255, 0, 0);
  private static double blueGreenScale = 0;
  private static int numSteps = 0;
  
  public static void main(String[] args)
  {
    GridDisplay grid = load("maze2.txt");
    solveQueue(grid);
    System.out.println("Done " + numSteps + " " + grid.getNumRows()*grid.getNumCols() + " " + blueGreenScale);
  }
  
  public static <E> E getBottom(Stack<E> s)
  {
    Stack<E> temp = new ArrayStack<E>(new ArrayList<E>());
    while(s.isEmpty()!=true) {
    	temp.push(s.pop());
    }
    E bottom = temp.peek();
    
    while(temp.isEmpty()!=true) {
    	s.push(temp.pop());
    }
    return bottom;
  }
  
  public static void solveRecursive(GridDisplay grid, int row, int col)
  {
	  if(grid.getColor(new Location(row, col)).equals(PATH_COLOR)) {
		  grid.setColor(new Location(row, col), VISIT_COLOR);
		  GridDisplay.pause(10);
		  solveRecursive(grid, row-1, col);//up
		  solveRecursive(grid, row+1, col);//down
		  solveRecursive(grid, row, col+1);//right
		  solveRecursive(grid, row, col-1);//left
	  }
  }
  
  public static void solveStack(GridDisplay grid)
  {
	  ArrayStack<Location> toVisit = new ArrayStack<Location>(new ArrayList<Location>());
	  toVisit.push(new Location(1,1));
	  while(toVisit.isEmpty()==false) {
		  Location temp = toVisit.pop();
		  if(grid.getColor(temp).equals(PATH_COLOR)) {
			  blueGreenScale = blueGreenScale + 255*2.0/(grid.getNumCols()*grid.getNumRows());
			  //grid.setColor(temp, new Color(204 - (int)(blueGreenScale*0.2), (int)(blueGreenScale*0.6), 127 - (int)(Math.abs(127 - blueGreenScale))));
			  grid.setColor(temp, new Color(255 - (int)(blueGreenScale*0.3), 50 + (int)(blueGreenScale*0.5), (int)(blueGreenScale*0)));
			  GridDisplay.pause(0);
			  
			  toVisit.push(new Location(temp.getRow()-1, temp.getCol()));//up
			  toVisit.push(new Location(temp.getRow()+1, temp.getCol()));//down
			  toVisit.push(new Location(temp.getRow(), temp.getCol()+1));//right
			  toVisit.push(new Location(temp.getRow(), temp.getCol()-1));//left
		  }
		  numSteps++;
	  }
  }
  
  public static void solveQueue(GridDisplay grid)
  {
	  LLQueue<Location> toVisit = new LLQueue<Location>();
	  toVisit.enqueue(new Location(1,1));
	  while(toVisit.isEmpty()==false) {
		  Location temp = toVisit.dequeue();
		  if(grid.getColor(temp).equals(PATH_COLOR)) {
			  blueGreenScale = blueGreenScale + 255*2.0/(grid.getNumCols()*grid.getNumRows());
			  grid.setColor(temp, new Color(127*0 + (int)(Math.abs(127+127 - 2*blueGreenScale)),  127*0 + 102  - (int)(Math.abs(127*0 + 102 - 0.8*blueGreenScale)),  (int)(2*blueGreenScale) > 255 ? 255 : (int)(1.8*blueGreenScale)));
			  //grid.setColor(temp, new Color(127*0 + (int)(Math.abs(127+127 - 2*blueGreenScale)),  127 - (int)(Math.abs(127 - 0.8*blueGreenScale)),  (int)(2*blueGreenScale) > 255 ? 255 : (int)(1.8*blueGreenScale)));
			  //grid.setColor(temp, new Color(127 + (int)(Math.abs(127 - blueGreenScale)),  127 - (int)(Math.abs(127 - blueGreenScale)),  (int)blueGreenScale));
			  GridDisplay.pause(0);
			  
			  toVisit.enqueue(new Location(temp.getRow()-1, temp.getCol()));//up
			  toVisit.enqueue(new Location(temp.getRow()+1, temp.getCol()));//down
			  toVisit.enqueue(new Location(temp.getRow(), temp.getCol()+1));//right
			  toVisit.enqueue(new Location(temp.getRow(), temp.getCol()-1));//left
		  }
	  }
  }
  
  public static GridDisplay load(String file)
  {
    try
    {
      BufferedReader in = new BufferedReader(new FileReader(file));
      String line = in.readLine();
      ArrayList<String> lines = new ArrayList<String>();
      while (line != null)
      {
        lines.add(line);
        if (line.length() != lines.get(0).length())
          throw new RuntimeException("inconsistent line length");
        line = in.readLine();
      }
      
      GridDisplay grid = new GridDisplay(lines.size(), lines.get(0).length());
      grid.setTitle(file);
      for (int row = 0; row < grid.getNumRows(); row++)
      {
        for (int col = 0; col < grid.getNumCols(); col++)
        {
          char ch = lines.get(row).charAt(col);
          Color c;
          if (ch == 'X')
            c = WALL_COLOR;
          else if (ch == '.')
            c = PATH_COLOR;
          else
            throw new RuntimeException("invalid char:  '" + ch + "'");          
          grid.setColor(new Location(row, col), c);
        }
      }
      in.close();
      return grid;
    }
    catch(IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}