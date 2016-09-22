

/**
 * Created by niyatishah on 2/25/16.
 */
public class Node {
    double ymax;
    double x;
    float slope;
    Node link = null;


    Node(){

    }
    Node(double yMax, double x,float slope, Node node){
        this.ymax = yMax;
        this.x = x;
        this.slope = slope;
        this.link = node;
    }

    public Node getNext(){
        return link;
    }

    public void setNext(Node next){
        this.link=next;

    }


}
