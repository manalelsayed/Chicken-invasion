
package chicken.pkg3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Chickens extends Item {

    private Pane pane;
    private boolean alive;
    private Boat boat;
    private static int dead = 0;
    private int move = 5;
    Chickens(){
        super("file:images/chick.png");
        alive = true;
    }

    public boolean isAlive() {
        return alive;
    }
    
    public void die() {
        shape.setVisible(false);
        alive = false;
        dead++;
        if (dead == 36)
            boat.win();
    }
    
    public void egg() {
        Egg Egg = new Egg(shape.getX()+40,shape.getY(),pane,boat);
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setPlane(Boat boat) {
        this.boat = boat;
    }
    
    public void startMove() {
        System.out.println(shape.getX());
        double startX = shape.getX()-50;
        double endX = shape.getX()+50;
        Timeline movement = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            shape.setX(shape.getX() + move);
            if (shape.getX() >= endX || shape.getX() <= startX)
                move *= -1;
        }));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }
    
}
