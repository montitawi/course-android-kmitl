package kmitl.lab04.montita58070114.simplemydot.model;

import java.io.Serializable;

public class DotSerializable implements Serializable{
    private int centerX;
    private int centerY;
    private int radius;


    @Override
    public String toString() {
        return "DotSerializable{" +
                "centerX=" + centerX +
                ", centerY=" + centerY +
                ", radius=" + radius +
                '}';
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}