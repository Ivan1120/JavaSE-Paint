

import java.util.ArrayList;
import java.util.Stack;

import com.sun.javafx.scene.CameraHelper.CameraAccessor;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Gui class
 * 
 * @author Chen,Wenqiang(Ivan) A00871834 Set 2A
 * @version Apr 7, 2019
 */
public class Gui extends Application {
    private Group root;
    private BorderPane borderPane;
    private Pane pane;
    private ComboBox<String> shapeBox, colorBox;
    private Button selectBtn;

    private Stack<Canvas> canvasArr;
    private Canvas curCanvas;
    private GraphicsContext curGc;
    private String curGcColor;

    private Board board;
    private double startX, startY, endX, endY;
    private boolean finishDrag;

    /**
     * start program
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start gui general layout
     */
    @Override
    public void start(Stage primaryStage) {
        board = new Board(this);

        primaryStage.setTitle("paint123");
        root = new Group();
        borderPane = new BorderPane();

        setToolBox();
        setCanvasLayout();
        borderPane.setOnKeyPressed(shapeDelete);
        root.getChildren().add(borderPane);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * set up canvas layout
     */
    private void setCanvasLayout() {
        pane = new Pane();
        canvasArr = new Stack<>();
        pane.getChildren().addAll(canvasArr);
        createCanvas();
        borderPane.setCenter(pane);
    }

    /**
     * set up function layout
     */
    private void setToolBox() {
        setColorBox();
        setShapeBox();
        setSelectBtn();
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.getChildren().addAll(shapeBox, colorBox, selectBtn);
        borderPane.setTop(hbox);

    }

    /**
     * create select button
     */
    private void setSelectBtn() {
        selectBtn = new Button("select");
        selectBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                board.setSelectMode(true);
                shapeBox.setValue("");
            }

        });

    }

    /**
     * create colorBox
     */
    private void setColorBox() {
        colorBox = new ComboBox<String>();
        String[] colorArr = { "black", "white", "red", "green", "blue", "grey", "pink", "lightgreen", "lightblue",
                "lightgray" };

        colorBox.getItems().addAll(colorArr);

        colorBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                curGcColor = newValue;
                setGcColor(newValue);
            }
        });
        colorBox.setValue("color");// set default value

    }

    /**
     * change GraphicsContext color based on the selection make on color box
     * 
     * @param curColor cur color of GraphicsContext
     */
    public void setGcColor(String curColor) {
        if (curColor == null)
            return;

        switch (curColor) {
        case "black":
            curGc.setFill(Color.BLACK);
            break;
        case "white":
            curGc.setFill(Color.WHITE);
            break;
        case "red":
            curGc.setFill(Color.RED);
            break;
        case "green":
            curGc.setFill(Color.GREEN);
            break;
        case "blue":
            curGc.setFill(Color.BLUE);
            break;
        case "grey":
            curGc.setFill(Color.GREY);
            break;
        case "pink":
            curGc.setFill(Color.PINK);
            break;
        case "lightgreen":
            curGc.setFill(Color.LIGHTGREEN);
            break;
        case "lightblue":
            curGc.setFill(Color.LIGHTBLUE);
            break;
        case "lightgrey":
            curGc.setFill(Color.LIGHTGREY);
            break;
        }
    }

    /**
     * set up shapeBox change the current draw shape based on selection
     */
    private void setShapeBox() {
        shapeBox = new ComboBox<String>();
        String[] shapArr = { "rectangle", "square", "oval", "circle" };
        shapeBox.getItems().addAll(shapArr);

        shapeBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue != null) {

                    board.setCurDrawShape(newValue);
                }

            }

        });
        shapeBox.setValue("shape");// set default value

    }

    /**
     * Create new Canvas and change to current Canvas,add event(press,drag,release)
     * to current canvas
     */
    public void createCanvas() {

        Canvas newCanvas = new Canvas(500, 500);

        curCanvas = newCanvas;
        curGc = curCanvas.getGraphicsContext2D();
        setGcColor(curGcColor);

        curCanvas.setOnMousePressed(canvasPress);
        curCanvas.setOnMouseDragged(canvasDrag);
        curCanvas.setOnMouseReleased(canvasReleased);

        canvasArr.add(newCanvas);

        pane.getChildren().add(newCanvas);

    }

    /**
     * when user press del key,if a shape has selected,clear canvas and remove
     * corresponding canvas and shape.
     */
    EventHandler<KeyEvent> shapeDelete = new EventHandler<KeyEvent>() {
        @Override
        public void handle(final KeyEvent keyEvent) {

            if (keyEvent.getCode() == KeyCode.DELETE) {

                if (board.isSelectMode() && board.isSelected()) {

                    if (board.getAllShapes().isEmpty())
                        return;

                    curGc.clearRect(0, 0, 500, 500);
                    canvasArr.remove(board.getIndexOfShape());
                    board.getAllShapes().remove(board.getIndexOfShape());

                }
            }
        }
    };

    /**
     * 1 when user press canvas,set selectMode true if shapeBox has a shape
     * selected,false otherwise 2 if selectMode,search from top to bottom to check
     * which shape has user click.if found,based on the index of shape,reset
     * curShape and curCanvas ,and set isSelected true, 3 if not selectMode,record
     * drawing start point;
     */
    EventHandler<MouseEvent> canvasPress = (new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            if (shapeBox.getValue().equals("")) {
                board.setSelectMode(true);

            } else {
                board.setSelectMode(false);

            }

            double x = e.getX();
            double y = e.getY();
            if (board.isSelectMode()) {
                board.setSelected(false);
                board.isInRange(x, y);
            } else {
                startX = x;
                startY = y;
            }
        }

    });

    /**
     * In selected mode, when a shape has selected based on the index of shape,reset
     * curShape and curCanvas
     * 
     * @param index index of the canvas
     */
    public void selectCanvas(int index) {
        curCanvas = canvasArr.get(index);
        curGc = curCanvas.getGraphicsContext2D();
        // curCanvas.toFront(); // to put current canvas to front
    }

    /**
     * when user enter selected mode and has selected a shape,move shape based on
     * mouse position also record end position when user finish dragged pass to
     * board to check how to move shape
     */
    EventHandler<MouseEvent> canvasDrag = (new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            if (board.isSelectMode() && board.isSelected()) {
                double moveX = e.getX();
                double moveY = e.getY();
                board.resetShape(moveX, moveY);
                endX = moveX;
                endY = moveY;
                finishDrag = true;

            }
        }

    });
    /**
     * if user enter selected mode and finish drag, board decide how to reset the
     * type of shape if user not in selected mode, create new shape and new layer
     * and add to allshape and all canvas
     */
    EventHandler<MouseEvent> canvasReleased = (new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            double x = e.getX();
            double y = e.getY();
            if (board.isSelectMode()) {
                if (finishDrag) {
                    board.handleSelectMode(endX, endY);

                    finishDrag = false;

                }
            } else {

                double diffX = Math.abs(x - startX);
                double diffY = Math.abs(y - startY);

                if (diffX > 1 && diffY > 1) {// to avoid accidently draw
                    board.createNewShape(startX, startY, x, y,curGcColor);
                }
                System.out.println("canvasSize" + canvasArr.size());
                System.out.println("allShape" + board.getAllShapes().size());

            }

        }

    });

    /**
     * Draw Rect
     * 
     * @param left   left top corner X of the shape
     * @param top    left top corner Y of the shape
     * @param width  width of the shape
     * @param height height of the shape
     */
    public void drawRec(double left, double top, double width, double height) {

        curGc.fillRect(left, top, width, height);

    }

    /**
     * Draw oval
     * 
     * @param left   left top corner X of the shape
     * @param top    left top corner Y of the shape
     * @param width  width of the shape
     * @param height height of the shape
     */
    public void drawOval(double left, double top, double width, double height) {
        curGc.fillOval(left, top, width, height);

    }

    /**
     * when client move shape , if rect selected ,gui clear canvas and redraw rect
     * 
     * @param left   left top corner X of the shape
     * @param top    left top corner Y of the shape
     * @param width  width of the shape
     * @param height height of the shape
     */
    public void resetRect(double left, double top, double width, double height) {
        curGc.clearRect(0, 0, 500, 500);
        drawRec(left, top, width, height);

    }

    /**
     * when client move shape , if oval selected ,gui clear canvas and redraw oval
     * 
     * @param left   left top corner X of the shape
     * @param top    left top corner Y of the shape
     * @param width  width of the shape
     * @param height height of the shape
     */
    public void resetOval(double left, double top, double width, double height) {
        curGc.clearRect(0, 0, 500, 500);
        drawOval(left, top, width, height);

    }

    /**
     * add new Canvas
     */
    public void addCanvas() {
        if (canvasArr.size() == board.getAllShapes().size()) {
            endX = 0;
            endY = 0;
            createCanvas();
        }

    }

}
