package com.example.admin.lfutilapp.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EllipsizingTextView extends AppCompatTextView {
    private static final String ELLIPSIS = "…";
    private String END_TEXT = "";
    private String FULL_ELLIPSIS = ELLIPSIS + END_TEXT;

    public interface EllipsizeListener {
        void ellipsizeStateChanged(boolean ellipsized);
    }

    private final List<EllipsizeListener> ellipsizeListeners = new ArrayList<EllipsizeListener>();

    private boolean isEllipsized;
    private boolean isStale;
    private boolean programmaticChange;
    private String fullText;
    private int mMaxLines = -1;
    private float lineSpacingMultiplier = 1.0f;
    private float lineAdditionalVerticalPadding = 0.0f;
    private float mMarginLeft = 0.0f;
    private float mMarginRight = 0.1f;

    public EllipsizingTextView(Context context) {
        super(context);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 开始没加这两行的时候，一直不对，maxlinex在textChange里面会被改变为-1
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.maxLines});
        setMaxLines(a.getInt(0, 2));
        a.recycle();
        TypedArray marginLeft = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_marginLeft});
        TypedArray marginRight = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_marginRight});
        mMarginLeft = marginLeft.getDimension(0, -1);
        mMarginRight = marginRight.getDimension(0, -1);
        Log.v("TAG", "marginLeft : " + mMarginLeft);
        Log.v("TAG", "marginRight : " + mMarginRight);
    }

    public EllipsizingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 开始没加这两行的时候，一直不对，maxlinex在textChange里面会被改变为-1
        TypedArray a = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.maxLines});
        setMaxLines(a.getInt(0, 2));
        a.recycle();
        TypedArray marginLeft = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_marginLeft});
        TypedArray marginRight = context.obtainStyledAttributes(attrs, new int[]{android.R.attr.layout_marginRight});
        mMarginLeft = marginLeft.getDimension(0, -1);
        mMarginRight = marginRight.getDimension(0, -1);
        Log.v("TAG", "marginLeft : " + mMarginLeft);
        Log.v("TAG", "marginRight : " + mMarginRight);
    }

    public void addEllipsizeListener(EllipsizeListener listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        ellipsizeListeners.add(listener);
    }

    public void removeEllipsizeListener(EllipsizeListener listener) {
        ellipsizeListeners.remove(listener);
    }

    public boolean isEllipsized() {
        return isEllipsized;
    }

    public void setEndText(String endText) {
        this.END_TEXT = endText;
        FULL_ELLIPSIS = ELLIPSIS + END_TEXT;
        isStale = true;
    }

    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        this.mMaxLines = maxLines;
        isStale = true;
    }

    public int getMaxLine() {
        return mMaxLines;
    }

    public int getTextLines() {
        return createWorkingLayout(fullText).getLineCount();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            if (mMarginLeft >= 0.0f && mMarginRight >= 0.0f){
                int displayWidth = getResources().getDisplayMetrics().widthPixels;
                int exceptWidth = displayWidth - (int)mMarginLeft - (int)mMarginRight;
                Log.v("TAG","onMeasure exceptWidth ：" + exceptWidth);
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth, MeasureSpec.EXACTLY);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setLineSpacing(float add, float mult) {
        this.lineAdditionalVerticalPadding = add;
        this.lineSpacingMultiplier = mult;
        super.setLineSpacing(add, mult);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int before, int after) {
        super.onTextChanged(text, start, before, after);
        if (!programmaticChange) {
            fullText = text.toString();
            isStale = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isStale) {
            super.setEllipsize(null);
            resetText();
        }
        super.onDraw(canvas);
    }

    private void resetText() {
        int maxLines = getMaxLine();
        String workingText = fullText;
        String measureLengthText = fullText + END_TEXT;
        boolean ellipsized = false;
        Log.v("TAG", "maxLines : " + maxLines);
        if (maxLines != -1) {
            Layout measureLayout = createWorkingLayout(measureLengthText);
            Log.v("TAG", "measure lines : " + measureLayout.getLineCount());
            if (measureLayout.getLineCount() > maxLines) {
                //需要显示...
                Layout tempLayout = createWorkingLayout(fullText);
                workingText = fullText.substring(0, tempLayout.getLineEnd(maxLines - 1)).trim();
                Layout layout2 = createWorkingLayout(workingText + FULL_ELLIPSIS);
                while (layout2.getLineCount() > maxLines) {
                    workingText = workingText.substring(0, workingText.length() - 1);
                    layout2 = createWorkingLayout(workingText + FULL_ELLIPSIS);
                }
                workingText = workingText + FULL_ELLIPSIS;
                ellipsized = true;
            } else {
                //不需要显示...
                workingText = workingText + END_TEXT;
                ellipsized = false;
            }
        }

        if (!workingText.equals(getText())) {
            programmaticChange = true;
            try {
                setText(workingText);
            } finally {
                programmaticChange = false;
            }
        }
        isStale = false;
        if (ellipsized != isEllipsized) {
            isEllipsized = ellipsized;
            for (EllipsizeListener listener : ellipsizeListeners) {
                listener.ellipsizeStateChanged(ellipsized);
            }
        }

    }

    private Layout createWorkingLayout(String workingText) {
        return new StaticLayout(workingText, getPaint(), getWidth() - getPaddingLeft()
                - getPaddingRight(), Layout.Alignment.ALIGN_NORMAL, lineSpacingMultiplier,
                lineAdditionalVerticalPadding, false);
    }

    @Override
    public void setEllipsize(TextUtils.TruncateAt where) {
        // Ellipsize settings are not respected } }
    }
}