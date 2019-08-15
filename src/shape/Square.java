package shape;

/**
 * 
 * @author Chen,Wenqiang(Ivan) A00871834 Set 2A
 * @version Apr 7, 2019
 */
public class Square extends Rectangle{
    /**
     * Square constructor set left,right,top,bottom boundary, width,heigth,type
     * @param x1 start x1 mouse position of the shape
     * @param x2 end x2 mouse position of the shape
     * @param y1 start y mouse position of the shape
     * @param y2 end y mouse position of the shape
     */
    public Square(double x1, double x2, double y1, double y2,String color) {
        setForCircleSquare(x1, x2, y1, y2,color);
        setType();
    }

    /**
     * type setter
     */
    public void setType() {
        type="square";
    }


}
