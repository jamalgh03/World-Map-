package com.example.world_map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Alert.AlertType;

public class Jamalmain extends Application {
    public static File file;
    static ComboBox<String> scourseText = new ComboBox<String>();
    static ComboBox<String> targetText = new ComboBox<String>();
    static int numOfPointChoice = 0;
    static Pane pane2 = new Pane();
    // private Alert error = new Alert(AlertType.ERROR);

    static LinkedList<vertex> Cities = new LinkedList<>();
    TextArea pathText = new TextArea();
    Image m = new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\worldmap2.jpg");
    ImageView image = new ImageView(m);
    TextField distanceText = new TextField();

    @Override
    public void start(Stage primaryStage) {
        try {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetY(10.0); // Increase the shadow offset for more effect
            dropShadow.setColor(Color.color(0, 0, 0, 0.7)); // Darker shadow color

            image.setFitHeight(525);
            image.setFitWidth(1025);
            pane2.getChildren().add(image);
            image.setEffect(dropShadow); // Apply shadow effect

            primaryStage.getIcons().add(new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png"));

            file = new File("C:\\Users\\cts\\IdeaProjects\\World_Map\\files\\cities (1)");
            readFile(file);

            BorderPane pane = new BorderPane();
            pane.setPadding(new Insets(10));

            HBox hx = new HBox(10);
            hx.setAlignment(Pos.CENTER);
            hx.setPadding(new Insets(3));

            HBox h = new HBox(hx);
            h.setAlignment(Pos.CENTER);

            Label scourse = new Label("Sourse:");
            scourse.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");
            scourse.setEffect(dropShadow); // Apply shadow effect

            scourse.setPadding(new Insets(7));
            scourseText.setMinWidth(118);
            for (int i = 0; i < Cities.size(); i++) {
                scourseText.getItems().add(Cities.get(i).getcity().getCityname());
            }

            HBox h1 = new HBox(scourse, scourseText);
            h1.setAlignment(Pos.CENTER);

            Label target = new Label("Target:");
            target.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");
            target.setEffect(dropShadow); // Apply shadow effect

            target.setPadding(new Insets(7));
            for (int i = 0; i < Cities.size(); i++) {
                targetText.getItems().add(Cities.get(i).getcity().getCityname());
            }
            targetText.setMinWidth(118);

            HBox h2 = new HBox(target, targetText);
            h2.setAlignment(Pos.CENTER);

            Button run = new Button("Run");
            run.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold';");
            run.setEffect(dropShadow); // Apply shadow effect

            CheckBox resetCheckBox = new CheckBox("Enable Reset");
            resetCheckBox.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");
            resetCheckBox.setEffect(dropShadow); // Apply shadow effect
            Label ee = new Label("Btn Action :");
            ee.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");

            HBox butBox = new HBox(20, ee, run, resetCheckBox);
            butBox.setAlignment(Pos.CENTER);

            Label path = new Label("Shortest Path:");
            path.setPadding(new Insets(7));
            path.setMinHeight(200);
            path.setPadding(new Insets(7));
            path.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");
            path.setEffect(dropShadow); // Apply shadow effect

            pathText.setMaxSize(218, 200);
            pathText.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold';");
            pathText.setEffect(dropShadow); // Apply shadow effect

            HBox h3 = new HBox(path, pathText);
            h3.setAlignment(Pos.CENTER);

            Label distance = new Label("Distance:");
            distance.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: white;");
            distance.setEffect(dropShadow); // Apply shadow effect

            distance.setPadding(new Insets(7));
            distanceText.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: black;");
            distanceText.setEffect(dropShadow); // Apply shadow effect

            HBox h4 = new HBox(distance, distanceText);
            h4.setAlignment(Pos.CENTER);

            VBox v = new VBox(30, h, h1, h2, butBox);
            v.setAlignment(Pos.CENTER);
            v.setPadding(new Insets(10));

            VBox v1 = new VBox(30, h3, h4);
            v1.setAlignment(Pos.CENTER);
            v1.setPadding(new Insets(10));

            VBox mix = new VBox(10, v, v1);
            mix.setAlignment(Pos.CENTER);

            VBox Vmap = new VBox(pane2);
            Vmap.setAlignment(Pos.CENTER);

            HBox mainBox = new HBox(20, Vmap, mix);
            mainBox.setAlignment(Pos.CENTER);

            pane.setCenter(mainBox);

            pane2.setOnMouseClicked(event -> {
                double mouseX = event.getX();
                double mouseY = event.getY();

                numOfPointChoice++;
                if (numOfPointChoice == 2) {
                }
            });

            resetCheckBox.setOnAction(e -> {
                if (resetCheckBox.isSelected()) {
                    run.setText("Reset");
                    run.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-background-color: red;");
                } else {
                    run.setText("Run");
                    run.setStyle("-fx-font-size: 18px; -fx-font-family: 'Britannic Bold'; -fx-text-fill: black;");
                }
            });

            run.setOnAction(e -> {
                if (resetCheckBox.isSelected()) {
                    reset();
                } else {
                    vertex vertx1 = null;
                    vertex vertx2 = null;
                    String s1 = scourseText.getValue();
                    System.out.println(s1);
                    String s2 = targetText.getValue();
                    System.out.println(s2);

                    for (int i = 0; i < Cities.size(); i++) {
                        if (Cities.get(i).getcity().getCityname().equals(s1)) {
                            vertx1 = Cities.get(i);
                        }
                        if (Cities.get(i).getcity().getCityname().equals(s2)) {
                            vertx2 = Cities.get(i);
                        }
                    }

                    if (vertx1 != null && vertx2 != null) {
                        double distancee = drowLine(Dijkstra(vertx1, vertx2), pathText, distanceText);
                        System.out.println(distancee);
                        distanceText.setText(String.format("%.2f km", distancee));
                    }
                }
            });

            addPoint();
            Scene scene = new Scene(pane, 1535, 800);
            primaryStage.setScene(scene);
            pane.setStyle("-fx-background-color: rgb(20, 24, 82);");
            pane2.setStyle("-fx-background-color: rgb(20, 24, 82);");

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double drowLine(vertex Destination, TextArea pathText, TextField distanceText) {
        double totalDistance = 0;
        if (Destination == null) {
            pathText.setText("No Path");
        } else {
            List<vertex> p = new ArrayList<>();
            for (vertex v = Destination; v != null; v = v.previous) {
                p.add(v);
            }
            Collections.reverse(p);
            if (p.size() >= 1) {
                StringBuilder path = new StringBuilder();
                for (int i = 1; i < p.size(); i++) {
                    double d = Distance(p.get(i - 1), p.get(i));
                    totalDistance += d; // جمع المسافة لكل خطوة في المسار
                    path.append(p.get(i - 1).getcity().getCityname()).append(" --> ");
                }
                path.append(p.get(p.size() - 1).getcity().getCityname()).append("/n");
                pathText.setText(path.toString());
            }
            if (p.size() <= 1) {
                pathText.setText("No Path");
            }

            for (int i = 0; i < p.size() - 1; i++) {
                vertex u = p.get(i);
                vertex v = p.get(i + 1);

                if (i != 0 && i != p.size() - 1) {
                    ImageView vi0 = new ImageView(new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png"));
                    vi0.setFitHeight(16);
                    vi0.setFitWidth(16);
                    u.getcity().getRadioButton().setGraphic(vi0);
                }

                Line line = new Line(u.city.getX(), u.city.getY(), v.city.getX(), v.city.getY());
                line.setStroke(Color.WHITE); // تعيين لون الخط إلى الأبيض
                line.setStrokeWidth(2); // تعيين عرض الخط

                // إضافة سهم
                double arrowLength = 10;
                double arrowWidth = 7;

                double ex = line.getEndX();
                double ey = line.getEndY();
                double sx = line.getStartX();
                double sy = line.getStartY();

                double dx = ex - sx;
                double dy = ey - sy;

                double angle = Math.atan2(dy, dx) + Math.PI / 2.0; // عكس الاتجاه

                double sin = Math.sin(angle);
                double cos = Math.cos(angle);

                // الخط الأول من السهم
                Line arrow1 = new Line(ex, ey, ex + cos * arrowWidth - sin * arrowLength,
                        ey + sin * arrowWidth + cos * arrowLength);
                arrow1.setStroke(Color.WHITE);
                arrow1.setStrokeWidth(2);

                // الخط الثاني من السهم
                Line arrow2 = new Line(ex, ey, ex - cos * arrowWidth - sin * arrowLength,
                        ey - sin * arrowWidth + cos * arrowLength);
                arrow2.setStroke(Color.WHITE);
                arrow2.setStrokeWidth(2);

                pane2.getChildren().addAll(line, arrow1, arrow2);
            }
        }
        distanceText.setText(String.format("%.2f km", totalDistance));
        return totalDistance;
    }

    private void addPoint() {
        for (int i = 0; i < Cities.size(); i++) {
            RadioButton r = Cities.get(i).getcity().getRadioButton();
            r.setLayoutX(Cities.get(i).getcity().getX());
            r.setLayoutY(Cities.get(i).getcity().getY());
            pane2.getChildren().add(r);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void free() {
        try {
            for (int i = 0; i < Cities.size(); i++) {
                Cities.get(i).getcity().getRadioButton().setDisable(false);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private int compareVertices(vertex v1, vertex v2) {
        return Double.compare(v1.Weight, v2.Weight);
    }

    public void readFile(File file) {
        try {
            Scanner sc = new Scanner(file);
            String[] l = sc.nextLine().split(",");
            int numCounter = Integer.parseInt(l[0]);
            int numEdge = Integer.parseInt(l[1]);
            int count = 0;
            int num = 0;

            while (count < numCounter - 1) {
                String line = sc.nextLine();
                System.out.println(line);
                vertex ver = new vertex(new Cities(line), num++);
                Cities.add(ver);
                count++;
            }

            count = 0;
            while (count < numEdge) {
                String tokens[] = sc.nextLine().split(",");
                for (int i = 0; i < Cities.size(); i++) {
                    if (Cities.get(i).getcity().getCityname().compareToIgnoreCase(tokens[0]) == 0) {
                        for (int j = 0; j < Cities.size(); j++) {
                            if (Cities.get(j).getcity().getCityname().compareToIgnoreCase(tokens[1]) == 0) {
                                Cities.get(i).e.add(new edges(Cities.get(i), Cities.get(j),
                                        Distance(Cities.get(i), Cities.get(j))));
                            }
                        }
                    }
                }
                count++;
            }
            sc.close();
        } catch (FileNotFoundException t) {
            System.out.println(t);
        }
    }

    public double Distance(vertex a, vertex b) {
        final int EARTH_RADIUS = 6371;
        double WIDTH = 1025; // width

        double x1 = vertex.convertLongitude(a.getcity().getLongitude());
        double y1 = vertex.convertLatitude(a.getcity().getLatitude());

        double x2 = vertex.convertLongitude(b.getcity().getLongitude());
        double y2 = vertex.convertLatitude(b.getcity().getLatitude());

        double distanceOnScreen = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        double distanceOnEarth = (distanceOnScreen / WIDTH) * (2 * Math.PI * EARTH_RADIUS);

        return distanceOnEarth;
    }

    private void reset() {
        pane2.getChildren().clear();
        targetText.getSelectionModel().select(null);
        scourseText.getSelectionModel().select(null);
        distanceText.setText("");
        pathText.setText("");
        numOfPointChoice = 0;

        pane2.getChildren().add(image);
        Image pinImage = new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png");
        for (vertex cou : Cities) {
            ImageView vi = new ImageView(pinImage);
            vi.setFitHeight(17);
            vi.setFitWidth(16);
            cou.getcity().getRadioButton().setGraphic(vi);
            cou.getcity().getRadioButton().setSelected(false);
            free();
        }

        for (int i = 0; i < Cities.size(); i++) {
            Cities.get(i).visited = false;
            Cities.get(i).Weight = Double.POSITIVE_INFINITY;
            Cities.get(i).previous = null;
        }

        for (vertex cou : Cities) {
            if (!pane2.getChildren().contains(cou.getcity().getLabel())) {
                pane2.getChildren().add(cou.getcity().getLabel());
            }
        }

        addPoint();
    }



    public vertex Dijkstra(vertex Source, vertex target) {
        initializetable(Source); // Ensure all vertices are properly initialized

        MinHeap1<vertex> heap = new MinHeap1<>(this::compareVertices);

        heap.insert(Source);

        while (!heap.isEmpty()) {
            vertex u = heap.removeMin();

            u.visited = true;
            if (u.city.getCityname().equals(target.getcity().getCityname())) {
                break;
            }

            for (edges e : u.getE()) {
                vertex v = e.Target;
                if (!v.visited) {
                    double weight = e.Weight;
                    double distanceThroughU = u.Weight + weight;
                    if (distanceThroughU < v.Weight) {
                        v.Weight = distanceThroughU;
                        v.previous = u;
                        heap.insert(v);
                    }
                }
            }
        }

        return target;
    }

    public void initializetable(vertex Source) {
        final double INFINITY = Double.POSITIVE_INFINITY;
        for (vertex v : Cities) {
            v.visited = false;
            v.previous = null;
            v.Weight = INFINITY;
            Source.Weight = 0; // Ensure the source distance is set to 0

        }
    }

}