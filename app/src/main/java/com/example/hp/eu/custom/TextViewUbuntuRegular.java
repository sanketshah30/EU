package com.example.hp.eu.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by User on 08-09-2016.
 */
public class TextViewUbuntuRegular extends AppCompatTextView {
    public TextViewUbuntuRegular(Context paramContext) {
        super(paramContext);
        a();
    }

    public TextViewUbuntuRegular(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a();
    }

    public TextViewUbuntuRegular(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        a();
    }

    public void a() {
        if (Build.VERSION.SDK_INT < 21) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Ubuntu-Regular_0.ttf"), 0);
            return;
        }
        int i = getTypeface().getStyle();
        if (i == 1) {
            setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Ubuntu-Regular.ttf"), i);
            return;
        }
        setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Ubuntu-Regular.ttf"), 0);
    }
}
