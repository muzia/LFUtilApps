package com.example.admin.lfutilapp.custom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.admin.lfutilapp.R;

import java.util.ArrayList;
import java.util.List;

public class VerticalRunningLayout extends RelativeLayout {
    int textResId = -1;
    int childHeight;
    /**
     * 每个Item当前展示几行TextView
     */
    int mLines = 3;
    TextView[] children;
    int position = 0;
    Scroller scroller;
    Handler handler = new Handler();
    long delayMillis = 4000;
    List<String> mData;
    Runnable sizeChangePost;
    LayoutInflater inflater;
    private OnItemClickListener listener;

    public VerticalRunningLayout(Context context, AttributeSet attrs,
                                 int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getAttrs(context, attrs);
        init();
    }

    public VerticalRunningLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(context, attrs);
        init();
    }


    public VerticalRunningLayout(Context context) {
        super(context);
        init();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private void getAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VerticalRunningLayout);
        mLines = typedArray.getInt(R.styleable.VerticalRunningLayout_lines, 1);
        textResId = typedArray.getResourceId(R.styleable.VerticalRunningLayout_itemResId, -1);
    }

    private void init() {
        scroller = new Scroller(getContext());
        childHeight = 0;
        mData = new ArrayList<>();
        mData.add("");
        mData.add("");
        reLayout();
    }

    private void newChildren() {
        removeAllViews();
        children = new TextView[mLines * 2];
        for (int i = 0; i < children.length; i++) {
            children[i] = newChild();
            children[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.OnItemClick((int) v.getTag());
                    }
                }
            });
            children[i].getLayoutParams().height = childHeight;
            addView(children[i]);
            children[i].setText(mData.get(i % mData.size()));
            children[i].setTag(i % mData.size());
        }

    }

    LayoutInflater getInflater() {
        if (inflater == null) {
            inflater = LayoutInflater.from(getContext());
        }
        return inflater;
    }

    @NonNull
    private TextView newChild() {
        if (textResId != -1) {
            return (TextView) getInflater().inflate(textResId, this, false);
        } else {
            return new TextView(getContext());
        }
    }

    public int getPosition() {
        return position;
    }

    public void setTextResourceId(int resourceId) {
        textResId = resourceId;
        reLayout();
    }

    public void setLines(int lines) {
        mLines = lines;
        reLayout();
    }

    private void reLayout() {
        //为了结局getHeight()为0的情况，有时候post之后getHeight也还是为0，所有将onSizeChanged和post结合使用
        post(new Runnable() {
            @Override
            public void run() {
                if (getHeight() == 0) {
                    sizeChangePost = this;
                } else {
                    childHeight = getHeight() / mLines;
                    newChildren();
                    startScroll();
                }
            }
        });

    }

    public void setDataList(final List<String> data) {
        if (data == null)
            return;
        if (data.size() < 1)
            return;
        this.mData = data;
        if (children != null) { //children 不等于null代表已经布局完成，在某些特殊情况下有可能布局还没有完成，setDataList就被调用了
            setItemText();
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (sizeChangePost != null && h != 0 && w != 0) {
            post(sizeChangePost);
            sizeChangePost = null;
        }
    }

    private void startScroll() {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scroller.startScroll(0, 0, 0, childHeight * mLines,
                        (int) delayMillis / 2);
                getScrollerValues();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int lPosition = (position + 1) * mLines;
                        for (int i = 0; i < mLines; i++) {
                            children[i + mLines].setText(mData.get((lPosition + i)
                                    % mData.size()));
                            children[i + mLines].setTag((lPosition + i)
                                    % mData.size());
                        }
                    }
                }, 1000 / 100);
                handler.postDelayed(this, delayMillis);
            }

        }, delayMillis);
    }

    private void getScrollerValues() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (mData == null)
                    return;
                if (mData.size() < 1)
                    return;
                if (scroller.computeScrollOffset()) {
                    Log.e("computeScrollOffset", scroller.getCurrX() + "," + scroller.getCurrY());
                    scrollTo(scroller.getCurrX(), scroller.getCurrY());
                    handler.postDelayed(this, 1000 / 100);
                } else {
                    position++;
                    setItemText();
                }
            }
        });
    }

    private void setItemText() {
        int lPosition = position * mLines;
        for (int i = 0; i < mLines; i++) {
            children[i].setText(mData.get((lPosition + i)
                    % mData.size()));
            children[i + mLines].setTag((lPosition + i) % mData.size());
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, i * (b - t) / mLines, r,
                    (i + 1) * (b - t) / mLines);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }
}
