package com.vxmobilecomm.util;

import android.content.Context;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.ListView;

public class NewListView extends ListView {

	private VelocityTracker mVelocityTracker;

	public NewListView(Context context) {
		super(context);
	}

	public NewListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NewListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public boolean onTouchEvent(MotionEvent event) {
		int index = event.getActionIndex();
		int action = event.getActionMasked();
		int pointerId = event.getPointerId(index);

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (mVelocityTracker == null) {
				mVelocityTracker = VelocityTracker.obtain();
			} else {
				mVelocityTracker.clear();
			}
			mVelocityTracker.addMovement(event);
			break;
		case MotionEvent.ACTION_MOVE:
			mVelocityTracker.addMovement(event);
			mVelocityTracker.computeCurrentVelocity(1000);
//			Log.d("",
//					"X velocity: "
//							+ VelocityTrackerCompat.getXVelocity(
//									mVelocityTracker, pointerId));
			Log.d("",
					"Y velocity: "
							+ VelocityTrackerCompat.getYVelocity(
									mVelocityTracker, pointerId));
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			mVelocityTracker.recycle();
			break;
		}
		return false;
	}
}
