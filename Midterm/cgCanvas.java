//  cgCanvas.java 20155
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Niyati Shah (nxs6032)
//

///
// This is a simple canvas class for adding functionality for the
// 2D portion of Computer Graphics.
//
///


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class cgCanvas extends simpleCanvas {

    public static LinkedList<Polygon> poly_draw = new LinkedList<Polygon>();
    public static int number = 0;
    public static Matrix Transform;
    public static Matrix ViewPort;
    public static Polygon clippingWindow;
    public static int xvmin,xvmax, yvmin, yvmax;



    ///
    // Constructor
    //
    // @param w width of canvas
    // @param h height of canvas
    ///

    cgCanvas (int w, int h)
    {
        super (w, h);
        Transform = Matrix.identity(3);
        ViewPort = Matrix.identity(3);

        // YOUR IMPLEMENTATION HERE if you need to modify the constructor
    }

    ///
    // addPoly - Adds and stores a polygon to the canvas.  Note that this
    //           method does not draw the polygon, but merely stores it for
    //           later draw.  Drawing is initiated by the draw() method.
    //
    //           Returns a unique integer id for the polygon.
    //
    // @param x - Array of x coords of the vertices of the polygon to be added
    // @param y - Array of y coords of the vertices of the polygin to be added
    // @param n - Number of verticies in polygon
    //
    // @return a unique integer identifier for the polygon
    ///

    public int addPoly (float x[], float y[], int n)
    {
        // YOUR IMPLEMENTATION HERE
        int[] X = new int[x.length];
        int[] Y = new int[y.length];
        Polygon P = new Polygon();
        //initializing points array for the polygon
        for(int indexi=0; indexi< x.length;indexi++){
            X[indexi] = (int)x[indexi];
            Y[indexi] = (int)y[indexi];
        }
        for (int i=0; i < n; i++){
            P.addPoint (X[i], Y[i]);
            
        }
        //Creates polygon object to hold the points for it
        poly_draw.add(number,P);
        // REMEMBER TO RETURN A UNIQUE ID FOR THE POLYGON
        return number++;
    }


    ///
    // drawPoly - Draw the polygon with the given id.  Should draw the
    //        polygon after applying the current transformation on the
    //        vertices of the polygon.
    //
    // @param polyID - the ID of the polygon to be drawn
    ///

    public void drawPoly (int polyID)
    {
        // YOUR IMPLEMENTATION HERE
     
        Polygon P ;
        P = poly_draw.get(polyID);
        int indexj,x0,x1,y0,y1;
        // Apply clipping on the points
        P = applyClipping(P);	
        // Apply transformation on the points
        P = applyTransform(P);
        // Apply view transformation on the points 
        P = applyView(P);
        //Create & initialize edge table for polygon fill
        ArrayList<Node> [] edgeTable = new ArrayList[yvmax+1];
        Arrays.fill(edgeTable,null);
        

        for(int index = 0;index<P.npoints;index++) {
            if (index + 1 == P.npoints) {        //   to  get the first and the last points
                indexj = 0;
            } else {
                indexj = index+1;
            }
            x0 = P.xpoints[index];
            x1 = P.xpoints[indexj];
            y0 = P.ypoints[index];
            y1 = P.ypoints[indexj];


            //If they are figures, then check if
            //values are within the viewport window
            // if not change them to viewport co-ordinates

                if (x0 < xvmin) {
                    x0 = xvmin;
                }
                if (x0 > xvmax) {
                    x0 = xvmax - 1;
                }
                if (x1 > xvmax) {
                    x1 = xvmax - 1;
                }
                if (x1 < xvmin) {
                    x1 = xvmin;
                }

                if (y0 < yvmin) {
                    y0 = yvmin;
                }

                if (y0 > yvmax) {
                    y0 = yvmax - 1;
                }

                if (y1 > yvmax) {
                    y1 = yvmax - 1;
                }

                if (y1 < yvmin) {
                    y1 = yvmin;
                }
            float slope, X;
            int ymx,ymn;
            slope = getSlope(x0,y0,x1,y1);
            if(y0>y1){
                ymx = y0;
                ymn = y1;
                X = x1;
            }else{
                ymx = y1;
                ymn = y0;
                X = x0;
            }
            // Creating edge table for the given points
            addEdge(edgeTable,X,ymx,ymn,slope);          
        }
        // perform fill colour on the polygon
        fillColor(edgeTable);
    }

    /*
     * Function to perform fill colour on the given polygon
     * 
     * param: Arraylist of type node: edgetable
     */
    public void fillColor(ArrayList<Node>[] edgeTable ) {

        ArrayList<Node> ActiveList = new ArrayList<>();
        
        for (int scanY = 0; scanY < edgeTable.length; scanY++) {
        	// Remove edges whose ymax has been reached
            removeMaxEdge(ActiveList, scanY);

            // add edge if present at that y point in the edgetable
            if(edgeTable[scanY]!=null){
                AddToActiveList(ActiveList,edgeTable,scanY);
                sortNode(ActiveList);
            }
            
            // choose every alternate edge (even parity)
            // to start the paint function from
            
            for(int j =0;j<ActiveList.size();j+=2){
               // 1st even parity edge to start the paint 
                float xmin = ActiveList.get(j).x;
               //2nd odd parity edge to end the paint at
                float xmax = ActiveList.get(j+1).x;
        
                // paint the line at that scan line Y
                while(xmin<xmax){              
                    setPixel((int)xmin,scanY);
                    xmin++;
                }
               
            }
            // update the points in the active list
            for(int k =0;k<ActiveList.size();k++){
                ActiveList.get(k).x += ActiveList.get(k).slope;
                
            }


        }

    }

    /*
     * Function to remove any edges that have reached their y Max value.
     * 
     * param: active list
     * param: y max value
     */
    public void removeMaxEdge(ArrayList<Node> ActiveList, int maxY){
        int i=0;
        while(i <ActiveList.size()){
        	//Check the active list if any edge has reached its ymax value.
            if(ActiveList.get(i).yMax == maxY){              
                ActiveList.remove(i);
                i--;
            }
            i++;
        }
    }

    /*
     * Sort function for the Activelist  to  sort the nodes in ascending order
	 * of X values and if they are same then the ascending order of slope value.
	 * 
	 * param: Active list of nodes
	 * 
     */
    public void sortNode(ArrayList<Node> nodeList) {
        Node temp;
        int size = nodeList.size();
        for(int i=1;i<size;i++){
            for(int j=i;j>0;j--){
            	
        //   sort on the base of x (if x1<x2)
                if(nodeList.get(j).x < nodeList.get(j-1).x){
                    temp = nodeList.get(j);
                    nodeList.set(j,nodeList.get(j-1));
                    nodeList.set((j-1), temp);
                }else if(nodeList.get(j).x == nodeList.get(j-1).x){			// if x1=x2 
                    if(nodeList.get(j).slope < nodeList.get(j-1).slope){	// sort on the base of slope
                        temp = nodeList.get(j);
                        nodeList.set(j,nodeList.get(j-1));
                        nodeList.set((j-1), temp);
                    }
                }
            }
        }
    }

    /*
     * Function to add nodes to the active edge list
     * 
     * param: Active edge list
     * param: edge table
     * param: pointer to the current y value
     * 
     */
    public void AddToActiveList(ArrayList<Node> ActiveList,ArrayList<Node>[]edgeTable,int scanY){
        for(int i=0;i<edgeTable[scanY].size();i++){
            ActiveList.add(edgeTable[scanY].get(i));
        }
    }

    /*
     * Function to get the slope of the given line
     * as per the scanline fill algorithm
     * 
     * param: x1
     * param: x2
     * param: y1
     * param: y2
     * 
     */
    public float getSlope(int x1, int y1,int x2,int y2){
        float slope, dx, dy;
        dx = x2-x1;
        dy = y2-y1;
        if(dy ==0){			// if dy =0 slope is infinity
            slope = 999;
        }else{
            slope = dx/dy;	
        }
        return slope;
    }

    /*
     * function to add edge to the edge table
     * 
     * param:edgetable
     * param:X
     * param: ymax
     * param: slope
     *
     */
    public void addEdge(ArrayList<Node>[] edgeTable, float X,int ymx,int ymn,float slope ) {
       
        if (slope != 999) {		// If slope is not infinity then add an edge
            if(edgeTable[ymn]==null){	// if this is the 1st node to that point on teh edge table
                edgeTable[ymn] = new ArrayList<>();
            }
            edgeTable[ymn].add(new Node(ymx,X,slope));	// if node is any node
        }
    }

    /*
     * Funtion to apply transformation to the polygon
     * 
     * param: Polygon
     */
    public static Polygon applyTransform(Polygon P){
        Matrix points;
        double[][] temparray = new double[3][1];

        for (int i =0;i<P.npoints;i++){
            temparray[0][0] = P.xpoints[i];
            temparray [1][0] = P.ypoints[i];
           
            temparray[2][0] = 1;
            points = new Matrix(temparray);
            Matrix multiply = Transform.times(points);
        
            P.xpoints[i] = (int)multiply.getvalue(0,0);
            P.ypoints[i] = (int)multiply.getvalue(1,0);
          
        }
        return P;
    }


    ///
    // clearTransform - Set the current transformation to the identity matrix.
    ///

    public void clearTransform()
    {
        // YOUR IMPLEMENTATION HERE

        Transform = Matrix.identity(3);

    }

    ///
    // translate - Add a translation to the current transformation by
    //             pre-multiplying the appropriate translation matrix to
    //             the current transformation matrix.
    //
    // @param x - Amount of translation in x
    // @param y - Amount of translation in y
    ///


    public void translate (float x, float y)
    {
        // YOUR IMPLEMENTATION HERE
       
        Matrix Translate = Matrix.identity(3);
        Translate.setvalue(0,2,x);
        Translate.setvalue(1,2,y);
        Transform = Translate.times(Transform);
      
    }

    ///
    // rotate - Add a rotation to the current transformation by
    //          pre-multiplying the appropriate rotation matrix to the
    //          current transformation matrix.
    //
    // @param degrees - Amount of rotation in degrees
    ///



    public void rotate (float degrees)
    {
        // YOUR IMPLEMENTATION HERE
   

        double degree = Math.toRadians(degrees);

        Matrix Rotate = Matrix.identity(3);
        Rotate.setvalue(0,0,Math.cos(degree));
        Rotate.setvalue(1,0,Math.sin(degree));
        Rotate.setvalue(0,1,(-1)*(Math.sin(degree)));
        Rotate.setvalue(1,1,Math.cos(degree));
        Transform = Rotate.times(Transform);
   

    }
    ///
    // scale - Add a scale to the current transformation by pre-multiplying
    //         the appropriate scaling matrix to the current transformation
    //         matrix.
    //
    // @param x - Amount of scaling in x
    // @param y - Amount of scaling in y
    ///



    public void scale (float x, float y)
    {
        Matrix Scale = Matrix.identity(3);
        Scale.setvalue(0,0,x);
        Scale.setvalue(1,1,y);
        Transform = Scale.times(Transform);
       
    }

    ///
    // setClipWindow - defines the clip window
    //
    // @param bottom - y coord of bottom edge of clip window (in world coords)
    // @param top - y coord of top edge of clip window (in world coords)
    // @param left - x coord of left edge of clip window (in world coords)
    // @param right - x coord of right edge of clip window (in world coords)
    ///

    public void setClipWindow (float bottom, float top, float left, float right)
    {
        // YOUR IMPLEMENTATION HERE
        clippingWindow = new Polygon();
   
        float[] X = { left,right,};
        float[] Y = { top,bottom};
      
        for(int index =0;index<X.length;index++){
     
            clippingWindow.addPoint((int)X[index],(int)Y[index]);
        }
        
    }

    ///
    // setViewport - defines the viewport
    //
    // @param xmin - x coord of lower left of view window (in screen coords)
    // @param ymin - y coord of lower left of view window (in screen coords)
    // @param width - width of view window (in world coords)
    // @param height - width of view window (in world coords)
    ///

    public void setViewport (int x, int y, int width, int height)
    {
        // YOUR IMPLEMENTATION HERE
        
        xvmin = x;
        xvmax = x+width;
        yvmin =y;
        yvmax =y+height;

        // set values of clipping window
        float left =clippingWindow.xpoints[0];
        float right =clippingWindow.xpoints[1];
        float top = clippingWindow.ypoints[0];
        float bottom = clippingWindow.ypoints[1];

        // set values for sx, sy,tx and ty
        float sx = (float)(xvmax-xvmin)/(float)(right-left);
        float sy = (float)(yvmax- yvmin)/(float)(top-bottom);
        float tx =(float)((right*xvmin)-(left*xvmax))/(float)(right-left);
        float ty = (float)((top*yvmin)-(bottom*yvmax))/(float)(top-bottom);

        // set value for viewport matrix
        ViewPort.setvalue(0,0,sx);
        ViewPort.setvalue(1,1,sy);
        ViewPort.setvalue(0,2,tx);
        ViewPort.setvalue(1,2,ty);
      

    }

    /*
     * funtion to apply view to the polygon
     * 
     * paramL polygon
     */
    public static Polygon applyView(Polygon P){
        Matrix points;
        double[][] temparray = new double[3][1];

        for (int i =0;i<P.npoints;i++){
            temparray[0][0] = P.xpoints[i];
            temparray [1][0] = P.ypoints[i];
            temparray[2][0] = 1;
            points = new Matrix(temparray);
            Matrix view = ViewPort.times(points);
           
            P.xpoints[i] = (int)view.getvalue(0,0);
            P.ypoints[i] = (int)view.getvalue(1,0);
           
        }

        return P;
    }

    /*
     *Function to apply clipping to the polygon
     *
     * param:polygon
     */
    public static Polygon applyClipping(Polygon P){

        ArrayList<Float> X = new ArrayList<Float>();
        ArrayList<Float> Y = new ArrayList<Float>();
        float px,py,qx,qy,rx,ry,sx,sy;
        for(int index = 0;index<P.npoints;index++){
            X.add((float)P.xpoints[index]);
            Y.add((float)P.ypoints[index]);
        }
        /*
        	p ____Q
        	 |____|
        	R 	   S
        */
        // set clipping wondow points
        px = clippingWindow.xpoints[0];
        py = clippingWindow.ypoints[0];

        qx = clippingWindow.xpoints[1];
        qy = clippingWindow.ypoints[0];

        rx = clippingWindow.xpoints[1];
        ry = clippingWindow.ypoints[1];

        sx = clippingWindow.xpoints[0];
        sy = clippingWindow.ypoints[1];

        //Call SHPC function for each side of clipping window
        SHPC(X,Y,px,py,qx,qy,0);        //Top side
        SHPC(X,Y,rx,ry,qx,qy,0);        //Right side
        SHPC(X,Y,px,py,sx,sy,1);        //left side
        SHPC(X,Y,sx,sy,rx,ry,1);        //Bottom side

        Polygon Temp = new Polygon();
     
        for(int index =0;index<X.size();index++){
            Temp.addPoint(X.get(index).intValue(), Y.get(index).intValue());

        }
        return Temp;
    }

    /*
    Sudherland Hodgeman polygon clipping function
    param: Arraylist X:     Contains X co-ordinates
    param: Arraylist Y:     contains Y co-ordinates
    param: float px,qx:     x-coordiante of clipping side
    param: float py,qy:     y-coordiante of clipping side
    param: int choice:      1 -> points above or to right are inside
                            2 -> points below or to left are inside
    returns: None
     */
    public static void SHPC(ArrayList<Float> X,ArrayList<Float> Y,float px,float py,float qx, float qy, int choice ){
        int indexj;
        float x1,y1,x2,y2;
        int count =0;

        float[][]temp ;
        ArrayList<Float> Xn = new ArrayList<Float>();
        ArrayList<Float> Yn = new ArrayList<Float>();

        //Add points to temporary arraylist
        for(int index =0;index<X.size();index++){
            Xn.add(X.get(index));
            Yn.add(Y.get(index));
        }

        X.clear();
        Y.clear();
        for (int index = 0; index < Xn.size(); index++) {

            if (index + 1 == Xn.size()) {        //   to  get the first and the last points
                indexj = 0;
            } else {
                indexj = index + 1;
            }
            x1 = Xn.get(index);
            y1 = Yn.get(index);
            x2 = Xn.get(indexj);
            y2 = Yn.get(indexj);

            //Check where the line belongs wrt the clip side
            temp = checkLine(x1, y1, x2, y2, px, py, qx, qy, choice);

            //if line is parrallel/overlapping
            // keep both points
            if (temp[0][0] == 999) {
            }
            // if both points are outside the clipping side none are added
            else if (temp[0][0] == 0 && temp[0][1] == 0) {
            }
            else {
                // add the points on the arraylist with the clipped points
                X.add(count,temp[0][0]);
                Y.add(count,temp[0][1]);
                count++;
                X.add(count,temp[1][0]);
                Y.add(count,temp[1][1]);
                count++;
            }
        }

        //Remove extra same points added while clipping
        int j;
        for(int i=0;i<X.size();i++){
            if (i + 1 == X.size()) {        //   to  get the first and the last points
                j = 0;
            } else {
                j = i + 1;
            }
            if(X.get(i).equals(X.get(j)) && Y.get(i).equals(Y.get(j))){
                X.remove(j);
                Y.remove(j);
            }

        }


    }

    /*
    Check function: used to check if the points on the line are within the clip boundary.
    If not then get new clipped points for the line
    param:  x1,x2,y1,y2:     x and y co-ordinates of the line to be checked
    param:  ax,ay,bx,by:     x and y co-ordinates of the clipping line
    param:  int choice:      1 -> points above or to right are inside
                            2 -> points below or to left are inside
    return: 2D float array of clipped points
     */

    public static float[][] checkLine(float x1,float y1,float x2,float y2, float ax, float ay, float bx, float by, int choice) {
        float [][] intersection = new float[3][3];
        float[] temp;

        // choice = 1 : clip line is bottom or left side
        if(choice==1) {
            //clip line is bottom  side
            if (ay == by) {
                // if both points are above the clipping side
                if(y1>=ay && y2 >=ay){
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is above clipping side
                //&& 2nd point is below clipping side
                else if (y1 >=ay && y2<ay ){
                    intersection[0][0] = x1;
                    intersection[0][1] =y1;
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    //If the 1st and 2nd point is same
                    if(x1==temp[0]&&y1 ==temp[1]){
                        intersection[1][0] = x2;
                        intersection[1][1] = y2;
                    }else {
                        intersection[1][0] = temp[0];
                        intersection[1][1] = temp[1];
                    }
                }   // if 1st point is below clipping side
                //&& 2nd point is above clipping side
                else if (y1 <ay && y2>=ay ){
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are below clipping side
                else if(y1<ay && y2<ay ){
                    intersection[0][0] = 0;
                    intersection[0][1] = 0;
                    intersection[1][0] = 0;
                    intersection[1][1] = 0;
                }
                // if clipping line is right side
            }else if(ax == bx){
                // if both points are to the right the clipping side
                if(x1>=ax && x2 >=ax){
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                    // if 1st point is to the right &
                    // the 2nd to the left the clipping side
                }else if (x1 >= ax && x2<ax ) {
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    //If the 1st and 2nd point is same
                    if(x2==temp[0]&&y2 ==temp[1]){
                        intersection[1][0] = x2;
                        intersection[1][1] = y2;
                    }else {
                        intersection[1][0] = temp[0];
                        intersection[1][1] = temp[1];
                    }
                }
                // if 2nd point is to the right &
                // the 1st to the left the clipping side
                else if (x1 < ax && x2>=ax ){
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are to the left of the clipping side
                else if(x1<ax && x2<ax ){
                    intersection[0][0] = 0;
                    intersection[0][1] = 0;
                    intersection[1][0] = 0;
                    intersection[1][1] = 0;
                }
            }
            // choice = 2 : clip line is above or right side
        }else{
            //clip line is above line
            if (ay == by) {
                // if both points are below the clipping side
                if(y1<=ay && y2 <=ay){
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is below clipping side
                //&& 2nd point is above clipping side
                else if (y1 <=ay && y2>ay ){
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    //If the 1st and 2nd point is same
                    if(x1==temp[0]&&y1 ==temp[1]){
                        intersection[1][0] = x2;
                        intersection[1][1] = y2;
                    }else {
                        intersection[1][0] = temp[0];
                        intersection[1][1] = temp[1];
                    }
                }
                // if 1st point is above clipping side
                //&& 2nd point is below clipping side
                else if (y1 >ay && y2<=ay ){
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are to the below of the clipping side
                else if(y1>ay && y2>ay ){
                    intersection[0][0] = 0;
                    intersection[0][1] = 0;
                    intersection[1][0] = 0;
                    intersection[1][1] = 0;
                }
            }
            //clip line is left  side
            else if(ax == bx){
                // if both points are below the clipping side
                if(x1<=ax && x2 <=ax){
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is right clipping side
                //&& 2nd point is left clipping side
                else if (x1 <= ax && x2>ax ) {
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    //If the 1st and 2nd point is same
                    if(x1==temp[0]&&y1 ==temp[1]){
                        intersection[1][0] = x2;
                        intersection[1][1] = y2;
                    }else {
                        intersection[1][0] = temp[0];
                        intersection[1][1] = temp[1];
                    }
                }
                // if 1st point is left clipping side
                //&& 2nd point is right clipping side
                else if (x1 > ax && x2<=ax ){
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are to the right of the clipping side
                else if(x1>ax && x2>ax ){
                    intersection[0][0] = 0;
                    intersection[0][1] = 0;
                    intersection[1][0] = 0;
                    intersection[1][1] = 0;
                }
            }
        }
        return intersection;
    }
    /*
       get intersection function to find the intersection point of 2 lines
       param:      x1,x2,y1,y2:     x and y co-ordinates of the line to be checked
       param:      ax,ay,bx,by:     x and y co-ordinates of the clipping line
       return:     array containing point x and y
       */
    public static float[] getIntersection(float ax1, float ay1, float ax2, float ay2,      // Line A
                                          float bx1, float by1, float bx2, float by2){    //  Line B

        float intersection[] = new float[2];
        float x =999;
        float y=999;
        // If lines are parallel then let x = 999 and y = 999
        if((((ax1-ax2)*(by1-by2))-((ay1-ay2)*(bx1-bx2))) == 0){
            intersection[0] = x;
            intersection[1] = y;
        }else {
            // if lines are not parallel
            // formula to find intersection of line [1]
            x = ((ax1 * ay2 - ay1 * ax2) * (bx1 - bx2) - (ax1 - ax2) * (bx1 * by2 - by1 * bx2)) / (((ax1 - ax2) * (by1 - by2)) - ((ay1 - ay2) * (bx1 - bx2)));
            y = ((ax1 * ay2 - ay1 * ax2) * (by1 - by2) - (ay1 - ay2) * (bx1 * by2 - by1 * bx2)) / (((ax1 - ax2) * (by1 - by2)) - ((ay1 - ay2) * (bx1 - bx2)));
            intersection[0] = x;
            intersection[1] = y;
        }
        return intersection;
    }

}