package shape;

/**
 * The shape class
 * @author Chen,Wenqiang(Ivan) A00871834 Set 2A
 * @version Apr 7, 2019
 */
public abstract class TheShape {
    protected double left, right, top, bottom, width, height;
    
    protected String type,color;

   
    /**
     * default constructor
     */
    public TheShape() {

    }

    /**
     * set for circle and square
     * 
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    protected void setForCircleSquare(double x1, double x2, double y1, double y2,String color) {
        setWidth(x1, x2, y1, y2);
        setHeight(x1, x2, y1, y2);
        setShape(x1, x2, y1, y2,color);

    }

    /**
     * set for oval and rectangle
     * 
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    protected void setForOvalRect(double x1, double x2, double y1, double y2,String color) {
        setWidth(x1, x2);
        setHeight(y1, y2);
        setShape(x1, x2, y1, y2,color);
    }

    /**
     * set left,top,right,bottom of the shape
     * 
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    protected void setShape(double x1, double x2, double y1, double y2,String color) {
        setLeft(x1, x2);
        setTop(y1, y2);
        setRight();
        setBottom();
        setColor(color);
    }
    /**
     * check if the shape is in range
     * @param x x mouse position
     * @param y y mouse position
     * @return
     */
   public abstract boolean isInRange(double x, double y);
    
    /**
     * reset position after move shape
     * @param x new x mouse position
     * @param y new y mouse position
     */
    public void resetPosition(double x, double y) {
        left = x;
        top = y;
        right = x + width;
        bottom = y + height;

    }
    /**
     * left getter 
     * @return left
     */
    public double getLeft() {
        return left;
    }
    /**
     * left setter 
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     */
    public void setLeft(double x1, double x2) {
        left = Math.min(x1, x2);
    }
    /**
     * right getter 
     * @return right
     */
    public double getRight() {
        return right;
    }
    /**
     * right setter 
     */
    public void setRight() {
        this.right = left + width;
    }
    /**
     * top getter 
     * @return top
     */
    public double getTop() {
        return top;
    }
    /**
     * top setter 
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public void setTop(double y1, double y2) {
        top = Math.min(y1, y2);
    }
    /**
     * bottom getter
     * @return bottom
     */
    public double getBottom() {
        return bottom;
    }
    /**
     * bottom setter
     */
    public void setBottom() {
        this.bottom = top + height;
    }
    /**
     * width getter
     * @return width
     */
    public double getWidth() {
        return width;
    }
    /**
     * width setter for rectangle and oval
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     */
    public void setWidth(double x1, double x2) {
        this.width = Math.abs(x1 - x2);
       
    }
    /**
     * width setter for square and circle
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public void setWidth(double x1, double x2, double y1, double y2) {
        this.width = Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
    /**
     * height getter
     * @return height
     */
    public double getHeight() {
        return height;
    }
    /**
     * height setter for rectangle and oval
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public void setHeight(double y1, double y2) {
        this.height = Math.abs(y1 - y2);
    }
    /**
     * height setter for square and circle
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public void setHeight(double x1, double x2, double y1, double y2) {
        this.height = Math.min(Math.abs(x1 - x2), Math.abs(y1 - y2));

    }
    /**
     * Type getter
     * @return type
     */
    public String getType() {
        return type;
    }
    /**
     * Type setter
     */
    public abstract void setType();
    /**
     * color getter 
     * @return color
     */
    public String getColor() {
        return color;
    }
    /**
     * color setter
     * @param color to set
     */
    public void setColor(String color) {
        this.color = color;
    }


}
