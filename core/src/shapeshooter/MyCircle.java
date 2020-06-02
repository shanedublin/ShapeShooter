package shapeshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyCircle extends Shape {

	public Vector2 trajectory;
	public Circle c;
	public float radius;
	MyCircle(float radius, Vector2 traj, Vector2 pos){
		this.radius = radius;
		this.trajectory = traj;
		this.position = pos;
		this.previousPos = new Vector2(pos.x, pos.y);
		this.c = new Circle(pos, radius);
		
	}
	
	
	public void move(float speed){ 
		// stop using pos 
		previousPos.x = position.x;
		previousPos.y = position.y;
		
		this.position.x += trajectory.x * Gdx.graphics.getDeltaTime() * speed;
		this.position.y += trajectory.y * Gdx.graphics.getDeltaTime() * speed;
		
		this.c.x += trajectory.x * Gdx.graphics.getDeltaTime() * speed;
		this.c.y += trajectory.y * Gdx.graphics.getDeltaTime() * speed;
		
	}
}
