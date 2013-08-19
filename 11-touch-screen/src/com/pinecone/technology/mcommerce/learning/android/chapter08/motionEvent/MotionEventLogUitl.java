package com.pinecone.technology.mcommerce.learning.android.chapter08.motionEvent;

import android.view.MotionEvent;
import android.view.View;

public class MotionEventLogUitl {
	public static String describeEvent(View view, MotionEvent event) {
		StringBuilder result = new StringBuilder(300);
		result.append("Action: ").append(event.getAction()).append("\n");
		result.append("Location: ").append(event.getX()).append(" x ")
				.append(event.getY()).append("\n");
		if (event.getX() < 0 || event.getX() > view.getWidth()
				|| event.getY() < 0 || event.getY() > view.getHeight()) {
			result.append(">>> Touch has left the view <<<\n");
		}
		result.append("Edge flags: ").append(event.getEdgeFlags()).append("\n");
		result.append("Pressure: ").append(event.getPressure(0)).append("   ");
		result.append("Size: ").append(event.getSize(0)).append("\n");
		result.append("Down time: ").append(event.getDownTime()).append("ms\n");
		result.append("Event time: ").append(event.getEventTime()).append("ms");
		result.append("  Elapsed: ").append(
				event.getEventTime() - event.getDownTime());
		result.append(" ms\n");
		return result.toString();
	}
}
