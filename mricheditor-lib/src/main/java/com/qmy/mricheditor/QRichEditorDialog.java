package com.qmy.mricheditor;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmy.mricheditor.dialog.ColorPicker;
import com.qmy.mricheditor.view.RichEditor;

/**
 * 作者：邱明月
 * 创建时间：2017/2/7 0007
 * 修改时间：
 * 作者邮箱：530594053@qq.com
 * 描述：
 */

public class QRichEditorDialog extends Dialog implements View.OnClickListener{
    private RichEditor richEditor;
    private ImageView redoImageView;
    private ImageView undoImageView;
    private ImageView textColorImageView;
    private CheckBox boldImageView;
    private CheckBox italicImageView;
    private CheckBox underlineImageView;
    private CheckBox headingImageView;
    private CheckBox bulletsImageView;
    private CheckBox numbersImageView;
    private ImageView eraserImageView;
    private TextView saveTextView;
    private ColorPicker colorPicker;

    public QRichEditorDialog(Context context) {
        super(context, R.style.dialog_fullscreen);
        initView();
        initListener();
    }


    protected void initView() {
        setContentView(R.layout.dialog_rich_editor);
        saveTextView = (TextView) findViewById(R.id.textView_save);
        richEditor = (RichEditor) findViewById(R.id.richEditor);
        redoImageView = (ImageView) findViewById(R.id.imageView_redo);
        undoImageView = (ImageView) findViewById(R.id.imageView_undo);
        textColorImageView = (ImageView) findViewById(R.id.imageView_text_color);
        eraserImageView = (ImageView) findViewById(R.id.imageView_eraser);
    }


    protected void initListener() {
        saveTextView.setOnClickListener(this);
        redoImageView .setOnClickListener(this);
        undoImageView.setOnClickListener(this);
        textColorImageView.setOnClickListener(this);
        boldImageView.setOnClickListener(this);
        italicImageView.setOnClickListener(this);
        underlineImageView.setOnClickListener(this);
        headingImageView.setOnClickListener(this);
        bulletsImageView.setOnClickListener(this);
        numbersImageView.setOnClickListener(this);
        eraserImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == saveTextView){

        }else if (view == redoImageView){
            richEditor.redo();

        }else if (view == undoImageView){
            richEditor.redo();
        }else if (view == textColorImageView){
            if (colorPicker == null){
                colorPicker = new ColorPicker(getOwnerActivity());
            }
            colorPicker.setOnColorPickListener(new ColorPicker.OnColorPickListener() {
                @Override
                public void onColorPicked(int pickedColor) {
                    richEditor.setTextColor(pickedColor);
                    colorPicker.dismiss();
                }
            });
            colorPicker.show();
        }else if (view == boldImageView){
            richEditor.setBold();
        }else if (view == italicImageView){
            richEditor.setItalic();
        }else if (view == underlineImageView){
            richEditor.setUnderline();
        }else if (view == headingImageView){
            richEditor.setHeading(4);
        }else if (view == bulletsImageView){
            if (numbersImageView.isChecked()){
                numbersImageView.setChecked(false);
            }
            richEditor.setBullets();
        }else if (view == numbersImageView){
            if (bulletsImageView.isChecked()){
                bulletsImageView.setChecked(false);
            }
            richEditor.setNumbers();
        }else if (view == eraserImageView){
            richEditor.removeFormat();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (colorPicker != null){
            colorPicker.destroy();
        }
    }
}
