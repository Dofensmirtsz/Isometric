package io.fabianterhorst.isometric;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fabianterhorst on 31.03.17.
 */

public class IsometricView extends View {

    public interface OnItemClickListener {
        void onClick(@NonNull Isometric.Item item);
    }

    private final Isometric isometric = new Isometric();

    private OnItemClickListener listener;

    private boolean sort = true;

    public IsometricView(Context context) {
        super(context);
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public void setClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void clear() {
        isometric.clear();
    }

    public void add(Path path, Color color) {
        isometric.add(path, color);
    }

    public void add(Shape shape, Color color) {
        isometric.add(shape, color);
    }

    public IsometricView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IsometricView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public IsometricView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        isometric.measure(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec), sort);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,64);
        isometric.draw(canvas);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (listener != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                Isometric.Item item = isometric.findItemForPosition(new Point(event.getX(), event.getY()));
                if (item != null) {
                    listener.onClick(item);
                }
                performClick();
            }
        }
        return super.onTouchEvent(event);
    }
}
