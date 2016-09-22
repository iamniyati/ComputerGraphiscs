//
//  Rasterizer.java
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 Rochester Institute of Technology. All rights reserved.
//
//  Contributor:  Rudresh Pandit(rmp7494)
//

///
// 
// This is a class that performs rasterization algorithms
//
///

import java.util.*;

public class Rasterizer {

	///
	// number of scanlines
	///

	int n_scanlines;

	///
	// Constructor
	//
	// @param n - number of scanlines
	//
	///

	Rasterizer(int n) {
		n_scanlines = n;
	}

	///
	// Draw a filled polygon in the simpleCanvas C.
	//
	// The polygon has n distinct vertices. The
	// coordinates of the vertices making up the polygon are stored in the
	// x and y arrays. The ith vertex will have coordinate (x[i], y[i])
	//
	// You are to add the implementation here using only calls
	// to C.setPixel()
	///

	public void drawPolygon(int n, int x[], int y[], simpleCanvas C) {
		// YOUR IMPLEMENTATION GOES HERE
		int max = y[0];
		for (int i = 1; i < n; i++) {
			if (y[i] > max)
				max = y[i];
		}
		Node[] edgeList = createEdgeTable(n, x, y);
		Node activeList = null;
		Node newActiveList = null;

		for (int i = 0; i <= max; i++) {
			newActiveList = null;
			if (edgeList[i] != null) {
				if (activeList == null) { // if the active list doesnt have any
											// items
					activeList = edgeList[i]; // add edgelist to it
				} else {
					Node first = activeList;
					while (first.link != null) { // else traverse through the
													// list
						first = first.link;
					}
					first.link = edgeList[i]; // and add at the end
				}
			}
			// deletion code here

			activeList = delete(activeList, i); // to delete from active list
												// where ymax = scan line

			// sort call here
			newActiveList = sortIt(activeList); // sort according to x then
												// according to 1/slope

			// Traversing active list
			Node q = newActiveList;
			while ((q != null) && (q.link != null)) { // traversing two elements
														// at a time
				double xmin = q.x;
				q.x = (xmin + q.slope);
				q = q.link;
				double xmax = q.x;
				q.x = (xmax + q.slope);
				for (double k = xmin; k <= xmax; k++) {
					C.setPixel((int) k, i); // drawing the line
				}
				q = q.link;
			}

			activeList = newActiveList; // one scan line done, time to increment
										// and bring new lines if present
			newActiveList = null;

		}

	}

	public static Node delete(Node theList, int y) {
		/*
		 * Function which deletes all the nodes(segments) which have their YMAX
		 * equal to the scan line
		 */
		Node someLink = theList;
		Node current = theList;
		Node prev = null;

		while (current != null) {
			if (current.ymax == y) {
				if (prev == null) {
					current = current.link; // if its head(or start)
					someLink = current;
				} else {
					prev.link = current.link;
				}
			}
			prev = current;
			current = current.link;
		}
		return someLink;

	}

	public static Node sortIt(Node theList) {
		/*
		 * Function to sort the given activelist according to X and 1/SLOPE (We
		 * are swapping the values rather than the nodes itself).
		 */
		Node front = theList;
		Node i = front;
		Node j = i.link;
		while (i != null) {
			while (j != null) {
				if ((i.x) > (j.x)) { // if X is not equal
					double xtemp = i.x;
					double ytemp = i.ymax;
					float tslope = i.slope;

					i.x = j.x;
					i.ymax = j.ymax;
					i.slope = j.slope;

					j.x = xtemp;
					j.ymax = ytemp;
					j.slope = tslope;
				} else if ((i.x) == (j.x)) { // if X is equal then goto 1/SLOPE
					if ((i.slope) > (j.slope)) {
						double xtemp = i.x;
						double ytemp = i.ymax;
						float tslope = i.slope;

						i.x = j.x;
						i.ymax = j.ymax;
						i.slope = j.slope;

						j.x = xtemp;
						j.ymax = ytemp;
						j.slope = tslope;

					}
				}
				j = j.link;

			}
			i = i.link;
		}

		return front;
	}

	public static Node[] createEdgeTable(int n, int x[], int y[]) {
		/*
		 * Function to form the edgelist
		 * 
		 */
		Node edgeList[] = new Node[601];
		for (int i = 0; i < n; i++) {
			double x0, y0, x1, y1, YMAX, YMIN, X;
			float SLOPE;
			x0 = x[i];
			y0 = y[i];
			if (i != (n - 1)) {
				x1 = x[i + 1];
				y1 = y[i + 1];
			} else {
				x1 = x[0];
				y1 = y[0];
			}

			YMAX = findMAX(y0, y1);
			YMIN = findMIN(y0, y1);
			if (YMIN == y0)
				X = x0;
			else
				X = x1;
			SLOPE = findSLOPE(x0, y0, x1, y1);
			Node N = new Node(YMAX, X, SLOPE, null);
			if (y0 != y1) {
				edgeList = add(N, edgeList, YMIN);
			}

		}
		return edgeList;

	}

	public static Node[] add(Node n, Node[] edgeList, double position) {
		/*
		 * Function to add a node to the the end of the given position
		 */
		Node front = edgeList[(int) position];

		if (front == null) {
			edgeList[(int) position] = n;
		} else {
			while (front.link != null) {
				front = front.link;
			}
			front.link = n;
		}
		return edgeList;

	}

	public static double findMAX(double a, double b) {
		/*
		 * Function to find the maximum of two elements
		 */
		if (a < b)
			return b;
		else
			return a;
	}

	public static double findMIN(double a, double b) {
		/*
		 * Function to find the minimum of two elements
		 */
		if (a <= b)
			return a;
		else
			return b;
	}

	public static float findSLOPE(double a1, double b1, double a2, double b2) {
		/*
		 * Function to find slope for the given line segment
		 */
		float SLOPE;
		if ((a2 - a1) == 0) {
			SLOPE = 0; // slope is infinty but we dont need it
		} else if ((b1 - b2) == 0) {
			SLOPE = 0;
		} else {
			SLOPE = (float) ((a2 - a1) / (b2 - b1));
		}
		return SLOPE;
	}

}
