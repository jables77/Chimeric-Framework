package com.chimeric.blueballs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.chimeric.framework.core.BackgroundRenderer;
import com.chimeric.framework.diagnostic.FramesPerSecond;
import com.chimeric.framework.input.AccelerometerHandler;
import com.chimeric.framework.input.TouchEvent;
import com.chimeric.framework.math.Axis;
import com.chimeric.framework.math.Interval;
import com.chimeric.framework.math.Vector3d;
import com.chimeric.framework.physics.CollisionPredictedEvent;
import com.chimeric.framework.physics.CollisionPredictor;
import com.chimeric.framework.physics.CollisionPredicted;
import com.chimeric.framework.resource.ResourceFactory;
import com.chimeric.framework.resource.ResourceBroker;
import com.chimeric.framework.resource.Worker;

public class GameInProgressActivity
extends Activity {
	GameApplication game;	
	BackgroundRenderer<GameInProgressActivity> view;
	
	WakeLock wakeLock;
	
	//Specific implementation to this game
	ResourceBroker<BlueBall> blueBallBroker;
	float terminalVelocity = 500;
	BallMover ballMover;
	//OnBlueBallCollision ballCollision;
	
	//Screen setup
	Interval[] screenBoundaries;
	
	//Debug setup
	private FramesPerSecond fps;
	
	boolean debugFPS;
	boolean debugTouches;
	boolean debugVelocityMagnitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Create ui rendered to
		//Create input handler

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		game = (GameApplication) getApplication();
		
		//Create a wake lock to use for application
		PowerManager powerManager = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
		this.wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Chimeric Wake Lock");
		
		final ResourceFactory<BlueBall> blueBallFactory = new ResourceFactory<BlueBall>() {			
			@Override
			public BlueBall createResource() {
				return new BlueBall(25);
			}
		};
		
		this.blueBallBroker = new ResourceBroker<BlueBall>(blueBallFactory, 50);
		this.ballMover = new BallMover();
		//this.ballCollision = new OnBlueBallCollision();
				
		this.debugFPS = true;
		this.debugTouches = true;
		
		Display display = getWindowManager().getDefaultDisplay();
		
		this.screenBoundaries = new Interval[2];
		this.screenBoundaries[0] = new Interval (0, display.getWidth());
		this.screenBoundaries[1] = new Interval (0, display.getHeight());
		
		this.fps = new FramesPerSecond();

		Bitmap frameBuffer = null;//Bitmap.cre
		
		view = new BackgroundRenderer<GameInProgressActivity>(this, frameBuffer) {
			Typeface font;	
			
			Paint paint;
			Random rand;
			
			@Override
			public void customRender(Canvas canvas, float deltaTime) {
				context.update(deltaTime);
				
				//Erase
				canvas.drawRGB(0, 0, 0);
				
				if (debugFPS) {
					paint.setColor(Color.WHITE);
					canvas.drawText(
						String.format("FPS: %f", fps.getCurrentFPS()),
						0,
						30,
						paint
					);
				}
				
				TouchEvent touchEvent;
				BlueBall currentBall;
				Vector3d currentBallPosition;
				
				int objectsToProcess = game.getTouchHandler().getTotalTouchesTrackable();
				for (int i = 0; i < objectsToProcess; i++) {
					touchEvent = game.getTouchHandler().getTouchEvent(i);					
					if (touchEvent != null) {
						if (debugTouches) {
							paint.setColor(Color.WHITE);							
							canvas.drawText(
								String.format(
									"Touch %s (%f, %f)",
									touchEvent.getTouchState().toString(),
									touchEvent.getX(),
									touchEvent.getY()
								), 
								0,
								60 + i * 20,
								paint
							);
						}
					}
					
					switch (touchEvent.getTouchState()) {
					case PRESSED:
						touchEvent.consume();
						currentBall = blueBallBroker.creditResource();
						currentBallPosition = currentBall.getPosition();
						currentBallPosition.setX(canvas.getWidth() - touchEvent.getX());
						currentBallPosition.setY(touchEvent.getY());
						currentBall.setOwnedByPointerId(i);
						break;
					case CONSUMED:
						//currentBall = blueBall
						break;
					case DRAGGED:
						break;
					case UNPRESSED:
					}
				}
				
				objectsToProcess = blueBallBroker.getResourceCount();
				for (int i = 0; i < objectsToProcess; i++) {					
					currentBall = blueBallBroker.getResource(i);
					currentBallPosition = currentBall.getPosition();
					float x = canvas.getWidth() - currentBallPosition.getX();
					float y = + currentBallPosition.getY();				
					
					paint.setColor(currentBall.getColor());
					canvas.drawCircle(x, y, currentBall.getRadius(), paint);
				}				
			}

			@Override
			public void initialize() {
				font = Typeface.create("Courier New", Typeface.NORMAL);
				
				paint = new Paint();
				paint.setColor(Color.BLUE);
				paint.setTypeface(font);
				paint.setTextSize(15.0f);
				paint.setStrokeWidth(4);
				
				rand = new Random();
			}
		};
		setContentView(view);
		
		super.onCreate(savedInstanceState);
	}
	
	public void update(float deltaTime) {
		this.fps.update(deltaTime);
		
		int totalBalls = blueBallBroker.getResourceCount();
		
		CollisionPredictor cp = game.getCollisionPredictor();		
		
		//game.getCollisionManager().checkCircleCircleCollisions(blueBallBroker, deltaTime, this.ballCollision);
		
		BlueBall ball1;
		BlueBall ball2;
		for (int i = 0; i < totalBalls; i++) {
			ball1 = blueBallBroker.getResource(i);
			float b1V = FloatMath.sqrt(ball1.getVelocity().getMagnitudeSquared());
			
			for (int j = i + 1; j < totalBalls; j++) {
				ball2 = blueBallBroker.getResource(j);
				float b2V = FloatMath.sqrt(ball2.getVelocity().getMagnitudeSquared());

				float timeOfCollision = cp.timeOfCollision(ball1, ball2);
				if (timeOfCollision <= deltaTime) {
					ball1.setColor(Color.RED);
					ball2.setColor(Color.RED);
					//ball1.getVelocity().scale(timeOfCollision / b1V);
					//ball2.getVelocity().scale(timeOfCollision / b2V);
				}
			}
		}
		
		this.ballMover.setState(deltaTime);
		this.blueBallBroker.synchronizedIteration(this.ballMover);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		wakeLock.acquire();
		view.resume();	
		
		game.getAccelerometerHandler().attach();
		view.setOnTouchListener(this.game.getTouchHandler());
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		wakeLock.release();
		view.pause();		
		
		game.getAccelerometerHandler().detach();
		view.setOnTouchListener(null);		
		
		if (this.isFinishing()) {
			//Log out, save state
			//Dispose of resources
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		DebugMenuOptions[] menuOptions = DebugMenuOptions.values();
		int menuOptionsCount = menuOptions.length;
		
		for (int i = 0; i < menuOptionsCount; i++) {
			MenuItem item = menu.add(
				menuOptions[i].getGroupId(),
				menuOptions[i].getItemId(),
				Menu.NONE,
				menuOptions[i].getTitle()
			);
			item.setChecked(true);
		}
		
		menu.setGroupCheckable(DebugMenuOptions.DEBUG_OPTIONS, true, false);
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.isCheckable()) {
			item.setChecked(!item.isChecked());
		}
		
		if (item.getItemId() == DebugMenuOptions.DISPLAY_FPS.getItemId()) {
			this.debugFPS = item.isChecked();
		}
		
		if (item.getItemId() == DebugMenuOptions.DISPLAY_TOUCHES.getItemId()) {
			this.debugTouches = item.isChecked();
		}
		
		if (item.getItemId() == DebugMenuOptions.DISPLAY_RESOURCES.getItemId()) {
			this.debugVelocityMagnitude = item.isChecked();
		}
		
		if (item.getItemId() == DebugMenuOptions.RESET_BALLS.getItemId()) {
			this.blueBallBroker.bankrupt();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	class BallMover implements Worker<BlueBall, Float> {
		private float state;
		
		@Override
		public void workOn(ResourceBroker<BlueBall> broker, BlueBall itemOfWork, int index) {
			BlueBall blueBall = itemOfWork;
			Vector3d position = blueBall.getPosition();
			game.getAccelerometerHandler().copyAccelerationTo(blueBall.getAcceleration());
			blueBall.getAcceleration().setZ(0);
			blueBall.update(state);
			
			float velocityMagnitude = FloatMath.sqrt(blueBall.getVelocity().getMagnitudeSquared());
			if (velocityMagnitude > terminalVelocity) {
				blueBall.getVelocity().scale(terminalVelocity / velocityMagnitude);
			}

			position.setX(screenBoundaries[Axis.X.getAxisIndex()].clampToRange(position.getX()));
			position.setY(screenBoundaries[Axis.Y.getAxisIndex()].clampToRange(position.getY()));
		}

		@Override
		public void setState(Float value) {
			this.state = value;
		}
	}
}