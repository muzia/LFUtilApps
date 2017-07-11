package com.example.admin.lfutilapp.commen_util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.util.List;

/**
 * 侧滑activity
 */
public class SliderFrame extends FrameLayout {

	public SliderFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public SliderFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SliderFrame(Context context) {
		super(context);
		init();
	}

	private void init() {
		/**第一步，创建Scroller的实例**/
		mScroller = new Scroller(getContext());
	}

	float currentY;
	float currentX;
	Scroller mScroller;
	Handler handler = new Handler();
	boolean enbale = true;

	public boolean isEnbale() {
		return enbale;
	}

	public void setEnbale(boolean enbale) {
		this.enbale = enbale;
	}

	float startX;
	long startTime;
	int duration = 500;
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (!enbale)
			return false;
		if (isRootActivity())
			return false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			startTime = System.currentTimeMillis();
			break;
		case MotionEvent.ACTION_MOVE:
			int scrollX = (int) (getScrollX() - (event.getX() - currentX));
			if (scrollX > 0) {
				scrollX = 0;
			}
			if (scrollX < -getWidth()) {
				scrollX = -getWidth();
			}
			scrollTo(scrollX, getScrollY());
			break;
		case MotionEvent.ACTION_UP:
			float time = (System.currentTimeMillis()-startTime)/1000f;
			float distance = event.getX() - startX;
			double speed = distance/time/getWidth();
/*			Log.e("speed", speed+"");
			Log.e("time", time+"");
			Log.e("distance", distance+"");
			Log.e("startTime", startTime+"");
			Log.e("System.currentTimeMillis()", System.currentTimeMillis()+"");*/
			/**第二步，调用startScroll()方法来初始化滚动数据并刷新界面，完成后续的滚动动作**/
			if (speed>1) {
				mScroller.startScroll(getScrollX(), getScrollY(), (int) (-getWidth() - getScrollX()), 0, duration);
			}else if (getScrollX() < -getWidth() / 2) {
				mScroller.startScroll(getScrollX(), getScrollY(), (int) (-getWidth() - getScrollX()), 0, duration);

			} else {
				mScroller.startScroll(getScrollX(), getScrollY(), (int) (0 - getScrollX()), 0, duration);
			}
			 invalidate();
			break;
		}
		currentX = event.getX();
		currentY = event.getY();
		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN && enbale) {
			if (ev.getX() < getWidth() / 25)
				return true;
		}
		return super.onInterceptTouchEvent(ev);
	}
	

	
	public boolean isRootActivity() {
		ActivityManager manager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
		int i = runningTaskInfos.get(0).numActivities;
		return i == 1;
	}
	@Override
	public void scrollTo(int x, int y) {
		//(0到-getWidth())
		super.scrollTo(x, y);
		float progress = x/(float)-getWidth();
		int value = (int) (200*(1-progress));
		setBackgroundColor(Color.argb(value, 20, 20, 20));
	}
	/**第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑**/
	@Override
	public void computeScroll() {
		// TODO Auto-generated method stub
		 if (mScroller.computeScrollOffset()) {
	            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
	            invalidate();
	        }else {
				if (mScroller.getCurrX() == -getWidth()) {
					Log.e("finaaaaaaaaaa", "finish");
					((FragmentActivity) getContext()).finish();
				}
			}
	}
}
