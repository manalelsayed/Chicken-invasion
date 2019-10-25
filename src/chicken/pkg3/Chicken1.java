
package chicken.pkg3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Chicken1 extends Application {
    
    Chickens[][] chkns;
    Boat boat;
    
    @Override
    public void start(Stage stage) {
        
        // intro
        Pane introPane = new Pane();
        Scene scene = new Scene(introPane);
        Label l = new Label("Chicken Invaders");
        l.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,80));
        l.setTranslateX(360);
        l.setTranslateY(30);
        l.setStyle("-fx-text-fill: #fff;");
        Button btn = new Button("Play");
        btn.setTranslateX(650);
        btn.setTranslateY(500);
        btn.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD,30));
        ImageView bg = new ImageView("file:images/Background.png");
        introPane.getChildren().addAll(bg,l,btn);
        
        // start game
        btn.setOnAction(e -> {

            Pane gamePane = new Pane();
            ImageView bg2 = new ImageView("file:images/backgroundf.png");
            gamePane.getChildren().add(bg2);
            chkns = new Chickens[9][4];
            boat = new Boat(gamePane,chkns);
            gamePane.getChildren().add(boat.getShape());

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 9; j++) {
                    chkns[j][i] = new Chickens();
                    chkns[j][i].getShape().setX(1000/9*j+200);
                    chkns[j][i].getShape().setY(i*100+15);
                    chkns[j][i].setPane(gamePane);
                    chkns[j][i].setPlane(boat);
                    gamePane.getChildren().add(chkns[j][i].getShape());
                    chkns[j][i].startMove();
                }
            }


            Timeline eggAnimation = new Timeline();
            KeyFrame frame1 = new KeyFrame(Duration.millis(500), e2 -> {
                int c1i = (int)(Math.round(Math.random()*7));
                int c1j = (int)(Math.round(Math.random()*2));
                if (chkns[c1i][c1j].isAlive())
                    chkns[c1i][c1j].egg();
            });
            eggAnimation.getKeyFrames().addAll(frame1);
            eggAnimation.setCycleCount(Timeline.INDEFINITE);
            eggAnimation.play();

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent e) {
                    switch(e.getCode()) {
                        case RIGHT:
                            if (boat.getShape().getX() < gamePane.getWidth() - 100) {
                                boat.moveRight();
                            }
                            break;
                        case LEFT:
                            if(boat.getShape().getX() > 0) {
                                boat.moveLeft();
                            }
                            break;
                        case UP:
                            if (boat.getShape().getY() > 450){
                            boat.moveUp();
                        }
                            break;
                        case DOWN:
                            if(boat.getShape().getY()< gamePane.getHeight() - 110){
                                boat.moveDown();
                            }
                        case SPACE:
                            boat.shoot();
                            break;
                    }
                }
            });
            
            stage.getScene().setRoot(gamePane);
        });
        stage.setTitle("Chickens");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }   
}
