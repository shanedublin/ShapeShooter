package shapeshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class MyTriangle extends Shape {
	
	Vector2 topLeft;
	Vector2 topRight;
	boolean right;
	
	
	public MyTriangle (float x, float y){
		this.position = new Vector2(x, y);
		this.topLeft = new Vector2(x-20, y+20);
		this.topRight = new Vector2(x+20, y+20);
		this.right = MathUtils.randomBoolean();
		
	}
	
	public void update(){
		
		if(this.position.y > height-100){
			
			float tempY = -100 * Gdx.graphics.getDeltaTime();
			
			this.position.y += tempY;
			 
			this.topLeft.y += tempY;
			
			this.topRight.y += tempY;
			
		}
		
		if(right){
			float tempX = 100 * Gdx.graphics.getDeltaTime();			
			this.position.x += tempX;			
			this.topLeft.x += tempX;			
			this.topRight.x += tempX;
			if(topRight.x > width){
				right = !right;
			}
		}
		
		else{
			float tempX = -100 * Gdx.graphics.getDeltaTime();			
			this.position.x += tempX;			
			this.topLeft.x += tempX;			
			this.topRight.x += tempX;
			if(topLeft.x < 0){
				right = !right;
			}
		}
		
		
		
		
		
	}
	
	public void move(float x, float y){
		float tempX = x * Gdx.graphics.getDeltaTime();
		float tempY = y * Gdx.graphics.getDeltaTime();
		this.position.x += tempX;
		this.position.y += tempY;
		
		this.topLeft.x += tempX; 
		this.topLeft.y += tempY;
		
		this.topRight.x += tempX;
		this.topRight.y += tempY;
		
	}

}
