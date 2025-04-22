import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {
    protected boolean toDispose = false;

    public boolean getToDispose() { return toDispose; }

    public void onStart(){}

    public void update(){
        //System.out.println("Update object");
    }

    public void onDispose(){}
}
