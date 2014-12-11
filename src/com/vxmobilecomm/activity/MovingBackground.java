package com.vxmobilecomm.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vxappstore.R;
import com.vxmobilecomm.util.MySharedPref;

public class MovingBackground extends ActionBarActivity {
	private static final int RightToLeft = 1;
	private static final int LeftToRight = 2;
	private static final int DURATION = 10000;

	private ValueAnimator mCurrentAnimator;
	private final Matrix mMatrix = new Matrix();
	private ImageView mImageView;
	private float mScaleFactor;
	private int mDirection = RightToLeft;
	private RectF mDisplayRect = new RectF();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movingbackground);

		mImageView = (ImageView) findViewById(R.id.imageView);

		final LinearLayout layout_linearLayout = (LinearLayout) findViewById(R.id.layout_linearLayout);
		layout_linearLayout.post(new Runnable() {
			public void run() {
				int height = layout_linearLayout.getHeight();
				int width = layout_linearLayout.getWidth();

				Log.e("Height", height + "");

				MySharedPref.getInstance(MovingBackground.this).setString(
						"sendScreenHeight_SPLASHSCREEN", height + "");
				MySharedPref.getInstance(MovingBackground.this).setString(
						"sendScreenWidth_SPLASHSCREEN", width + "");
				
//				mScaleFactor = (float) height/2
//						/ (float) mImageView.getDrawable().getIntrinsicHeight();
				
				mScaleFactor = (float) mImageView.getHeight()
				/ (float) mImageView.getDrawable().getIntrinsicHeight();

				mMatrix.postScale(mScaleFactor, mScaleFactor);
				mImageView.setImageMatrix(mMatrix);
				animate();
			}
		});
	}

	private void animate() {
		updateDisplayRect();
		if (mDirection == RightToLeft) {
			
			float mDisplayRect_left = mDisplayRect.left ;
			float mDisplayRect_Right = mDisplayRect.left - (mDisplayRect.right - mImageView.getWidth()) ;
			
			Log.e("mDisplayRect_left", ""+mDisplayRect_left);
			Log.e("mDisplayRect_Right", ""+mDisplayRect_Right);
			
			
			animate(mDisplayRect.left, mDisplayRect.left
					- (mDisplayRect.right - mImageView.getWidth()));
		} else {
			animate( mDisplayRect.left
					- (mDisplayRect.right - mImageView.getWidth()),mDisplayRect.left);
		}
	}

	private void updateDisplayRect() {
		mDisplayRect.set(0, 0, mImageView.getDrawable().getIntrinsicWidth(),
				mImageView.getDrawable().getIntrinsicHeight());
		mMatrix.mapRect(mDisplayRect);
	}
	
	private void animate(float from, float to) {
		  MatrixImageView matrixImageView = new MatrixImageView(mImageView, mScaleFactor);
		  mCurrentAnimator = ObjectAnimator.ofFloat(matrixImageView, "matrixTranslateX", from, to);
		  mCurrentAnimator.setDuration(DURATION);
		  mCurrentAnimator.addListener(new AnimatorListenerAdapter() {
		      @Override
		      public void onAnimationEnd(Animator animation) {
		          if(mDirection == RightToLeft)
		              mDirection = LeftToRight;
		          else
		              mDirection = RightToLeft;

		          animate();
		      }
		  });
		  mCurrentAnimator.start();
		}

		class MatrixImageView {
		  private final ImageView mImageView;
		  private float mScaleFactor;
		  private final Matrix mMatrix = new Matrix();

		  public MatrixImageView(ImageView imageView, float scaleFactor) {
		      this.mImageView = imageView;
		      this.mScaleFactor = scaleFactor;
		  }

		  public void setMatrixTranslateX(float dx) {
			  
		      mMatrix.reset();
		      mMatrix.postScale(mScaleFactor, mScaleFactor);
		      mMatrix.postTranslate(dx, 0);
		      mImageView.setImageMatrix(mMatrix);
		  }
		}

}
