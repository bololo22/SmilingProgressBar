package com.example.smilingprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.smilingprogressbar.adapter.OnProgressBarChange;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class SmilingProgressBarView extends LinearLayout {
    private ImageView smileView;
    private ProgressBar progressBar;

    private SeekBar volumeControl = null;
    private ArrayList<OnProgressBarChange> mCallbacks = new ArrayList<OnProgressBarChange>();

    public SmilingProgressBarView(Context context){
        super(context);
        init(context);
    }

    public SmilingProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sample_smiling_progress_bar, this);
        smileView = (ImageView) findViewById(R.id.smile);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        volumeControl = (SeekBar) findViewById(R.id.seekBar1);

        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setProgress(volumeControl.getProgress());
                for (int i = 0; i < mCallbacks.size(); i++) {
                    mCallbacks.get(i).onChange(volumeControl.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(), "seek bar progress:" + volumeControl.getProgress(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        final float[] roundedCorners = new float[]{5, 5, 5, 5, 5, 5, 5, 5};
        ShapeDrawable pgDrawable = new ShapeDrawable(new RoundRectShape(roundedCorners, null, null));
        pgDrawable.setBounds(1, 1, 1, 1);
        String MyColor = "#7FFF00";
        pgDrawable.getPaint().setColor(Color.parseColor(MyColor));
        ClipDrawable progress = new ClipDrawable(pgDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL);
        progressBar.setProgressDrawable(progress);

        updateSmile();
    }

    public void setProgress(int progress) {
        volumeControl.setProgress(progress);
        updateSmile();
    }

    public int getProgress(){
        return volumeControl.getProgress();
    }

    private void updateSmile() {
        int progress = volumeControl.getProgress();

        if (progress < 12) {
            smileView.setImageResource(R.drawable.pic1);
        } else if (progress < 24) {
            smileView.setImageResource(R.drawable.pic2);
        } else if (progress < 36) {
            smileView.setImageResource(R.drawable.pic3);
        } else if (progress < 48) {
            smileView.setImageResource(R.drawable.pic4);
        } else if (progress < 60) {
            smileView.setImageResource(R.drawable.pic5);
        } else if (progress < 72) {
            smileView.setImageResource(R.drawable.pic6);
        } else if (progress < 84) {
            smileView.setImageResource(R.drawable.pic7);
        } else {
            smileView.setImageResource(R.drawable.pic8);
        }
    }

    public void onProgressChange(OnProgressBarChange onProgressBarChange) {
        mCallbacks.add(onProgressBarChange);
    }
}
