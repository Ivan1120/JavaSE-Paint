

import java.util.Stack;

import shape.Circle;
import shape.Oval;
import shape.Rectangle;
import shape.Square;
import shape.TheShape;
/**
 * Board class
 * @author Chen,Wenqiang(Ivan) A00871834 Set 2A
 * @version Apr 7, 2019
 */
public class Board {
    private Stack<TheShape> allShapes;
    private String curDrawShape;
    private TheShape curShape;
    private Gui gui;
    private int indexOfShape;
    private boolean isSelected;
    private boolean selectMode;

    /**
     * board constructor
     * @param gui the gui to use
     */
    public Board(Gui gui) {
        setGui(gui);
        allShapes = new Stack<>();

    }
    /**
     * when user when select mode ,to check if user click on shape range
     * 
     * @param x x position of user click
     * @param y y position of user click
     */
    public void isInRange(double x, double y) {
        boolean isFound = false;
        for (int i = 0; i < allShapes.size(); i++) {

            switch (allShapes.get(i).getType()) {
            case "square":
            case "rectangle":
                isFound = allShapes.get(i).isInRange(x, y);

                break;
            case "circle":
                isFound = allShapes.get(i).isInRange(x, y);

                break;
            case "oval":

                isFound = allShapes.get(i).isInRange(x, y);
                break;

            }

            if (isFound) {
                indexOfShape = i;
                curShape = allShapes.get(i);
                curDrawShape = allShapes.get(i).getType();
                isSelected = true;
                gui.selectCanvas(i);
            }
        }

    }
    /**
     * Create new shape based on user draw
     * @param startX start x mouse position
     * @param startY start y mouse position
     * @param x end x mouse position
     * @param y end y mouse position
     */
    public void createNewShape(double startX, double startY, double x, double y,String color) {
      
        switch (curDrawShape) {

        case "rectangle":
            curShape = new Rectangle(startX, x, startY, y,color);
            gui.drawRec(curShape.getLeft(), curShape.getTop(), curShape.getWidth(), curShape.getHeight());

            break;
        case "square":
            curShape = new Square(startX, x, startY, y,color);
            gui.drawRec(curShape.getLeft(), curShape.getTop(), curShape.getWidth(), curShape.getHeight());

            break;
        case "oval":
            curShape = new Oval(startX, x, startY, y,color);
            gui.drawOval(curShape.getLeft(), curShape.getTop(), curShape.getWidth(), curShape.getHeight());

            break;
        case "circle":
            curShape = new Circle(startX, x, startY, y,color);
           gui.drawOval(curShape.getLeft(), curShape.getTop(), curShape.getWidth(), curShape.getHeight());
            break;

        }
        
        allShapes.add(curShape);
        gui.addCanvas();

    }

    /**
     * based on user move position and the corresponding shape, pass to gui reset
     * shape
     * 
     * @param x x mouse position
     * @param y y mouse position
     */
    public void resetShape(double x, double y) {
        switch (curDrawShape) {
        case "square":
        case "rectangle":
            gui.resetRect(x, y, curShape.getWidth(), curShape.getHeight());
            break;
        case "circle":
        case "oval":
            gui.resetOval(x, y, curShape.getWidth(), curShape.getHeight());
            break;

        }

    }

    /**
     * when user enter selected mode and finish drag, reset current shape position
     * ,board decide how to reset the type of shape and pass to gui to draw shape
     * 
     * @param endX1 endX mouse position
     * @param endY1 endY mouse position
     */
    public void handleSelectMode(double endX1, double endY1) {
        curShape.resetPosition(endX1, endY1);
        resetShape(curShape.getLeft(), curShape.getTop());
    }
    /**
     * isSelect getter
     * @return isSelected
     */
    public boolean isSelected() {
        return isSelected;
    }


    /**
     * isSelected setter
     * @param isSelected to set
     */
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    /**
     * isSelectMode getter
     * @return isSelectMode
     */
    public boolean isSelectMode() {
        return selectMode;
    }
    /**
     * isSelectMode setter
     * @param selectMode to set
     */
    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }

   
    /**
     * curDrawShape getter
     * 
     * @return curDrawShape
     */
    public String getCurDrawShape() {
        return curDrawShape;
    }
    /**
     * curDrawShape setter
     * @param curDrawShape to set
     */
    public void setCurDrawShape(String curDrawShape) {
        this.curDrawShape = curDrawShape;
    }
    /**
     * curShape getter
     * @return curShape 
     */
    public TheShape getCurShape() {
        return curShape;
    }
    /**
     * curShape setter
     * @param curShape to set
     */
    public void setCurShape(TheShape curShape) {
        this.curShape = curShape;
    }
    /**
     * getIndexOfShape getter
     * @return getIndexOfShape
     */
    public int getIndexOfShape() {
        return indexOfShape;
    }
    /**
     * getIndexOfShape setter
     * @param index getIndexOfShape
     */
    public void setIndex(int index) {
        this.indexOfShape = index;
    }
    /**
     * allShapes getter 
     * @return allShapes
     */
    public Stack<TheShape> getAllShapes() {
        return allShapes;
    }
    /**
     * allShapes setter 
     * @param allShapes to set
     */
    public void setAllShapes(Stack<TheShape> allShapes) {
        this.allShapes = allShapes;
    }
    /**
     * gui getter
     * @return gui
     */
    public Gui getGui() {
        return gui;
    }
    /**
     * gui setter 
     * @param gui to set
     */
    public void setGui(Gui gui) {
        this.gui = gui;
    }
   

}