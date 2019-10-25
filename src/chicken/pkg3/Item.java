
package chicken.pkg3;

import javafx.scene.image.ImageView;

public class Item {
    protected ImageView shape;
    
    Item(String path) {
        shape = new ImageView(path);
    }
    
    public ImageView getShape() {
         return shape;
     }
}
