package BFST22Group10;

import BFST22Group10.Models.*;
import BFST22Group10.Views.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import static javafx.scene.paint.Color.*;

public class Controller {
    private Popup popUpPOI;
    private Popup popUpRoute;
    private Point2D lastMouse;
    private final Model model;
    private final View view;
    private long lastDraw;
    private boolean routeTimer = true;
    private boolean showNearestRoadField = false;
    private boolean debugNearestNeighbor = false;
    private boolean debugTree = false;
    private boolean debugRouteSearch = false;
    private boolean hasTrailer = false;
    private boolean fromTo = true;
    private boolean blackWhite = true;
    private boolean routeSearch = true;
    private final Stage primaryStage;
    private Vertex nearestNeighbor;
    private ModeOfTransport modeOfTransport = ModeOfTransport.CAR;
    private ArrayList<Float> currentRouteLats;
    private ArrayList<Float> currentRouteLons;
    private float[][] visitedEdgesLats;
    private float[][] visitedEdgesLons;
    private Address from;
    private Address to;
    private final HashMap<String, Address> pointsOfInterest = new HashMap<>();

    public Controller(Stage primaryStage, InputStream path, String pathName) throws IOException {
        model = new Model(path, pathName);
        view = new View(primaryStage, this, model.getLatLonProportions());
        view.zoomToBox(model.getMinLat(), model.getMaxLat(), model.getMinLon(), model.getMaxLon(), 0.002f);
        draw();
        primaryStage.widthProperty().addListener((observable, oldValue, newValue) -> draw());
        primaryStage.heightProperty().addListener((observable, oldValue, newValue) -> draw());
        this.primaryStage = primaryStage;
        createMenu();
        setAddressFieldVisibility();
        createPopUps();
    }

    private void createPopUps() {
        popUpPOI = new Popup();
        popUpPOI.getContent().add(popUpPOIBox);
        popUpRoute = new Popup();
        popUpRoute.getContent().add(popUpRouteBox);
    }

    private void createMenu() {
        String os = System.getProperty("os.name");
        if(os.contains("Mac")){
            menuBar.setLayoutX(-100);
            menuBar.setLayoutY(-100);
            addressSearchBar.setLayoutX(3);
            addressSearchBar.setLayoutY(3);
            menuBar.setUseSystemMenuBar(true);

        }
        primaryStage.setMinHeight(525);
        primaryStage.setMinWidth(1000);
        mapCanvas.setOnContextMenuRequested(event -> {
            contextMenu.show(mapCanvas, event.getScreenX(), event.getScreenY());
        });
    }

    private void FromToHere(Boolean isFrom) throws NonInvertibleTransformException {
        if(routeSearch){
            showRouteSearch();
            fromTo = !fromTo;
        }
        Point2D point = mapCanvas.inverseTransform((float)lastMouse.getX(), (float)lastMouse.getY());
        if(isFrom) {
            from = (Address) model.getNearestNeighborVertex((float) point.getX(), (float) point.getY(), true);
            addressSearchBar.getEditor().setText(from.toString());
        }
        if(!isFrom){
            to = (Address) model.getNearestNeighborVertex((float) point.getX(), (float) point.getY(), true);
            secondAddressSearchBar.getEditor().setText(to.toString());
        }
        findShortestPath();
    }

    private void draw() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime < lastDraw + 30) return;
        lastDraw = currentTime;
        view.mapCanvas.clear(model.isIsland());

        HashMap<Tag, ArrayList<OSMElement>> elements = new HashMap<>();
        try {
            elements = model.spatialTreeSearchQuery(view.getBoundaryOfScreenBox(debugTree), (int) mapCanvas.getZoomLvl(), debugTree);
        }
        catch (NonInvertibleTransformException e) {
            System.err.println(e.getMessage());
        }

        drawByTag(elements);

        if (debugNearestNeighbor) view.drawAddressPoint(nearestNeighbor.getMainLat(), nearestNeighbor.getMainLon(), Color.RED);

        if(debugTree) drawTreeDebug();

        drawRoute();

        if (from != null && !pointsOfInterest.containsKey(from.toString())) drawAddress(from, RED);
        if (to != null) drawAddress(to, GREEN);

        drawZoomRuler();

        drawPOIs();
    }

    private void drawAddress(Address address, Color color){
        view.drawAddressPoint(address.getMainLat(), address.getMainLon(), color);
    }

    private void drawPOIs() {
        pointsOfInterest.entrySet().stream().forEach(
                input -> view.drawAddressPoint(input.getValue().getMainLat(), input.getValue().getMainLon(), RED));
    }

    private void drawZoomRuler() {
        int meters = (int) (Math.floor(10 + 2000000 / mapCanvas.getZoomLvl()) * 10);
        label.setText("Distance: " + meters + "m");
        view.drawZoomRuler(meters);
    }

    private void drawRoute() {
        if(debugRouteSearch) {
            view.drawLines(visitedEdgesLons, visitedEdgesLats, Color.ORANGE, 0.0001, blackWhite, true);
        }

        if (currentRouteLats != null && currentRouteLons != null && currentRouteLons.size() != 0 && currentRouteLats.size() != 0) {
            view.drawPath(currentRouteLons, currentRouteLats);
        }
    }

    private void drawTreeDebug() {
        try {
            view.drawBox(view.getBoundaryOfScreenBox(debugTree));
        } catch (NonInvertibleTransformException e) {
            System.out.println(e.getMessage());
        }
    }

    private void drawByTag(HashMap<Tag, ArrayList<OSMElement>> elements) {
        for (Tag tag : Tag.values()) {
            ArrayList<OSMElement> osmElements = elements.get(tag);
            if(mapCanvas.getZoomLvl() < tag.getRequiredZoomLevel()) continue;
            for (OSMElement element : osmElements) {
                if (element.isPolygon()) {
                    view.drawPolygon(element.getLon(), element.getLat(), tag.getColor(), blackWhite);
                }
                else {
                    if (element.getLon().length > 0) {
                        view.drawLines(element.getLon(), element.getLat(), tag.getColor(), tag.getLineWidth()+0.5 / mapCanvas.getZoomLvl(), blackWhite, tag == Tag.DEBUG || tag == Tag.ROUTE_FERRY);
                    }
                }
            }
        }
    }

    private void zoom(double factor, double x, double y) {
        mapCanvas.zoom(factor, x, y);
        draw();
    }

    private void createNewMenu(String name){
        Menu newMenu = new Menu(name);
        MenuItem menuOne = new MenuItem("Go to");
        menuOne.setOnAction(λ -> {
            addressSearchBar.getEditor().setText(pointsOfInterest.get(name).toString());
            searchAddress();
            searchSingleAddress();
        });
        MenuItem menuTwo = new MenuItem("Remove");
        menuTwo.setOnAction(λ -> {
            menuPOI.getItems().remove(newMenu);
            pointsOfInterest.remove(name);
            drawPOIs();
        });
        newMenu.getItems().addAll(menuOne, menuTwo);
        menuPOI.getItems().add(newMenu);
    }

    @FXML
    public MapCanvas mapCanvas;
    public ComboBox<String> addressSearchBar;
    public TextField label;
    public MenuBar menuBar;
    public ComboBox<String> secondAddressSearchBar;
    public ToggleGroup wayToTravelGroup;
    public Button pathButton;
    public Button RouteSearchButton;
    public RadioButton radioButtonCar;
    public RadioButton radioButtonBicycle;
    public RadioButton radioButtonWalk;
    public CheckBox checkBoxTrailer;
    public Button GoButton;
    public TextField addressField;
    public ContextMenu contextMenu;
    public TextField searchTime;
    public Button flipButton;
    public VBox popUpPOIBox;
    public VBox popUpRouteBox;
    public TextField inputPOI;
    public Menu menuPOI;
    public TextArea routeDescriptionTextArea;

    @FXML
    private void searchSingleAddress(){
        if (from != null) {
            view.zoomToBox(from.getMainLat(), from.getMainLat(), from.getMainLon(), from.getMainLon(), 0.002f);
            draw();
        }
    }

    @FXML
    private void showRouteSearch(){
        if(routeSearch) {
            RouteSearchButton.setText("X");
            pathButton.setVisible(true);
            radioButtonCar.setVisible(true);
            radioButtonBicycle.setVisible(true);
            radioButtonWalk.setVisible(true);
            checkBoxTrailer.setVisible(true);
            secondAddressSearchBar.setVisible(true);
            flipButton.setVisible(true);
            GoButton.setVisible(false);
            routeSearch=!routeSearch;
        }else{
            RouteSearchButton.setText("Route Search");
            pathButton.setVisible(false);
            radioButtonCar.setVisible(false);
            radioButtonBicycle.setVisible(false);
            radioButtonWalk.setVisible(false);
            checkBoxTrailer.setVisible(false);
            secondAddressSearchBar.setVisible(false);
            flipButton.setVisible(false);
            GoButton.setVisible(true);
            routeSearch=!routeSearch;
            fromTo = !fromTo;
            currentRouteLons = null;
            currentRouteLats = null;
            popUpRoute.hide();
            addressSearchBar.getEditor().setText("");
            secondAddressSearchBar.getEditor().setText("");
            from = null;
            to = null;
            draw();
        }
    }

    @FXML
    private void searchAddress(){
        try {
            String input = addressSearchBar.getEditor().getText();
            from = model.addressSearch(input);
        } catch (NullPointerException e){
            from = null;
        }
    }

    @FXML
    private void searchAddressTwo(){
        try {
            String input = secondAddressSearchBar.getValue();
            to = model.addressSearch(input);
        } catch (NullPointerException e){
            to = null;
        }
    }

    @FXML
    private void suggestAddress(KeyEvent keyEvent) {
        ComboBox<String> searchBar = (ComboBox<String>) keyEvent.getSource();
        String input = searchBar.getEditor().getText();
        if (input.length() < 1) {
            searchBar.hide();
            return;
        }
        HashSet<Address> addresses = model.getAddressSuggestions(input);
        ObservableList<String> suggestions = searchBar.getItems();
        suggestions.clear();
        for (Address address : addresses) {
            suggestions.add(address.toString());
        }
        searchBar.show();
        searchBar.setValue(input);
    }

    @FXML
    private void findShortestPath(){
        Long start = System.nanoTime();
        if(from == null||to == null) return;
        Vertex v1 = (Vertex) model.getNearestNeighborVertex(from.getMainLon(), from.getMainLat(), false);
        Vertex v2 = (Vertex) model.getNearestNeighborVertex(to.getMainLon(), to.getMainLat(), false);

        PathFinder pathFinder = new PathFinder(v1, v2, modeOfTransport, model.getVerticesCount(), hasTrailer);
        ArrayList<Vertex> path = new ArrayList<>(pathFinder.getFullPath());

        currentRouteLons = routeToDraw(path, false);
        currentRouteLats = routeToDraw(path, true);

        setVisitedEdgesInShortestPath(pathFinder.getVisitedEdgesLons(), pathFinder.getVisitedEdgesLats());


        float minLat = Math.min(from.getMainLat(), to.getMainLat());
        float maxLat = Math.max(from.getMainLat(), to.getMainLat());
        float minLon = Math.min(from.getMainLon(), to.getMainLon());
        float maxLon = Math.max(from.getMainLon(), to.getMainLon());

        view.zoomToBox(minLat, maxLat, minLon, maxLon, 0.008f);

        draw();
        showRouteDescription(pathFinder.getRouteDescription());
        double timeUsage = (System.nanoTime() - start) / 1000000000.0;
        searchTime.setText("Search Time: "+ String.format("%.2f", timeUsage)+" seconds");
    }

    private void setVisitedEdgesInShortestPath(ArrayList<float[]> lons, ArrayList<float[]> lats) {
        visitedEdgesLons = new float[lons.size()][];
        visitedEdgesLats = new float[lats.size()][];

        for (int i = 0; i < lons.size(); i++) {
            visitedEdgesLons[i] = lons.get(i);
            visitedEdgesLats[i] = lats.get(i);
        }
    }

    private ArrayList<Float> routeToDraw(ArrayList<Vertex> path, boolean lats){
        ArrayList<Float> coords = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            coords.add(lats ? path.get(i).getMainLat() : path.get(i).getMainLon());
        }
        return coords;
    }

    @FXML
    private void selectWayToTravel(){
        RadioButton selectedRadioButton = (RadioButton) wayToTravelGroup.getSelectedToggle();
        String wayToTravel = selectedRadioButton.getText();
        modeOfTransport = ModeOfTransport.valueOf(wayToTravel.toUpperCase());
        checkBoxTrailer.setVisible(modeOfTransport.equals(ModeOfTransport.CAR));
    }

    @FXML
    private void hasTrailer() {
        hasTrailer = !hasTrailer;
        System.out.println(hasTrailer);
    }

    @FXML
    private void onScroll(ScrollEvent e) {
        zoom(Math.pow(1.005, e.getDeltaY()), e.getX(), e.getY());
    }

    @FXML
    private void onMousePressed(MouseEvent e) {
        lastMouse = new Point2D(e.getX(), e.getY());
        contextMenu.hide();
    }

    @FXML
    private void onMouseDragged(MouseEvent e) {
        double dx = e.getX() - lastMouse.getX();
        double dy = e.getY() - lastMouse.getY();
        mapCanvas.pan(dx, dy);
        lastMouse = new Point2D(e.getX(), e.getY());
        draw();
    }

    @FXML
    private void findNearestNeighbor(MouseEvent e) throws NonInvertibleTransformException {
        if(showNearestRoadField || debugNearestNeighbor) {
            Point2D point2D = mapCanvas.inverseTransform((float) e.getX(), (float) e.getY());
            nearestNeighbor = (Vertex) model.getNearestNeighborVertex((float) point2D.getX(), (float) point2D.getY(), false);
            addressField.setText(nearestNeighbor.getNeighbors() == null ? nearestNeighbor.toString() : nearestNeighbor.getNeighbors().get(0).getStreetName());
            draw();
        }
    }

    @FXML
    private void changeColor(){
        blackWhite = !blackWhite;
        draw();
    }

    @FXML
    private void showSpatialTreeDebug(){
        debugTree = !debugTree;
        draw();
    }

    @FXML
    private void uploadFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized and OSM files", "*.obj", "*.osm", "*.OSM"));
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file!=null) {
            primaryStage.close();
            try {
                App.restart(primaryStage, file.getPath());
            } catch (IOException | XMLStreamException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @FXML
    private void serializeFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Serialized", "*.obj"));
        File saveDirectory = fileChooser.showSaveDialog(primaryStage);
        if (saveDirectory != null) {
            Thread saveThread = new Thread(() -> {
                System.out.println("Starting saving");
                try {
                    model.save(saveDirectory.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Done saving");
            });
            saveThread.setDaemon(false);
            saveThread.start();
        }
    }

    @FXML
    private void plusPushed(){
        zoom(1.5, mapCanvas.getWidth() / 2, mapCanvas.getHeight() / 2);
    }

    @FXML
    private void minusPushed(){
        zoom(0.5, mapCanvas.getWidth() / 2, mapCanvas.getHeight() / 2);
    }

    @FXML
    private void changeSpatialTree() {
        model.changeSpatialTree();
        draw();
    }

    @FXML
    private void NearestNeighbor(){
        debugNearestNeighbor = !debugNearestNeighbor;
    }

    @FXML
    private void directionsFromHere() throws NonInvertibleTransformException {
        FromToHere(true);
    }

    @FXML
    private void directionsToHere() throws NonInvertibleTransformException {
        FromToHere(false);
    }

    @FXML
    private void pointOfInterest(){
        popUpPOI.show(primaryStage);
        popUpRoute.hide();
    }

    @FXML
    private void debugRouteSearch() {
        debugRouteSearch = !debugRouteSearch;
    }

    @FXML
    private void timer(){
        routeTimer = !routeTimer;
        searchTime.setVisible(routeTimer);
        draw();
    }

    @FXML
    private void setAddressFieldVisibility(){
        showNearestRoadField = !showNearestRoadField;
        addressField.setVisible(showNearestRoadField);
        draw();
    }

    @FXML
    private void flipAddress(){
        String tempAddress = addressSearchBar.getEditor().getText();
        addressSearchBar.getEditor().setText(secondAddressSearchBar.getEditor().getText());
        secondAddressSearchBar.getEditor().setText(tempAddress);
        from = model.addressSearch(addressSearchBar.getEditor().getText());
        to = model.addressSearch(secondAddressSearchBar.getEditor().getText());
    }

    @FXML
    private void addPOI() throws NonInvertibleTransformException {
        String name = inputPOI.getText().toLowerCase(Locale.ROOT);
        if(name.length() < 3){
            Alert alert = new Alert(Alert.AlertType.NONE, "Please provide a name with 3 or more letters",ButtonType.CLOSE);
            alert.show();
        }else if(pointsOfInterest.containsKey(name)){
            Alert alert = new Alert(Alert.AlertType.NONE, "Name already used, please use another",ButtonType.CLOSE);
            alert.show();
        }else{
            createNewMenu(name);
            Point2D point = mapCanvas.inverseTransform((float)lastMouse.getX(), (float)lastMouse.getY());
            Address poiAddress = new Address();
            poiAddress.setCity(inputPOI.getText());
            poiAddress.setLat((float)point.getY());
            poiAddress.setLon((float)point.getX());
            model.addAddress(poiAddress);
            pointsOfInterest.put(name, poiAddress);
            draw();
            inputPOI.setText("");
            popUpPOI.hide();
        }
    }

    private void showRouteDescription(ArrayList<String> description){
        routeDescriptionTextArea.clear();
        popUpRoute.show(primaryStage);

        // JavaFX issue causing blurry texts fixed with the following lines of code https://stackoverflow.com/a/31622654
        routeDescriptionTextArea.setCache(false);
        ScrollPane sp = (ScrollPane)routeDescriptionTextArea.getChildrenUnmodifiable().get(0);
        sp.setCache(false);
        for (Node n : sp.getChildrenUnmodifiable()) {
            n.setCache(false);
        }

        popUpRoute.setX(primaryStage.getX() + primaryStage.getWidth() - popUpRoute.getWidth() - 40);
        popUpRoute.setY(primaryStage.getY() + 40);
        for (String segment : description) {
            routeDescriptionTextArea.appendText(segment + "\n\n");
        }
    }
}