import DataAccess.Repositories.RecordRepository;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class GameManager extends JFrame {
    private Player player;

    public GameManager(Player player) {
        this.player = player;
    }

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
                    loseGame();
                }
                if(gameObject instanceof Enemy && ((Enemy) gameObject).position.getY() < Game.WINDOW_HEIGHT){
                    upgradePlayer();
                    Game.increaseScore(((Enemy) gameObject).getReward());
                }
                toDispose.add(gameObject);
                gameObject.onDispose();
            }
        }
        if(toDispose.size() > 0) gameObjects.removeAll(toDispose);
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

    public void upgradePlayer(){
        Random rand = new Random();
        int randomNum = rand.nextInt(100);

        if(randomNum < Game.BONUS_CHANCE){
            randomNum = rand.nextInt(Upgrade.values().length);
            Upgrade randomUpgrade = Upgrade.fromId(randomNum);
            randomUpgrade.apply(player);
        }
    }

    public void loseGame(){
        RecordRepository recordRepository = new RecordRepository();
        try{
            recordRepository.createRecord(Game.getScore(), Game.getPlayerId(), LocalDate.now(), LocalTime.ofSecondOfDay(Game.getCurrentTimeInSeconds()));
            Game.restartGame();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

