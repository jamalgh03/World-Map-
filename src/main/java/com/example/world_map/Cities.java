package com.example.world_map;


import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Cities {
    private double WIDTH = 1025; // width
    private double HEIGHT = 525; // height
    private String Cityname; // City  Name
    private double x;//X-coordinate
    private double y;// Y-coordinate
    private double longitude; // بتمثل خط الطول
    private double latitude; // بتمثل خط العرض
    private RadioButton radioButton = new RadioButton(); // بستخدمها  ل اختيار عاصمة من الخريطة
    private ToggleGroup group; // عشان احدد كم زر بدي اختار
    private Label label = new Label(); // عشان يضيف الاسم على الخريطة

    public Cities() {
        // TODO Auto-generated constructor stub
    }
    // هاي الميثود ل تقسيم الفايل
    public Cities(String line) {
        String[] arr = line.split(",");// ال Split عند :

        this.Cityname = arr[0]; // اسم العاصمة

        this.latitude = Double.parseDouble(arr[1]); // خط الطول
        this.longitude = Double.parseDouble(arr[2]); // خط العرض

        radioButton.setToggleGroup(group);
        radioButton.setPadding(new Insets(-7.5));
        ImageView vi = new ImageView(new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png"));
        vi.setFitHeight(16);
        vi.setFitWidth(16);
        radioButton.setGraphic(vi);

        label.setText(Cityname); //  لعرض اسم العاصمة على الخريطة

        label.setFont(Font.font("Verdana", 7));
        label.setTextFill(Color.WHITE); //  لون الخط
        label.setStyle("-fx-background-color: transparent;");
        label.setLayoutX(getX() - 8); // موقع ال ليبل راح ينعرض على يسار العلامة
        label.setLayoutY(getY() + 18);// لازاحة ال ليبل الرأسية
        Jamalmain.pane2.getChildren().add(label);

        radioButton.setOnAction(o -> {
            if (Jamalmain.numOfPointChoice == 0) {
                ImageView vi0 = new ImageView(new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png"));
                vi0.setFitHeight(16);
                vi0.setFitWidth(16);
                radioButton.setGraphic(vi0);
            }

            if (Jamalmain.numOfPointChoice == 1) {
                ImageView vi0 = new ImageView(new Image("C:\\Users\\cts\\IdeaProjects\\World_Map\\images\\locationpin.png"));
                vi0.setFitHeight(16);
                vi0.setFitWidth(16);
                radioButton.setGraphic(vi0);
            }
            radioButton.setSelected(true);
            Jamalmain.numOfPointChoice += 1;
//            if (Jamalmain.numOfPointChoice == 2) {
//               // Jamalmain.lock();
//            }

            if (Jamalmain.numOfPointChoice == 2) {
                Jamalmain.targetText.getSelectionModel().select(getCityname());
            }
            if (Jamalmain.numOfPointChoice == 1) {
                Jamalmain.scourseText.getSelectionModel().select(getCityname());
            }

        });
    }

    public String getCityname() {
        return Cityname;
    }

    public void setCityname(String Cityname) {
        this.Cityname = Cityname;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public RadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(RadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public ToggleGroup getGroup() {
        return group;
    }

    public void setGroup(ToggleGroup group) {
        this.group = group;
    }

    public double getX() {
        return ((longitude + 180) / 360 * WIDTH); // عشان يتحول خط الطول من (180,-180) ل
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return (HEIGHT - (latitude + 90) / 180 * HEIGHT);
    }

    public void setY(double y) {
        this.y = y;
    }
    public Label getLabel() {
        return label;
    }

}
