
package chicken.pkg3;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Boat extends Item {
    private Pane pane;
    private final Chickens[][] chickens;
    private boolean alive;
    private boolean winner;
    
    Boat(Pane pane, Chickens[][] chickens){
        //code
        super("file:images/boat.PNG");
        alive = true;
        winner=  false;
        shape.setX(400);
        shape.setY(650);
        this.pane = pane;
        this.chickens = chickens;
    }
    public void moveRight(){
        shape.setX(shape.getX() + 20);
    }
     public void moveLeft(){
          shape.setX(shape.getX() - 20);
     }
     public void moveUp(){
      shape.setY(shape.getY() - 10);
     }
     public void moveDown(){
          shape.setY(shape.getY() + 10);
     }
     
     public void shoot() {
         if (alive)
             new Arrow(shape.getX()+40,shape.getY(), pane,chickens); 
     }
     
     public void die() {
         if (alive && !winner) {
            shape.setVisible(false);
            alive = false;
            showMsg("LOSER");
         }
     }
    
     public void win() {
         winner = true;
         showMsg("Good job!");
     }
     
     private void showMsg(String msg) {
        Rectangle rect = new Rectangle(0,0,pane.getWidth(),pane.getHeight());
        rect.setFill(new Color(0,0,0,0.75));
        Label t = new Label(msg);
        t.setTranslateX(pane.getWidth()/2-100);
        t.setTranslateY(pane.getHeight()/2-25);
        t.setStyle("-fx-text-fill: #fff;");
        t.setFont(Font.font(50));
        pane.getChildren().addAll(rect,t);
     }
}