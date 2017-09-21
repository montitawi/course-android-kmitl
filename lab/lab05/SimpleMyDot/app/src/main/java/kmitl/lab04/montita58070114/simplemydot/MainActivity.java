package kmitl.lab04.montita58070114.simplemydot;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;


import java.util.Random;

import kmitl.lab04.montita58070114.simplemydot.fragment.DotFragment;
import kmitl.lab04.montita58070114.simplemydot.model.Colors;
import kmitl.lab04.montita58070114.simplemydot.model.Dot;
import kmitl.lab04.montita58070114.simplemydot.model.DotParcelable;
import kmitl.lab04.montita58070114.simplemydot.model.Dots;
import kmitl.lab04.montita58070114.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity
        implements DotView.OnDotViewPressListener{
    private DotView dotView;
    private Dots dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, new DotFragment().newInstance()).commit();
    }


    @Override
    public void onDotViewPressed(int x, int y, String status) {
        Random random = new Random();
        int radius = random.nextInt(200);
        int dotPosition = dots.findDot(x, y);
        if (dotPosition == -1) {
            Dot newDot = new Dot(x, y, radius, new Colors().createColor());
            dots.addDot(newDot);
        } else {
            alertDialog(dotPosition);
        }
    }

    public void alertDialog(final int dotPosition) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("What you want to do?");
        alertDialogBuilder
                .setMessage("")
                .setCancelable(true)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dots.removeBy(dotPosition);
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final DotParcelable dotParcelable = new DotParcelable(dotPosition, dots.getAllDot().get(dotPosition).getColor(), dots.getAllDot().get(dotPosition).getRadius());
                        Intent editActIntent = new Intent(MainActivity.this, EditDotActivity.class);
                        editActIntent.putExtra("dotParcelable", dotParcelable);
                        startActivityForResult(editActIntent, 1);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            DotParcelable dotParcelable = data.getParcelableExtra("reDotParcelable");
            if (resultCode == Activity.RESULT_OK) {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setColor(dotParcelable.getColor());
            } else {
                dots.getAllDot().get(dotParcelable.getDotPosition()).setRadius(dotParcelable.getRadius());
            }
        }
    }




}


