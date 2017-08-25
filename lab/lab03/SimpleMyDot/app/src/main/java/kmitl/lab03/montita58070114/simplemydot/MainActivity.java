package kmitl.lab03.montita58070114.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import kmitl.lab03.montita58070114.simplemydot.model.Dot;
import kmitl.lab03.montita58070114.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements Dot.onDotChangeListener {

    private Dot dot;
    private DotView dotView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dot = new Dot(this, 0, 0, 20);
        dotView = (DotView) findViewById(R.id.dotView);
    }

    public void onRandomDot(View view) {
        Random random = new Random();
        dot.setCenterX(random.nextInt(this.dotView.getWidth()));
        dot.setCenterY(random.nextInt(this.dotView.getHeight()));

    }

    @Override
    public void onDotChangeListener(Dot dot) {

        dotView.setDot(dot);
        dotView.invalidate();
    }
}
