package com.chimeric.framework.core;

import com.chimeric.framework.resource.Initializeable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class BackgroundRenderer<CustomActivity extends Activity>
extends SurfaceView
implements Runnable, Initializeable {
	private Thread renderThread;
	private volatile boolean running = false;

	protected SurfaceHolder holder;
	protected CustomActivity context;
	protected Bitmap frameBuffer;
	
	protected BackgroundRenderer(CustomActivity context, Bitmap frameBuffer) {
		super(context);
		this.context = context;
		this.frameBuffer = frameBuffer;
		holder = getHolder();
		this.initialize();
	}
	
	public abstract void customRender(Canvas canvas, float deltaTime);
	
	@Override
	public void run() {
		long frameStartTime = System.nanoTime();

		while(running) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			
			float deltaTime = (System.nanoTime() - frameStartTime) * 0.000000001f;
			frameStartTime = System.nanoTime();
			
			Canvas canvas = holder.lockCanvas();
			//canvas.drawBitmap(frameBuffer, null, dstRect, null);
			customRender(canvas, deltaTime);
			holder.unlockCanvasAndPost(canvas);
		}		
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				break;
			}
			catch (InterruptedException e) {
				
			}
		}
	}
}
