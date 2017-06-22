package com.example.admin.lfutilapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.lfutilapp.custom_textView.EllipsizingTextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewGroup mRootView;
//    private TextView tv1,tv2;
    private EllipsizingTextView elli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityOptions.makeSceneTransitionAnimation(activity, pairs).toBundle();
//        mRootView = (ViewGroup) findViewById(R.id.root_view);
//        tv1 = (TextView) findViewById(R.id.tv1);
//      tv1.setText(formatRatees("0.1350"));
        elli= (EllipsizingTextView) findViewById(R.id.elli);
        elli.setMaxLines(1);
        elli.setEndText("(阅读完毕)");
    }
    public static String formatRatees(String amt) {
        String formAmt = null;

        try {
		/*	if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
				Double amotd = Double.valueOf(amt) * 100.00;
				DecimalFormat df = new DecimalFormat();
				String formt = "00.00";
				df.applyPattern(formt);
				formAmt = df.format(amotd);
			}
			else{
				if (amt.contains(",")){
					amt.replace(",",".");
				}
			}*/
            if (amt.contains(",")){
                amt.replace(",",".");
            }
            Double amotd = Double.valueOf(amt) * 100.00;
            DecimalFormat df = new DecimalFormat();
            String formt = "00.00";
            df.applyPattern(formt);
            formAmt = df.format(amotd);

        } catch (Exception e) {
            return null;
        }
        return formAmt;
    }
    //true/false-->消费
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
    //若super=false-->activity:onTouchEvent
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(enterAnim, exitAnim);
    }
    @Override
    public void onClick(View v) {
        //自动的根据状态的区别去生成动画效果：界面渐变
       /* TransitionManager.beginDelayedTransition(mRootView,new Fade());
        toggleVisibility(tv1,tv2);*/
    }
 /*   private static void toggleVisibility(View... views) {
        for (View view : views) {
            boolean isVisible = view.getVisibility() == View.VISIBLE;
            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
        }
    }*/
}
