package kmitl.lab04.montita58070114.simplemydot.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.pes.androidmaterialcolorpickerdialog.ColorPickerCallback;

import kmitl.lab04.montita58070114.simplemydot.R;
import kmitl.lab04.montita58070114.simplemydot.model.Dot;

public class EditDotFragment extends Fragment {
    public interface OnDotUpdatedListener {
        void onDotUpdate(Dot dot, int position);
    }

    private static final String POSITION = "position";
    private static final String DOT = "dot";
    private Dot dot;
    private int position;
    private int currentSize;
    private int currentColor;
    OnDotUpdatedListener listener;

    public EditDotFragment() {
        // Required empty public constructor
    }


    public static EditDotFragment newInstance(Dot dot, int position) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable(DOT, dot);
        args.putInt(POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public static EditDotFragment newInstance() {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dot = getArguments().getParcelable(DOT);
        position = getArguments().getInt(POSITION);
        listener = (OnDotUpdatedListener) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        contentSetup(rootView);
        return rootView;
    }

    private void contentSetup(View rootView) {
        getActivity().setTitle("Edit Dot");
        Toast.makeText(getContext(), "Edit Dot", Toast.LENGTH_SHORT).show();
        final Button btnChangeColor = (Button) rootView.findViewById(R.id.btnChangeColor);
        final TextView colorText = (TextView) rootView.findViewById(R.id.colorText);
        colorText.setTextColor(dot.getColor());
        currentColor = dot.getColor();
        final SeekBar sizeSeekBar = (SeekBar) rootView.findViewById(R.id.sizeSeekBar);
        final TextView sizeText = (TextView) rootView.findViewById(R.id.sizeTV);
        Button btnCancel = (Button) rootView.findViewById(R.id.btnCancel);
        Button btnOK = (Button) rootView.findViewById(R.id.btnOK);

        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ColorPicker cp = new ColorPicker(getActivity(), Color.red(dot.getColor()), Color.green(dot.getColor()), Color.blue(dot.getColor()));
                cp.show();
                cp.setCancelable(false);
                cp.setCallback(new ColorPickerCallback() {
                    @Override
                    public void onColorChosen(@ColorInt int c) {
                        int color = Color.rgb(Color.red(c), Color.green(c), Color.blue(c));
                        colorText.setTextColor(color);
                        currentColor = color;
                        cp.cancel();
                    }
                });
            }
        });


        sizeSeekBar.setProgress(dot.getRadius());
        sizeSeekBar.setMax(200);
        sizeText.setText("Size: " + dot.getRadius());
        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sizeText.setText("Radius: " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentSize = sizeSeekBar.getProgress();
                dot.setRadius(currentSize);
                dot.setColor(currentColor);
                listener.onDotUpdate(dot, position);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDotUpdate(dot, position);
            }
        });
    }

}
