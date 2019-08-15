1 Gui class
  general layout: 	   stage --scene--group--borderPane
  For body:            borderPane-- pane--CanvasArr
  For function layout: boardPane--hbox--(shapeBox,colorBox,selectBtn)
 SelectBtn
  when click selectBtn,the board will enter selectMode and shapeBox will be reset.
 ColorBox
  when make selection on colorBox,it will change current GraphicsContext color
  ShapeBox
  if shapeBox does not has empty value,change the current draw shape based on selection
  CreateCanvas
  Create new Canvas and change to current Canvas,add event(press,drag,release) to current canvas
  DeleteShape
  when user enter del key,if a shape has selected,clear canvas and remove corresponding canvas and shape.
   CanvasPress
   1 when user press canvas,set selectMode true if shapeBox has a shape selected,false otherwise
   2 if selectMode,search from top to bottom to check which shape has user click
	 if found,based on the index of shape,reset curShape and curCanvas ,and set isSelected true,
   3 if not selectMode,record drawing start point;
   
   Canvas Drag
	1 when user enter selected mode and has selected a shape,move shape based on mouse position
	also record end position when user finish dragged 
	2 based on user move position and the corresponding shape, gui move shape
   Canvas Released
   1 if user enter selected mode and finish drag, board decide how to reset the type of shape
   2 if user not in selected mode, create new shape and new layer and add to allshape and all canvas
   
2 Board class
 2.1 board knows all shape.gui pass use input to board and board will check all shape and response to gui.
  board will do all the logical decision.
   2.2 curDrawShape for the shapebox selection.
	2.3 In select mode,board will know the range of each shape if they have been selected or not,and make decision
		and update the shape position
	2.4 In draw mode,board will let gui to create the corresponding shape based on user choice
3 The Shape
	square extends rectangle extends the shape and circle extends oval extends the shape
	the shape has left,right,top,bottom,width,height,for the corresponding boundary, and type color  for the corresponding type 
	because the method of set width and height for circle and square share are the same ,and same for rectangle and oval
	and all shape reset position at the same way so these method are all in the shape class
	as different method for isInRange, so each subclass has its own definition for isInRange 
4 Rectangle
  In rectangle constructor ,call the shape setForOvalRect and set its own type
	
5 Square
 In square constructor ,call the shape setForCircleSquare and set its own type
 and  square isInRange is the same as Rectangle,and it already inherit the method
	
6 oval
	In rectangle constructor ,call the shape setForOvalRect and set its own type
	i don't know if user click in oval range so put it the same method as rectangle 	
7 Circle
	In circle constructor ,call the shape setForCircleSquare and set its own type,radius,center x,center y
	circle has center x, y also radius instance member 
	circle has its own isInRange and resetPosition method


Note:
  1,for square and rectangle , i do not have x,y position for each corner
	I can add it in the future
  2, i dont know how to check if the click is in oval range, i only do circle range
  3, I don't have the button for delete shape and move shapes
  I think when you enter selectMode,you can use your mouse to move shape after you select shape
  and use del key to delete shape after you select shape
  4, My canvas sometimes has problem. when you create a shape and drag it, you will drag a new shape out of the original shape
	and the original shape does not disappear.but later, it disappear.
