package BFST22Group10.Views;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class MapCanvas extends Canvas {
    private final Affine transform = new Affine();
    private final GraphicsContext canvas = getGraphicsContext2D();

    public MapCanvas(){
        canvas.setTransform(transform);
    }
    
    public void clear(boolean isIsland){
        canvas.setFill(isIsland ? Color.LIGHTBLUE : Color.rgb(243, 242, 227, 1f));
        canvas.fillRect(-1000,-1000, 2000, 2000);
    }
    
    public void pan(double dx, double dy) {
        transform.prependTranslation(dx, dy);
        canvas.setTransform(transform);
    }
    
    public void zoom(double factor, double pivotX, double pivotY){
        transform.prependScale(factor, factor, pivotX, pivotY);
        canvas.setTransform(transform);
    }

    public void panToMiddle(double stageWidth, double stageHeight) {
        pan(stageWidth / 2, stageHeight / 2);
    }

    public Point2D inverseTransform(float x, float y) throws NonInvertibleTransformException {
        Point2D returnPoint = transform.inverseTransform(x, y);
        return new Point2D(returnPoint.getX() / View.LON_MULTIPLIER, -returnPoint.getY());
    }

    public double getZoomLvl(){
        return transform.getMxx();
    }
    
    public Affine getTransform() {
        return transform;
    }

    public void resetZoom() {
        transform.setMxx(1.0);
        transform.setMyy(1.0);
        transform.setTx(0);
        transform.setTy(0);
    }
}
