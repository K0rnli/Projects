import java.util.*;

public class Tile
{
	private boolean isTile;
	private boolean isTileNext;
    private boolean isPlayer;
    private boolean isPlayerNext;
    private boolean isP1, isP2;
    private boolean isP1Next, isP2Next;
    private boolean isGoal;
    private boolean isButton;
    private int buttonType;
    private ArrayList<Integer> tileList;
    private boolean isSplitter;
    ArrayList<Integer> splitLoc;
    private boolean isFalling;
    private boolean isSelected;
    private boolean isSelectedNext;
    private boolean isSwitchable;
    

    public Tile()
    {
        isTile = false;
        isTileNext = false;
        isPlayer = false;
        isP1 = false;
        isP2 = false;
        isP1Next = false;
        isP2Next = false;
        isButton = false;
        buttonType = -1;
        tileList = new ArrayList<Integer>();
        isSplitter = false;
        splitLoc = new ArrayList<Integer>();
        isFalling = false;
    }

    public boolean isTileNow() { return isTile; }

    public void    setTile(boolean a) { isTile = a; }
    
    public boolean isTileNextNow() { return isTileNext; }

    public void    setTileNext(boolean a) { isTileNext = a; }

    public boolean isPlayerNow() { return isPlayer; }

    public void    setPlayer(boolean a) { isPlayer = a; }
    
    public boolean isPlayerNextNow() { return isPlayerNext; }

    public void    setPlayerNext(boolean a) { isPlayerNext = a; }
    
    public boolean isP1Now() { return isP1; }

    public void    setP1(boolean a) { isP1 = a; }
    
    public boolean isP1NextNow() { return isP1Next; }

    public void    setP1Next(boolean a) { isP1Next = a; }
    
    public boolean isP2Now() { return isP2; }

    public void    setP2(boolean a) { isP2 = a; }
    
    public boolean isP2NextNow() { return isP2Next; }

    public void    setP2Next(boolean a) { isP2Next = a; }
    
    public boolean isGoalNow() { return isGoal; }

    public void    setGoal(boolean a) { isGoal = a; }
    
    public boolean isButtonNow() { return isButton; }

    public void    setButton(boolean a) { isButton = a; }
    
    public int 	   getButtonType() { return buttonType; }

    public void    setButtonType(int a) { buttonType = a; }
    
    public void    addTile(int x, int y, int act) { tileList.add(x); tileList.add(y); tileList.add(act); }

    public ArrayList<Integer>    getTileList() { return tileList; }
    
    public boolean isSplitterNow() { return isSplitter; }

    public void    setSplitter(boolean a) { isSplitter = a; }
    
    public void    addSplitLoc(int a, int b, int c, int d) { splitLoc.add(a); splitLoc.add(b); splitLoc.add(c); splitLoc.add(d);}
    
    public ArrayList<Integer>    getSplitLoc() { return splitLoc; }
    
    public boolean isFallingNow() { return isFalling; }

    public void    setFalling(boolean a) { isFalling = a; }
    
    public boolean isSelectedNow() { return isSelected; }

    public void    setSelected(boolean a) { isSelected = a; }
    
    public boolean isSelectedNextNow() { return isSelectedNext; }

    public void    setSelectedNext(boolean a) { isSelectedNext = a; }
    
    public boolean isSwitchableNow() { return isSwitchable; }

    public void    setSwitchable(boolean a) { isSwitchable = a; }
    
    
}
