//
//  Clipper.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Niyati Shah
//  Cite: [1] Wikipedia for intersection of line formula -Line line_intersection
//
//
// Object for performing clipping
//
///

import java.awt.*;
import java.util.ArrayList;

public class clipper {

    ///
    // clipPolygon
    //
    // Clip the polygon with vertex count in and vertices inx/iny
    // against the rectangular clipping region specified by lower-left corner
    // (llx,lly) and upper-right corner (urx,ury). The resulting vertices are
    // placed in outx/outy.
    //
    // The routine should return the the vertex count of the polygon
    // resulting from the clipping.
    //
    // @param in the number of vertices in the polygon to be clipped
    // @param inx - x coords of vertices of polygon to be clipped.
    // @param iny - y coords of vertices of polygon to be clipped.
    // @param outx - x coords of vertices of polygon resulting after clipping.
    // @param outy - y coords of vertices of polygon resulting after clipping.
    // @param llx - x coord of lower left of clipping rectangle.
    // @param lly - y coord of lower left of clipping rectangle.
    // @param urx - x coord of upper right of clipping rectangle.
    // @param ury - y coord of upper right of clipping rectangle.
    //
    // @return number of vertices in the polygon resulting after clipping
    //
    ///
    public int clipPolygon(int in, float inx[], float iny[],
                  float outx[], float outy[],
                  float llx, float lly, float urx, float ury)
    {
       //Create an arraylist of X and Y points
        ArrayList<Float> X = new ArrayList<Float>();
        ArrayList<Float> Y = new ArrayList<Float>();

        int count = 1;
        float px,py,qx,qy,rx,ry,sx,sy;
        // using the upper and lower end of clipping co-ordinates,
        // create all 4 points of the clipping co-ordinates
        px = llx;
        py =ury;

        qx = urx;
        qy = ury;

        rx = urx;
        ry = lly;

        sx = llx;
        sy = lly;

        // At first all points are assumed to be inside the clipping rectangle
        //so are added to the arraylist
        for(int index = 0;index<inx.length;index++){
            X.add(inx[index]);
            Y.add(iny[index]);
        }

        // For all 4 sides of the rectange, clip the polygon
        // by taking 1 side at a time
        SHPC(X,Y,px,py,qx,qy,0);        //Top side
        SHPC(X,Y,rx,ry,qx,qy,0);        //Right side
        SHPC(X,Y,px,py,sx,sy,1);        //left side
        SHPC(X,Y,sx,sy,rx,ry,1);        //Bottom side

        //After the clipping is performed
        //Add the final points to outx and outy array
        //for using it to fill the clipped portion
        for(int index =0;index<X.size();index++) {

                   outx[index] = X.get(index);
                   outy[index] = Y.get(index);

        }
        // count is equal to the number of points in the new array
        count = X.size();

        return count; // should return number of vertices in clipped poly.
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
    public void SHPC(ArrayList<Float> X,ArrayList<Float> Y,float px,float py,float qx, float qy, int choice ){
        int indexj;
        float x1,y1,x2,y2;
        int count =0;

        float[][]temp ;
        ArrayList<Float> Xn = new ArrayList<Float>();
        ArrayList<Float> Yn = new ArrayList<Float>();
        System.out.println("SHPC");

        for(int index =0;index<X.size();index++){

            Xn.add(X.get(index));
            Yn.add(Y.get(index));
            System.out.println("x1,y1 "+X.get(index)+" "+Y.get(index));
            System.out.println("In for");

        }

        X.clear();
        Y.clear();
        for (int index = 0; index < Xn.size(); index++) {
            System.out.println("In for 2");
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
            System.out.println("index "+index);
            System.out.println("********");
            System.out.println("x1,y1,x2,y2 "+x1+" "+y1+" "+x2+" "+y2);
            temp = checkLine(x1, y1, x2, y2, px, py, qx, qy, choice);

            //if line is parrallel/overlapping
            // keep both points
            if (temp[0][0] == 999) {
            }


            // if both points are outside the clipping side
            // remove the first point
            //if first point is index 0 do not remove that point
            else if (temp[0][0] == 0 && temp[0][1] == 0) {
                if (index != 0) {

                }
            } else {

                // replace the points on the arraylist with the clipped points

                X.add(count,temp[0][0]);
                Y.add(count,temp[0][1]);
                count++;
                X.add(count,temp[1][0]);
                Y.add(count,temp[1][1]);
                count++;
                System.out.println("added x1,y1,x2,y2 "+temp[0][0]+" "+temp[0][1]+" "+temp[1][0]+" "+temp[1][1]);
            }
        }
        System.out.println();
        for(int i=0;i<X.size();i++){

            System.out.println("x1,y1 "+X.get(i)+" "+Y.get(i));

        }


        int j;
        System.out.println();
        for(int i=0;i<X.size();i++){
            if (i + 1 == X.size()) {        //   to  get the first and the last points
                j = 0;
            } else {
                j = i + 1;
            }
            System.out.println("final x1,y1,x2,y2 "+X.get(i)+" "+Y.get(i)+" "+X.get(j)+" "+Y.get(j));
            if(X.get(i).equals(X.get(j)) && Y.get(i).equals(Y.get(j))){
                X.remove(j);
                Y.remove(j);
            }
          //  System.out.println("x1,y1 "+X.get(i)+" "+Y.get(i));
        }

        System.out.println();
        for(int i=0;i<X.size();i++){

            System.out.println("x1,y1 "+X.get(i)+" "+Y.get(i));

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

    public float[][] checkLine(float x1,float y1,float x2,float y2, float ax, float ay, float bx, float by, int choice) {
        float [][] intersection = new float[2][2];
        float[] temp;

        // choice = 1 : clip line is bottom or left side
        if(choice==1) {
            //clip line is bottom  side
            if (ay == by) {
                // if both points are above the clipping side
                if(y1>=ay && y2 >=ay){
                    System.out.println("in 1");
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is above clipping side
                //&& 2nd point is below clipping side
                else if (y1 >=ay && y2<ay ){
                    System.out.println("in 2");
                    System.out.println("OG x1,y1,x2,y2 "+x1+" "+y1+" "+x2+" "+ y2);
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
                    System.out.println("x1,y1,x2,y2 "+intersection[0][0]+" "+intersection[0][1]+" "+intersection[1][0]+" "+ intersection[1][1]);
                }   // if 1st point is below clipping side
                    //&& 2nd point is above clipping side
                else if (y1 <ay && y2>=ay ){
                    System.out.println("in 3");
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are below clipping side
                else if(y1<ay && y2<ay ){
                    System.out.println("in 4");
                    intersection[0][0] = 0;
                    intersection[0][1] = 0;
                    intersection[1][0] = 0;
                    intersection[1][1] = 0;
                }
                // if clipping line is right side
            }else if(ax == bx){
                // if both points are to the right the clipping side
                if(x1>=ax && x2 >=ax){
                    System.out.println("in 5");
                    System.out.println("OG x1,y1,x2,y2 "+x1+" "+y1+" "+x2+" "+ y2);
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                    System.out.println("x1,y1,x2,y2 "+intersection[0][0]+" "+intersection[0][1]+" "+intersection[1][0]+" "+ intersection[1][1]);
                    // if 1st point is to the right &
                    // the 2nd to the left the clipping side
                }else if (x1 >= ax && x2<ax ) {
                    System.out.println("in 6");
                    System.out.println("OG x1,y1,x2,y2 "+x1+" "+y1+" "+x2+" "+ y2);
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

                    System.out.println("x1,y1,x2,y2 "+intersection[0][0]+" "+intersection[0][1]+" "+intersection[1][0]+" "+ intersection[1][1]);
                }
                // if 2nd point is to the right &
                // the 1st to the left the clipping side
                else if (x1 < ax && x2>=ax ){
                    System.out.println("in 7");
                    System.out.println("OG x1,y1,x2,y2 "+x1+" "+y1+" "+x2+" "+ y2);
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                    System.out.println("x1,y1,x2,y2 "+intersection[0][0]+" "+intersection[0][1]+" "+intersection[1][0]+" "+ intersection[1][1]);
                }
                // if both points are to the left of the clipping side
                else if(x1<ax && x2<ax ){
                    System.out.println("in 8");
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
                    System.out.println("in 9");
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is below clipping side
                //&& 2nd point is above clipping side
                else if (y1 <=ay && y2>ay ){
                    System.out.println("in 10");
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
                    System.out.println("in 11");
                    temp = getIntersection(x1,y1,x2,y2,ax,ay,bx,by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are to the below of the clipping side
                else if(y1>ay && y2>ay ){
                    System.out.println("in 12");
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
                    System.out.println("in 3");
                    intersection[0][0] = x1;
                    intersection[0][1] = y1;
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if 1st point is right clipping side
                //&& 2nd point is left clipping side
                else if (x1 <= ax && x2>ax ) {
                    System.out.println("in 14");
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
                    System.out.println("in 15");
                    temp = getIntersection(x1, y1, x2, y2, ax, ay, bx, by);
                    intersection[0][0] = temp[0];
                    intersection[0][1] = temp[1];
                    intersection[1][0] = x2;
                    intersection[1][1] = y2;
                }
                // if both points are to the right of the clipping side
                else if(x1>ax && x2>ax ){
                    System.out.println("in 16");
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
    public float[] getIntersection(float ax1, float ay1, float ax2, float ay2,      // Line A
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
