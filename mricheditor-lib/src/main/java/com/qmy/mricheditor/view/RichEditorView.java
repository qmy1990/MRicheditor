package com.qmy.mricheditor.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.qmy.qricheditor.R;

/**
 * 作者：邱明月
 * 创建时间：2017/2/25 0025
 * 修改时间：
 * 作者邮箱：530594053@qq.com
 * 描述：
 */

public class RichEditorView extends RelativeLayout {

    private int textSize = 15;

    private boolean isBold = false;


    private RichEditor richEditor;

    public RichEditorView(Context context) {
        this(context, null);
    }

    public RichEditorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RichEditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context, attrs);
        initView(context);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RichEditorView);
       textSize = typedArray.getDimensionPixelSize(R.styleable.RichEditorView_textSize, textSize);
        isBold = typedArray.getBoolean(R.styleable.RichEditorView_bold, isBold);
        typedArray.recycle();
    }

    private void initView(Context context) {
       View view =  LayoutInflater.from(context).inflate(R.layout.dialog_rich_editor, this);
        view.findViewById(R.id.imageView_eraser);
        richEditor.setFontSize(textSize);
        if (isBold){
            richEditor.setBold();
        }
    }


    public void setBold(boolean isBold){
        if (this.isBold = isBold){
            return;
        }
        this.isBold = isBold;
        richEditor.setBold();
    }
}
