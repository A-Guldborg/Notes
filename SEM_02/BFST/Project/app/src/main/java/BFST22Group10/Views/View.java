package BFST22Group10.Views;

import BFST22Group10.App;
import BFST22Group10.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class View {
    public final MapCanvas mapCanvas;
    public static float LON_MULTIPLIER;
    GraphicsContext canvas;
    SVGPath path = new SVGPath();

    public View(Stage primaryStage, Controller controller, float lonMultiplier) throws IOException {
        Image icon = new Image(this.getClass().getResourceAsStream("/xploreX.png"));
        LON_MULTIPLIER = lonMultiplier;
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/View.fxml"));
        loader.setController(controller);
        controller = loader.getController();
        primaryStage.setScene(loader.load());
        primaryStage.setTitle("X-Plore");
        primaryStage.getIcons().add(icon);
        primaryStage.show();
        mapCanvas = controller.mapCanvas;
        mapCanvas.widthProperty().bind(primaryStage.widthProperty());
        mapCanvas.heightProperty().bind(primaryStage.heightProperty());
        canvas = mapCanvas.getGraphicsContext2D();
        path.setContent("m 0.00005 -0.0002 q 0.00005 0.00005 -0.00005 0.0002 q -0.0001 -0.00015 -0.00005 -0.0002 q 0.00005 -0.00005 0.0001 0 z m -0.00005 0.00002 c 0.00004 -0 0.00004 0.00006 -0 0.00006 c -0.00004 -0 -0.00004 -0.00006 0 -0.00006 z");
    }

    public void zoomToBox(float minLat, float maxLat, float minLon, float maxLon, float offset) {
        minLat -= offset; maxLat += offset; minLon -= offset; maxLon += offset;
        mapCanvas.resetZoom();
        double factorHeight = mapCanvas.getHeight() / Math.abs(maxLat - minLat);
        double factorWidth = mapCanvas.getWidth() / Math.abs(maxLon - minLon);
        mapCanvas.zoom(Math.min(factorWidth, factorHeight), (minLon + maxLon) * LON_MULTIPLIER / 2, -(minLat + maxLat) / 2);
        mapCanvas.panToMiddle(mapCanvas.getWidth(), mapCanvas.getHeight());
    }

    public void drawLines(float[][] xCoords, float[][] yCoords, Color color, double lineWidth, Boolean blackWhite, boolean drawPrecise){
        canvas.setStroke(blackWhite ? color : color.grayscale());
        canvas.setLineWidth(lineWidth);
        if (xCoords == null) return;
        drawHelper(xCoords, yCoords, canvas, drawPrecise);
        canvas.stroke();
    }

    public void drawPolygon(float[][] xCoords, float[][] yCoords, Color color, Boolean blackWhite){
        if (xCoords == null) return;
        canvas.setStroke(blackWhite ? color :  color.grayscale());
        canvas.setFill(blackWhite ? color : color.grayscale());
        canvas.setFillRule(FillRule.EVEN_ODD);
        drawHelper(xCoords, yCoords, canvas, false);
        canvas.fill();
    }

    public void drawHelper(float[][] xCoords, float[][] yCoords, GraphicsContext canvas, boolean drawPrecise) {
        int incrementer = 10000 / (int) mapCanvas.getZoomLvl();
        canvas.beginPath();
        for(int i = 0; i < xCoords.length; i++) {
            canvas.moveTo(LON_MULTIPLIER * xCoords[i][0], -yCoords[i][0]);
            for(int j = 0; j < xCoords[i].length; j++) {
                canvas.lineTo(LON_MULTIPLIER * xCoords[i][j], -yCoords[i][j]);
                if (!drawPrecise) j += incrementer;
            }
            canvas.lineTo(LON_MULTIPLIER * xCoords[i][xCoords[i].length-1], -yCoords[i][yCoords[i].length-1]);
        }
    }

    // debug
    public float[] getBoundaryOfScreenBox(Boolean debug) throws NonInvertibleTransformException {
        float leftX = 0, topY = 0;
        float rightX = (float) mapCanvas.getWidth();
        float bottomY = (float) mapCanvas.getHeight();
        int offSet = 100;
        Point2D leftTop = debug ? mapCanvas.inverseTransform(leftX+offSet, topY+offSet) : mapCanvas.inverseTransform(leftX, topY);
        Point2D rightBottom = debug ? mapCanvas.inverseTransform(rightX-offSet, bottomY-offSet) : mapCanvas.inverseTransform(rightX, bottomY);
        return new float[]{(float)rightBottom.getY(), (float)leftTop.getY(), (float)leftTop.getX(), (float)rightBottom.getX()};
    }

    public void drawBox(float[] box) {
        canvas.beginPath();
        canvas.setStroke(Color.BLACK);
        canvas.setLineWidth(1 / Math.sqrt(mapCanvas.getTransform().determinant()));
        canvas.moveTo(box[3] * LON_MULTIPLIER, -box[0]);
        canvas.lineTo(box[3] * LON_MULTIPLIER, -box[1]);
        canvas.lineTo(box[2] * LON_MULTIPLIER, -box[1]);
        canvas.lineTo(box[2] * LON_MULTIPLIER, -box[0]);
        canvas.lineTo(box[3] * LON_MULTIPLIER, -box[0]);
        canvas.stroke();
    }

    public void drawZoomRuler(float meters) {
        try {
            Point2D point = mapCanvas.inverseTransform(20, (float) mapCanvas.getHeight() - 75);
            double longitudeOffset = meters / 111139;
            canvas.beginPath();
            canvas.setStroke(Color.GRAY);
            canvas.setLineWidth(2 / Math.sqrt(mapCanvas.getTransform().determinant()));
            canvas.moveTo(point.getX() * LON_MULTIPLIER, -point.getY());
            canvas.lineTo(point.getX() * LON_MULTIPLIER + longitudeOffset, -point.getY());
            canvas.stroke();
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }
    
    public void drawPath(ArrayList<Float> lon, ArrayList<Float> lat) {
        canvas.beginPath();
        canvas.setStroke(Color.RED);
        canvas.setLineWidth(2 / Math.sqrt(mapCanvas.getTransform().determinant()));
        canvas.moveTo(lon.get(0)*LON_MULTIPLIER, -lat.get(0));
        for (int i = 1; i < lon.size(); i++) {
            canvas.lineTo(lon.get(i)*LON_MULTIPLIER, -lat.get(i));
        }
        canvas.stroke();
    }

    public void drawAddressPoint(float latPoint, float lonPoint, Color color) {
        canvas.beginPath();
        canvas.setFill(color);
        canvas.moveTo(lonPoint * LON_MULTIPLIER, -latPoint);
        canvas.appendSVGPath(path.getContent());
        canvas.fill();
    }

}
