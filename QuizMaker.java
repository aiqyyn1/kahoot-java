package com.example.project2;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizMaker extends Application {
    private ListView listView = new ListView();
    public Stage Bahafestival;
    private ArrayList<Question> questions2 = new ArrayList<Question>();
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private String[] options = new String[4];
    FileChooser fileChooser;
    Text text3=new Text();
    Test test = new Test();
    int ind = 0;
    int qyestions = 0;
    Timeline timeline;
    int score = 0;
    int[] arr;
    int count = 0;
    int[] w;
    Label label;
    Label lblOfTimer;
    Media media;

private int sec=0;
private int min=0;
    Timeline timer;
    File file;
    Quiz quiz;
    String[] data = new String[questions2.size()];
    HBox hBox;

    RadioButton[] radioButtons = new RadioButton[4];

    public void start(Stage stage) throws FileNotFoundException {


        Bahafestival = stage;

        Bahafestival.setScene(new Scene(filechooser(), 400, 400));


        Bahafestival.setTitle("project");
        Bahafestival.show();
    }


    public StackPane filechooser() throws FileNotFoundException {
        Image image = new Image(new FileInputStream("src/background.jpg"));

        ImageView view = new ImageView(image);
        view.setFitHeight(400);
        view.setFitWidth(400);
        Text text = new Text();
        text.setFont(new Font(20));
        text.setX(100);
        text.setY(100);
        text.setWrappingWidth(200);
        text.setTextAlignment(TextAlignment.JUSTIFY);

        text.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                text.setX(mouseEvent.getX());
                text.setY(mouseEvent.getY());
            }
        });

        Button button = new Button("Choose a file");

        quiz = new Quiz();
        test = new Test();
        StackPane stackPane = new StackPane();
        Test test = new Test();
        stackPane.getChildren().addAll(view, button);


        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(Bahafestival);
            questions2 = quiz.questions;
            try {
                quiz.loadFromFile(file.getPath());
                arr = new int[questions2.size()];
                data = new String[questions2.size()];

                questions2 = quiz.getQuestions();
                Bahafestival.setScene(new Scene(kquestions(), 500, 500));
                media = new Media(new File("src/music").toURI().toString());
                MediaPlayer mp = new MediaPlayer(media);
                mp.play();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (InvalidQuizFormatException ex) {
                ex.printStackTrace();
            }
        });


        return stackPane;
    }


    public BorderPane kquestions() throws FileNotFoundException {


        BorderPane borderPane = new BorderPane();
        Button button = new Button("<-");
        button.setMinWidth(30);
        button.setMinHeight(30);
        Button button1 = new Button("->");
        button1.setMinWidth(30);
        button1.setMinHeight(30);
        if (this.ind == 0) {
            button.setVisible(false);
        }
        button1.setOnAction(e -> {
            if (this.ind != questions2.size() - 1) {
                try {
                    this.ind = this.ind + 1;
                    Bahafestival.setScene(new Scene(kquestions(), 500, 500));

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                if (this.ind == questions2.size() - 1) {
                    try {
                        Bahafestival.setScene(new Scene(result(), 500, 500));

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    button1.setVisible(false);

                }
            }
        });
        button.setOnAction(e -> {
            try {
                this.ind = this.ind - 1;
                Bahafestival.setScene(new Scene(kquestions(), 500, 500));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        if (this.ind < questions2.size()) {
            if (questions2.get(this.ind).getDescription().contains("{blank}")) {

                Text text1 = new Text(this.ind + 1 + ")" + questions2.get(this.ind).getDescription().replace("{blank}", "____"));

                text1.setFill(Color.BLACK);
                text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                text1.setStyle("-fx-font-size: 15px;");
                Text text = new Text("Type your answer:");

                text.setX(180);
                text.setY(450);
                text.setFill(Color.BLACK);
                text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                text1.setFill(Color.BLACK);
                text1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

                TextField f = new TextField();
                Image image = new Image(new FileInputStream("src/fillin.png"));

                ImageView view = new ImageView(image);
                view.setFitHeight(300);
                view.setFitWidth(300);

                f.setText(data[this.ind]);
                f.setOnAction(e -> {
                    if (f.getText() != null) {
                        data[this.ind] = f.getText();

                    }
                });


                StackPane pane = new StackPane();
                pane.getChildren().addAll(text1);
                borderPane.getChildren().add(new Pane(text));
                borderPane.setLeft(new StackPane(button));
                borderPane.setRight(new StackPane(button1));
                borderPane.setTop(new StackPane(text1));
                borderPane.setCenter(new StackPane(view));
                borderPane.setBottom(new StackPane(f));


            } else {
                Image image = new Image(new FileInputStream("src/logo.png"));

                ImageView view = new ImageView(image);
                view.setFitHeight(300);
                view.setFitWidth(300);

                Test test2 = (Test) questions2.get(this.ind);
                Text text = new Text(this.ind + 1 + ")" + questions2.get(this.ind).getDescription());


                text.setFill(Color.BLACK);
                text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
                text.setStyle("-fx-font-size: 20px;");


                GridPane gridPane = new GridPane();
                Rectangle rectangleRed = new Rectangle(250, 50, 250, 50);
                rectangleRed.setFill(Color.RED);
                Rectangle rectangleYellow = new Rectangle(250, 50, 250, 50);
                rectangleYellow.setFill(Color.YELLOW);
                Rectangle rectangleBlue = new Rectangle(250, 50, 250, 50);
                rectangleBlue.setFill(Color.BLUE);
                Rectangle rectangleBlack = new Rectangle(250, 50, 250, 50);
                rectangleBlack.setFill(Color.BLACK);
                RadioButton radioButtonA = new RadioButton(test2.getOptionAt(0));
                // RadioButton radioButtonA = new RadioButton(questions2.get(ind).get);
                RadioButton radioButtonB = new RadioButton(test2.getOptionAt(1));
                RadioButton radioButtonC = new RadioButton(test2.getOptionAt(2));
                RadioButton radioButtonD = new RadioButton(test2.getOptionAt(3));

                ToggleGroup group = new ToggleGroup();
                radioButtonA.setToggleGroup(group);
                radioButtonB.setToggleGroup(group);
                radioButtonC.setToggleGroup(group);
                radioButtonD.setToggleGroup(group);

                gridPane.add(rectangleRed, 0, 0);
                gridPane.add(radioButtonA, 0, 0);
                gridPane.add(rectangleBlack, 0, 1);
                gridPane.add(radioButtonB, 0, 1);
                gridPane.add(rectangleYellow, 1, 0);
                gridPane.add(radioButtonD, 1, 0);
                gridPane.add(rectangleBlue, 1, 1);
                gridPane.add(radioButtonC, 1, 1);
                StackPane stackPane2 = new StackPane();
                stackPane2.getChildren().add(gridPane);
                borderPane.setCenter(new StackPane(view));
                borderPane.setTop(new StackPane(text));
                borderPane.setLeft(new StackPane(button));
                borderPane.setRight(new StackPane(button1));
                borderPane.setBottom(new Pane(gridPane));


                radioButtonA.setOnAction(e -> {
                    if (radioButtonA.isSelected()) {
                        arr[this.ind] = 1;
                        data[this.ind] = radioButtonA.getText();
                    }

                });
                radioButtonB.setOnAction(e -> {

                    if (radioButtonB.isSelected()) {
                        arr[this.ind] = 2;
                        data[this.ind] = radioButtonB.getText();
                    }

                });
                radioButtonC.setOnAction(e -> {

                    if (radioButtonC.isSelected()) {
                        arr[this.ind] = 3;
                        data[this.ind] = radioButtonC.getText();
                    }

                });

                radioButtonD.setOnAction(e -> {
                    if (radioButtonD.isSelected()) {
                        arr[this.ind] = 4;
                        data[this.ind] = radioButtonD.getText();
                    }

                });

                if (arr[this.ind] == 1) {
                    radioButtonA.setSelected(true);
                }
                if (arr[this.ind] == 2) {
                    radioButtonB.setSelected(true);
                }
                if (arr[this.ind] == 3) {
                    radioButtonC.setSelected(true);
                }
                if (arr[this.ind] == 4) {
                    radioButtonD.setSelected(true);
                }



            }

        }
        Timeline timer = new Timeline();
        Text Timer = new Text();
        timerStyle(Timer);

        timerAction(timer, Timer);

        Pane pane1 = new Pane();
        pane1.getChildren().add(Timer);
        borderPane.getChildren().add(pane1);
        return borderPane;
    }

    private void timerAction(Timeline timer, Text Timer){
        timer.setCycleCount(Animation.INDEFINITE);
        timer.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event ->
                                Timer.setText(timeFormat(sec+=1))));
        timer.play();

    }

    private void timerStyle(Text Timer){
        Timer.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Timer.setLayoutX(240);
        Timer.setLayoutY(45);
        Timer.setText(timeFormat(sec));

    }

    private String timeFormat(int sec) {
        min = sec / fromMinut();
        sec = sec % fromMinut();
        String strMin = String.valueOf(min);
        if (tekseru(min)) strMin = returnMin(strMin);
        String strSec = convert(sec);
        if (tekseru(sec)) strSec = returnSec(strSec);

        return strMin + ":" + strSec;
    }

    private String convert(int n){
        return n + "";
    }

    private boolean tekseru(int n){
        if (n < 10)return true;
        else return false;
    }

    private int fromMinut(){
        return (int)Math.pow(99999, 0) + 59;
    }

    private String returnMin(String strMin){
        return "0" + strMin;
    }

    private String returnSec(String strSec){
        return "0" + strSec;
    }

    public int checkanswer() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                if (data[i].equals(questions2.get(i).getAnswer())) {
                    count++;
                }
            }
        }
        return count;
    }

    public BorderPane result() throws FileNotFoundException {
        BorderPane borderPane1 = new BorderPane();

        double resultOfKahoot = (100 * checkanswer()) / questions2.size();
        String b = "Your result this" + " " + resultOfKahoot + "%";
        String g = "Number of Correct Answers:" + " " + (checkanswer() / 2) + "/" + questions2.size();
        Text text = new Text(b);
        text.setFill(Color.RED);
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        text.setStyle("-fx-font-size: 20px;");

        Text label = new Text(g);
        label.setFill(Color.BLUE);
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        label.setStyle("-fx-font-size: 20px;");

        label.setLayoutX(120);
        label.setLayoutY(40);
        Image image = new Image(new FileInputStream("src/result.jpg"));
        ImageView view = new ImageView(image);
        view.setFitHeight(300);
        view.setFitWidth(500);
Button buttonShow= new Button("Show Answer");
buttonShow.setTextFill(Color.WHITE);
        buttonShow.setMinHeight(20);
        buttonShow.setMinWidth(300);
        buttonShow.setLayoutX(110);
        buttonShow.setLayoutY(130);
        buttonShow.setStyle("-fx-background-color: red;");
   Button buttonclose = new Button("Close test");
   buttonclose.setMinHeight(20);
   buttonclose.setTextFill(Color.WHITE);
   buttonclose.setMinWidth(300);
   buttonclose.setLayoutX(110);
   buttonclose.setLayoutY(145);
   buttonclose.setStyle("-fx-background-color: blue;");
   buttonclose.setOnAction(e ->{
       System.exit(1);
   });

        String  k = "Finished in"+":"+timeFormat(sec)+"";
         text3 = new Text();
        text3.setText(k);
        text3.setY(90);
        text3.setX(100);
        text3.setFill(Color.BLACK);
        text3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        borderPane1.setTop(new StackPane(text));
        borderPane1.setBottom(new StackPane(view));
        borderPane1.getChildren().add(label);
borderPane1.setCenter(new Pane(buttonclose));
borderPane1.getChildren().add(new Pane(buttonShow));

borderPane1.getChildren().add(new Pane(text3));
        return borderPane1;
    }
}

