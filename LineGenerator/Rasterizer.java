package Hw1;//
//  Hw1.HW20.Rasterizer1.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  YOUR_NAME_HERE
//

///
// 
// A simple class for performing rasterization algorithms.
//
///

public class Rasterizer {
    
    ///
    // number of scanlines
    ///

    int n_scanlines;
    
    ///
    // Constructor
    //
    // @param n number of scanlines
    //
    ///

    Rasterizer (int n)
    {
        n_scanlines = n;
    }
    
    ///
    // Draw a line from (x0,y0) to (x1, y1) on the Hw1.simpleCanvas C.
    //
    // Implementation should be using the Midpoint Method
    //
    // You are to add the implementation here using only calls
    // to C.setPixel()
    //
    // @param x0 x coord of first endpoint
    // @param y0 y coord of first endpoint
    // @param x1 x coord of second endpoint
    // @param y1 y coord of second endpoint
    // @param C  The canvas on which to apply the draw command.
    ///

    public void drawLine (int x0, int y0, int x1, int y1, simpleCanvas C)
    {
            int dE, dNE, x, y, d, temp;

            int dy = Math.abs(y1 -y0);
            int dx = Math.abs(x1-x0);
            dE = 2 * dy;
            dNE = 2 * (dy - dx);
            d = dE - dx;

            if (x0 > x1) {                 // 1st pt is greater than 2nd pt
                temp = x0;                  // draws 5th octate
                x0 = x1;
                x1 = temp;
                temp = y0;
                y0 = y1;
                y1 = temp;
            }

            if (dy == 0) {                   //Horizontal Line
                y = y0;
                for (x = x0; x <= x1; x++) {
                    C.setPixel(x, y);
                }

            } else if (dx == 0) {             // Vertical Line
                if (y0 > y1) {                 //invert line if drawing from top to bottom
                    temp = x0;
                    x0 = x1;
                    x1 = temp;
                    temp = y0;
                    y0 = y1;
                    y1 = temp;
                }
                x = x0;
                for (y = y0; y <= y1; ++y) {
                    C.setPixel(x, y);
                }

            }else if (dy > dx) {            // slope greater than 1

                x0=x0+y0;
                y0 = x0-y0;
                x0=x0-y0;

                temp = x1;
                x1 = y1;
                y1 = temp;

                dy = Math.abs(y1-y0);
                dx = Math.abs(x1-x0);
                dE = 2 * dy;
                dNE = 2 * (dy - dx);
                d = dE - dx;

                if(x0<x1){
                    for (x = x0, y = y0; x <= x1; ++x) {
                        C.setPixel(y, x);
                        if (d <= 0) {
                            d += dE;
                        } else {
                            ++y;
                            d += dNE;
                        }
                    }
                }else {
                    temp = x0;
                    x0 = x1;
                    x1 = temp;
                    temp = y0;
                    y0 = y1;
                    y1 = temp;
                    for (y = y0, x = x0; x <= x1; ++x) {
                        C.setPixel(y, x);
                        if (d <= 0) {
                            d += dE;
                        } else {
                            --y;
                            d += dNE;
                        }
                    }
                }
            }  else if (dx > dy || dx>(-dy)) {          //slope less than 1 & greate than 0
                if(y0<y1) {
                    for (x = x0, y = y0; x <= x1; ++x) {
                        C.setPixel(x, y);
                        if (d <= 0) {
                            d += dE;
                        } else {
                            ++y;
                            d += dNE;
                        }
                    }
                }else{
                    for (x = x0, y = y0; x <= x1; ++x) {
                        C.setPixel(x, y);
                        if (d <= 0) {
                            d -= dNE;
                            --y;
                        } else {
                            d -= dE;

                        }
                    }
                }
            } else if ((dy == dx) || (dy == -dx)) { // 45 & 135 degree slope = 1
                x=x0;
                y=y0;
                if(x0<x1 & y0<y1){
                    while (x < x1 & y < y1) {
                        C.setPixel(x, y);
                        ++x;
                        ++y;
                    }
                }else if(x0<x1 & y0>y1) {
                    while ( x < x1 & y > y1) {
                        C.setPixel(x, y);
                        ++x;
                        --y;
                    }
                }
            }
    }    
      
}
