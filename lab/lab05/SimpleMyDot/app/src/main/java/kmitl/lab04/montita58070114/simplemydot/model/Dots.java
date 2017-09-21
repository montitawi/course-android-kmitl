package kmitl.lab04.montita58070114.simplemydot.model;

import java.util.ArrayList;
import java.util.List;

public class Dots {

    public interface OnDotsChangeListener {
        void onDotsChanged(Dots dots);
    }

    private OnDotsChangeListener listener;

    public void setListener(OnDotsChangeListener listener) {
        this.listener = listener;
    }

    private List<Dot> allDot = new ArrayList<>();
    private List<Dot> backupDot = new ArrayList<>();

    public List<Dot> getAllDot() {
        return allDot;
    }

    public void addDot(Dot dot) {
        this.allDot.add(dot);
        this.listener.onDotsChanged(this);
    }

    public void removeBy(int position) {
        allDot.remove(position);
        this.listener.onDotsChanged(this);
    }

    public void clearAll() {
        allDot.clear();
        this.listener.onDotsChanged(this);
    }

    public int findDot(int x, int y) {
        for (int i = 0; i < allDot.size(); i++) {
            int centerX = allDot.get(i).getCenterX();
            int centerY = allDot.get(i).getCenterY();
            double distance = Math.sqrt(Math.pow(centerX - x, 2)) +
                    Math.sqrt(Math.pow(centerY - y, 2));
            if (distance <= allDot.get(i).getRadius() ) {
                return i;
            }
        }
        return -1;
    }
    public void undo(){
        if(allDot.isEmpty()){
            allDot = new ArrayList<>(backupDot);
            backupDot.clear();
        }else {
            if(backupDot.isEmpty()){
                allDot.remove(allDot.size()-1);
            }else{
                allDot.add(backupDot.get(0));
                backupDot.remove(0);
            }

        }
        this.listener.onDotsChanged(this);
    }

}