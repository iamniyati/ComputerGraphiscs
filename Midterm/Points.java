import java.awt.*;

/**
 * Created by niyatishah on 3/16/16.
 */
public class Points {
    static int id = 0;
    Polygon polygon;


    Points() {
    }

    Points( int[] X, int [] Y){
        id = id+1;
        this.polygon = new Polygon(X,Y,X.length);

    }



}