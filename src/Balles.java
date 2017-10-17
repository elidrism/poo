import java.awt.geom.Point2D;
import java.util.LinkedList;

class Balles extends Point2D {
  public LinkedList<Point2D> balles = new LinkedList<Point2D>();

  public void translater(int dx, int dy){
    for (Point2D pt : balles) {
      translate(pt, dx, dy);
    }
  }

  @Override
  public void setLocation(double a, double b){}

  public double getX(){
    return 0.0;
  }

  public double getY(){
    return 0.0;
  }

  public void translate(Point2D pt, double dx, double dy){
    pt.setLocation(pt.getX() + dx, pt.getY() + dy);
  }
}
