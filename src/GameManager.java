import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JFrame {
    public void update(ArrayList<GameObject> gameObjects) {
        for(GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    public void disposeOfObjects(ArrayList<GameObject> gameObjects) {
        ArrayList<GameObject> toDispose = new ArrayList<>();
        for(GameObject gameObject : gameObjects) {
            if(gameObject.getToDispose()){
                gameObject.onDispose();
                if(gameObject instanceof Player){

                }
                if(gameObject instanceof Enemy && ((Enemy) gameObject).position.getY() < Game.WINDOW_HEIGHT){
                    Game.increaseScore(50);
                }
                toDispose.add(gameObject);
                gameObject.onDispose();
            }
        }
        gameObjects.removeAll(toDispose);
    }


    public void checkCollision(ArrayList<GameObject> gameObjects) {
        ArrayList<HardBody> hardBodies = new ArrayList<>();
        for(GameObject gameObject : gameObjects) {
            if(gameObject instanceof HardBody){
                hardBodies.add((HardBody) gameObject);
            }
        }

        for(int i = 0; i < hardBodies.size(); i++){
            for(int j = i + 1; j < hardBodies.size(); j++){
                HardBody first = hardBodies.get(i);
                HardBody second = hardBodies.get(j);
                if(first.getBounds().intersects(second.getBounds())) {
                    first.onCollision(second);
                    second.onCollision(first);
                }
            }
        }
    }
}

