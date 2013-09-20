package com.pinecone.technology.mcommerce.learning.android.chapter11.trackhistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchHistoricView extends View {
	private Paint paint = new Paint();
	private boolean handleHistoricEvent = true;
	private Path path = new Path();

	public MultiTouchHistoricView(Context context) {
		super(context);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(6f);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawPath(path, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		int action = event.getActionMasked();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(event.getX(), event.getY());
		case MotionEvent.ACTION_MOVE:
			printSamples(event);
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
		invalidate();
		return true;
	}

	void printSamples(MotionEvent ev) {
		final int historySize = ev.getHistorySize();
		final int pointerCount = ev.getPointerCount();
		for (int h = 0; h < historySize; h++) {
			System.out.printf("At time %d:", ev.getHistoricalEventTime(h));
			for (int p = 0; p < pointerCount; p++) {
				System.out.printf("  pointer %d: (%f,%f)", ev.getPointerId(p),
						ev.getHistoricalX(p, h), ev.getHistoricalY(p, h));
				paint.setColor(Color.BLUE);
				path.lineTo(ev.getHistoricalX(p, h), ev.getHistoricalY(p, h));
			}
		}
		System.out.printf("At time %d:", ev.getEventTime());
		for (int p = 0; p < pointerCount; p++) {
			System.out.printf("  pointer %d: (%f,%f)", ev.getPointerId(p),
					ev.getX(p), ev.getY(p));
			paint.setColor(Color.RED);
			path.lineTo(ev.getX(p), ev.getY(p));
		}
	}
}