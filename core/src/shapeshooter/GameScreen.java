package shapeshooter;

import java.util.Iterator;

import methods.Intersects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen,InputProcessor{
	public ShapeShooter game;
	public int width = 1600;
	public int height = 900;
	public int lastX;
	public int lastY;
	OrthographicCamera cam;
	Viewport viewport;
	public Array<MyCircle> circles;
	public Array<MyCircle> badCircles;
	public Array<MySquare> squares;
	public Array<MyTriangle> triangles;
	public WaveManager waveManager;
	public Player p;
	
	public GameScreen(ShapeShooter game){
		this.game = game;
		cam = new OrthographicCamera();
		cam.setToOrtho(false,width,height);
		viewport = new FillViewport(width, height, cam);
		
		circles = new Array<MyCircle>();
		badCircles = new Array<MyCircle>();
		squares = new Array<MySquare>();
		triangles = new Array<MyTriangle>();
		
		waveManager = new WaveManager();
		Gdx.input.setInputProcessor(this);
		lastX = 0;
		lastY = 0;
		//MyCircle c = new MyCircle(50, new Vector2(0,0), new Vector2(width/2, height/2));
		//badCircles.add(c);
		
		
		
	}
	
	
	
	public void doLogic(){
		int shape = waveManager.update();
		
		
		switch(shape){
		// not time yet to spawn something
		case 0:
		break;
		
		// spawn a circle
		case 1: badCircles.add(waveManager.getCircle()); 
		break;
		
		case 2: triangles.add(waveManager.getTriangle());
		break;
	
		}
		
		
		
		Iterator<MyTriangle> triangleItor = triangles.iterator();
		while(triangleItor.hasNext()){
			MyTriangle t = triangleItor.next();
			t.update();
		}
		
		Iterator<MyCircle> badCircleIterator =  badCircles.iterator();
		while(badCircleIterator.hasNext()){
			MyCircle c = badCircleIterator.next();
			c.move(100);
		}
		
		Iterator<MyCircle> circleIterator = circles.iterator();
		while(circleIterator.hasNext()){
			MyCircle c = circleIterator.next();
			c.move(2000);
			Iterator<MyCircle> itor = badCircles.iterator();
			
			while(itor.hasNext()){
				MyCircle c2 = itor.next();
			
				// change to raycasting
				if(Intersects.myCirclemyCircle(c, c2)){
					
					// change to damage later.
					itor.remove();
					circleIterator.remove();
					break;
				}
			}
			
			// if the circle goes out of bounds remove it.
			if(c.position.y > height +20 || c.position.x > width + 20 || c.position.x < -20){
				circleIterator.remove();
			}
			
			//TODO Detect Collision.
			
		}
		
	}
	
	
	
	
	
	

	@Override
	public void render(float delta) {
		//System.out.println("render");
		doLogic();
		cam.update();
		
		Gdx.gl.glClearColor(0, .5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.sr.setProjectionMatrix(cam.combined);
		game.sr.begin(ShapeType.Filled);
		//game.sr.circle(width/2, height/2, 100);
		
		Iterator<MyTriangle> triangleItor = triangles.iterator();
		while(triangleItor.hasNext()){
			MyTriangle t = triangleItor.next();
			game.sr.triangle(t.position.x, t.position.y, t.topLeft.x, 
					t.topLeft.y, t.topRight.x,t.topRight.y);
		}
		
		
		
		Iterator<MyCircle> circleItor = circles.iterator();
		while(circleItor.hasNext()){
			MyCircle c = circleItor.next();
			game.sr.circle(c.position.x, c.position.y, c.radius);
		}
		Iterator<MyCircle> badCircleItor = badCircles.iterator();
		while(badCircleItor.hasNext()){
			MyCircle c = badCircleItor.next();
			game.sr.circle(c.position.x, c.position.y, c.radius);
		}
		
		
		
		
		game.sr.end();
		game.batch.setProjectionMatrix(cam.combined);
		game.batch.begin();
		game.font.draw(game.batch, "x: " + lastX, 0, 100);
		game.font.draw(game.batch, "y: " + lastY, 0, 80);
		
		game.batch.end();
		
	}

	@Override
	public void resize(int width, int height) {

		viewport.update(width, height);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastX = screenX;
		lastY = Math.abs(screenY-height);
		MyCircle c;
		Vector2 tempVec;
		Vector3 touchPos= new Vector3(0,0,0);
		cam.unproject(touchPos.set(screenX, screenY,0));
		lastX = (int) touchPos.x;
		lastY = (int) touchPos.y;
		//touchPos = new Vector2(screenX, screenY);
		//cam.unproject(new Vector3(touchPos, 0));
		
		float x;
		float y;
		float hypo;
		x = touchPos.x - width/2;
		y = touchPos.y  - 0;
		hypo = (float) Math.sqrt( x * x + y *y);
		x = x / hypo;
		y = y / hypo;
		tempVec = new Vector2(x, y);
		
		c = new MyCircle(5, tempVec, new Vector2(width/2,0 ));
		circles.add(c);
		System.out.println("Circle added");
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
