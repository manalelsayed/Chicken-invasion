
package chicken.pkg3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Arrow extends Item {
   int c = -1;
    Arrow(double x,double y, Pane pane, Chickens[][] chickens){

    super("file:images/arrow.png");
        // get column
        for (int i = 0; i < 9; i++) {
            double start = chickens[i][0].getShape().getX();
            if (x >= start && x <= start+100) {
                c = i;
                break;
            }
        }
        
    shape.setX(x);
    shape.setY(y);
    pane.getChildren().add(1,shape);
    Timeline animation = new Timeline();
    KeyFrame frame = new KeyFrame(Duration.millis(30), e -> {
        shape.setY(shape.getY()-10);
        int last = -1;
        if (c != -1) {
            for (int i = 0; i < 4; i++) {
                if (chickens[c][i].isAlive()) {
                    last = i;
                }
            }
        }
        if (last != -1) {
            if (shape.getY() <= chickens[c][last].getShape().getY()+80) {
                chickens[c][last].die();
                animation.stop();
                shape.setVisible(false);
            }
        }
    });
    animation.getKeyFrames().add(frame);
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.play();
    }

}
