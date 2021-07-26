package com.amol.test.textstyle;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class EuropaRegular extends AppCompatTextView {

    public EuropaRegular(Context context) {
        super(context);
        init(context);
    }

    public EuropaRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EuropaRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        Typeface tf = Typeface.createFromAsset(context.getAssets(), "Nunito-Regular.ttf");
        setTypeface(tf);
    }
}