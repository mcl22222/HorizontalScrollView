package com.example.testapplication;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout contentLinearLayout;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private HorizontalScrollView scrollView;

    private Button btn1;
    private Button btn2;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = findViewById(R.id.bt_click);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    private void showPopupWindow() {
        View wh = LayoutInflater.from(this).inflate(R.layout.item_main, null);
        scrollView = wh.findViewById(R.id.scrollView);
        layout1 = wh.findViewById(R.id.layout1);
        layout2 = wh.findViewById(R.id.layout2);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final int width = getWidthInPx(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

        ViewGroup.LayoutParams params1 = layout1.getLayoutParams();
        ViewGroup.LayoutParams params2 = layout2.getLayoutParams();
        params1.width = width;
        params2.width = width;
        layout1.setLayoutParams(layoutParams);
        layout2.setLayoutParams(layoutParams);

        final WPopupWindow popupWindow = new WPopupWindow(wh);
        popupWindow.showAtLocation(getContentView(MainActivity.this), Gravity.BOTTOM, 0, 0);

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        wh.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(0, 0);
            }
        });
        wh.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.scrollTo(width, 0);
            }
        });

    }

    public int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    private static View getContentView(Activity ac) {
        ViewGroup view = (ViewGroup) ac.getWindow().getDecorView();
        FrameLayout content = view.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }
}
