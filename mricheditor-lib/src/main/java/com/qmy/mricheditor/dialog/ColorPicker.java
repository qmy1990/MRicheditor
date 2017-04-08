package com.qmy.mricheditor.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.qmy.qricheditor.R;
import com.qmy.mricheditor.utils.ColorPickerIcon;
import com.qmy.mricheditor.utils.ImageUtils;
import com.qmy.mricheditor.view.ColorPanelView;


/**
 * 作者：邱明月
 * 创建时间：2017/2/7 0007
 * 修改时间：
 * 作者邮箱：530594053@qq.com
 * 描述：
 */

public class ColorPicker extends Dialog implements View.OnClickListener{

    private ColorPanelView multiColorView;
    private ColorPanelView blackColorView;

    private Button sureButton;

    private int currentColor = Color.WHITE;

    private OnColorPickListener onColorPickListener;

    public ColorPicker(Context context) {
        super(context);
        initDialog();
        initView();
        initListener();
        initColor();
        setUpView();
    }



    private void initDialog() {
        setCanceledOnTouchOutside(true);//触摸屏幕取消窗体
        setCancelable(true);//按返回键取消窗体
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.color_picker_window_animation);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.requestFeature(Window.FEATURE_NO_TITLE);

        WindowManager m = window.getWindowManager();
        WindowManager.LayoutParams p = window.getAttributes();
        Point size = new Point();
        m.getDefaultDisplay().getSize(size);
        p.width = size.x; //设置dialog的宽度为当前手机屏幕的宽度

        window.setAttributes(p);

    }

    private void initView(){
        setContentView(R.layout.dialog_color_picker);
        multiColorView = (ColorPanelView) findViewById(R.id.colorPanelView_multi_color);
        blackColorView = (ColorPanelView) findViewById(R.id.colorPanelView_black);

        sureButton = (Button) findViewById(R.id.button_sure);

    }

    private void initListener() {
        sureButton.setOnClickListener(this);
    }


    private void initColor() {
        Drawable cursorTopDrawable = ImageUtils.toDrawable(ColorPickerIcon.getCursorTop());
        multiColorView.setPointerDrawable(cursorTopDrawable);
        multiColorView.setOnColorChangedListener(new ColorPanelView.OnColorChangedListener() {
            @Override
            public void onColorChanged(ColorPanelView view, int color) {
                updateCurrentColor(color);
            }
        });

        Drawable cursorBottomDrawable = ImageUtils.toDrawable(ColorPickerIcon.getCursorBottom());
        blackColorView.setPointerDrawable(cursorBottomDrawable);
        blackColorView.setOnColorChangedListener(new ColorPanelView.OnColorChangedListener() {
            @Override
            public void onColorChanged(ColorPanelView view, int color) {
                updateCurrentColor(color);
            }
        });
        multiColorView.setColor(currentColor);//将触发onColorChanged，故必须先待其他控件初始化完成后才能调用
        multiColorView.setBrightnessGradientView(blackColorView);
    }

    private void setUpView(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            sureButton.setTextColor(getContext().getResources().getColor(android.R.color.widget_edittext_dark, getContext().getTheme()));
        }else{
            sureButton.setTextColor(getContext().getResources().getColor(android.R.color.widget_edittext_dark));
        }
    }

    private void updateCurrentColor(int color) {
        this.currentColor = color;
        this.sureButton.setTextColor(color);
    }


    @Override
    public void onClick(View v) {
        if (v == sureButton) {
            if (onColorPickListener != null) {
                onColorPickListener.onColorPicked(getCurrentColor());
            }
            dismiss();
        }
    }

    /**
     * 设置初始默认颜色
     *
     * @param initColor 颜色值，如：0xFFFF00FF
     */
    public void setInitColor(int initColor) {
        this.currentColor = initColor;
    }

    public void setOnColorPickListener(OnColorPickListener onColorPickListener) {
        this.onColorPickListener = onColorPickListener;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    /**
     * 颜色选择回调
     */
    public interface OnColorPickListener {
        void onColorPicked(@ColorInt int pickedColor);
    }

    public void destroy(){
        multiColorView.recycle();
        blackColorView.recycle();
    }

}
