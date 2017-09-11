package kmitl.lab04.montita58070114.simplemydot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

import kmitl.lab04.montita58070114.simplemydot.model.DotParcelable;
import kmitl.lab04.montita58070114.simplemydot.model.DotSerializable;


public class SecondActivity extends AppCompatActivity {
    private DotSerializable dotSerializable;
    private DotParcelable dotParcelable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tvValueX = (TextView) findViewById(R.id.tvValuesX);
        TextView tvDot = (TextView) findViewById(R.id.tvDot);
        int x = getIntent().getIntExtra("xValue",0);
        tvValueX.setText(String.valueOf(x));

        dotSerializable = (DotSerializable) getIntent().getSerializableExtra("dotSerializable");
        tvDot.setText("CenterX : " + dotSerializable.getCenterX() + " CenterY : " + dotSerializable.getCenterY()
                + " Radius : " + dotSerializable.getRadius());
        //tvDot.setText(dotSerializable.toString());

        dotParcelable = (DotParcelable) getIntent().getParcelableExtra("dotParcelable");
        tvDot.setText(dotParcelable.toString());

    }
}
