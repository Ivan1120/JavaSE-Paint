package shape;


/**
 * Circle
 * 
 * @author Chen,Wenqiang(Ivan) A00871834 Set 2A
 * @version Apr 7, 2019
 */
public class Circle extends Oval {
    private double centerX,centerY;
   
    private double radius;

    /**
     * 
     * Circle constructor set left,right,top,bottom boundary,
     * width,heigth,type,radius,center x,center y
     * 
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public Circle(double x1, double x2, double y1, double y2,String color) {
        setForCircleSquare(x1, x2, y1, y2,color);
        setType();
        setRadius();
        setCenterX();
        setCenterY();

    }
    /**
     * type setter
     */
    public void setType() {
        type = "circle";
    }
    /**
     * to check if user click in shape range 
     */
    public boolean isInRange(double x2, double y2) {
        double distanceX = Math.abs(x2 - centerX);
        double distanceY = Math.abs(y2 - centerY);

        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (distance > radius)
            return false;
        else
            return true;
    }
    /**
     * reset position after user move
     */
    public void resetPosition(double x, double y) {
        super.resetPosition(x, y);
        setCenterX();
        setCenterY();

    }
    /**
     * radius getter
     * @return radius getter
     */
    public double getRadius() {
        return radius;
    }
    /**
     * radius setter
     */
    public void setRadius() {
        this.radius = width / 2;
    }
    /**
     * center x getter 
     * @return center x
     */
    public double getCenterX() {
        return centerX;
    }
    /**
     * center x setter
     */
    public void setCenterX() {
        centerX = left + radius;

    }
    /**
     * center y getter
     * @return center y
     */
    public double getCenterY() {
        return centerY;
    }
    /*
     * center y setter
     */
    public void setCenterY() {
        centerY = top + radius;
    }

}
