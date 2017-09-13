package kmitl.lab04.montita58070114.simplemydot.model;

import android.graphics.Color;

import java.util.Random;

public class Colors {

    public int createColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return Color.rgb(red, green, blue);
    }
}