package kmitl.lab03.montita58070114.simplemydot.model;

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
        this.listener.onDotChangeListener(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
        this.listener.onDotChangeListener(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
