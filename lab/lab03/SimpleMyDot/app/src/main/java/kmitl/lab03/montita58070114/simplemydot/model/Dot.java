package kmitl.lab03.montita58070114.simplemydot.model;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class Dot {


    public interface onDotChangeListener {
        void onDotChangeListener(Dot dot);
    }

    private onDotChangeListener listener;

    public void setListener(onDotChangeListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int red;
    private int green;
    private int blue;
    ArrayList<Integer> listCenterx = new ArrayList<>();
    ArrayList<Integer> listCentery = new ArrayList<>();
    ArrayList<Integer> listRadius = new ArrayList<>();
    ArrayList<Integer> listred = new ArrayList<>();
    ArrayList<Integer> listgreen = new ArrayList<>();
    ArrayList<Integer> listblue = new ArrayList<>();


    public void clearDot() {
        listCenterx.clear();
        listCentery.clear();
        listRadius.clear();
        this.listener.onDotChangeListener(this);
    }

    public Dot(onDotChangeListener listener, int centerX, int centerY, int radius) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;

    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
        listCenterx.add(centerX);
        this.listener.onDotChangeListener(this);

    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        listCentery.add(centerY);
        this.listener.onDotChangeListener(this);
    }


    public int getRadius() {
        return radius;
    }


    public void setRadius(int radius) {
        this.radius = radius;
        listRadius.add(radius);
    }


    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
        listred.add(red);
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
        listgreen.add(green);
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
        listblue.add(blue);
    }

    public ArrayList<Integer> getlistCenterx() {
        return listCenterx;
    }

    public ArrayList<Integer> getlistCentery() {
        return listCentery;
    }

    public ArrayList<Integer> getlistRadius() {
        return listRadius;
    }

    public ArrayList<Integer> getListred() {
        return listred;
    }

    public ArrayList<Integer> getListgreen() {
        return listgreen;
    }

    public ArrayList<Integer> getListblue() {
        return listblue;
    }
}
