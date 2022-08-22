
public class Square 
{
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int START = 2;
	final static int EXIT = 3;
	
	final static char WORKING 		= 'o';
	final static char EXPLORED 		= '.';
	final static char ON_EXIT_PATH 	= 'x';
	final static char UNKNOWN 		= '_';
	
	private int row, col;
	private int type;
	private char status;
	private Square pre;
	/**
	 * constructs the square
	 * @param row  the row the square is in
	 * @param col  the column the square is in
	 * @param type  the type the square is
	 */
	public Square(int row, int col, int type)
	{
		this.setRow(row);
		this.setCol(col);
		this.setType(type);
		setPre(null);
		status = UNKNOWN;
	}
	/**
	 * gets the column of the square
	 * @return the column of the square
	 */
	public int getCol() {
		return col;
	}
	/**
	 * sets the column of the square
	 * @param col the new column of the square
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * gets the type of the square
	 * @return the type of the square
	 */
	public int getType() {
		return type;
	}
	/**
	 * sets the type of the square
	 * @param type the new type of the square
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * gets the status of the square
	 * @return the status of the square
	 */
	public char getStatus() {
		return status;
	}
	/**
	 * sets the status of the square
	 * @param status the new status of the square
	 */
	public void setStatus(char status) {
		this.status = status;
	}
	/**
	 * gets the row of the square
	 * @return the row of the square
	 */
	public int getRow() {
		return row;
	}
	/**
	 * gets the square that progressed to it
	 * @return  the square presiding it
	 */
	public Square getPre() {
		return pre;
	}
	/**
	 * sets the square that progressed to it
	 * @param pre  the square presiding it
	 */
	public void setPre(Square pre) {
		this.pre = pre;
	}
	/**
	 * checks if the tile is a place that can be moved to
	 * @return if the tile can be moved to
	 */
	public boolean isMoveable()
	{
		return (type == 0 || type == 3) && status == UNKNOWN;
	}
	/**
	 * sets the row of the square
	 * @param row the new row of the square
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * compares 2 square
	 * @return the result of the comparison
	 */
	@Override
	public boolean equals(Object obj)
	{
		return ((Square)obj).getCol() == col && ((Square)obj).getRow() == row;
	}
	/**
	 * the toString
	 * @return the toString
	 */
	@Override
	public String toString()
	{
		switch(type)
		{
		case 0 : return status + "";
		case 1 : return "#";
		case 2 : return "S";
		case 3 : return "E";
		}
		return "";
	}
}
