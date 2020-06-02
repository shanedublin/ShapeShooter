package methods;

import com.badlogic.gdx.math.Vector2;

import shapeshooter.MyCircle;

public class Intersects {
	
	
	/**
	 * give two circles and determines if they collide
	 * does not account for the movement of the second circle. 
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean myCirclemyCircle(MyCircle c1, MyCircle c2){
		
		float ax;
		float ay;		
		float bx;
		float by;		
		float cx;
		float cy;		
		
		ax = c1.position.x;
		ay = c1.position.y;		
		bx = c1.previousPos.x;
		by = c1.previousPos.y;		
		cx = c2.position.x;
		cy = c2.position.y;		
		
		Vector2 ab;
		Vector2 ac;
		Vector2 projection = new Vector2(0, 0);
		Vector2 ad;
		
		ab = new Vector2(ax - bx, ay - by);
		ac = new Vector2(ax - cx, ay - cy);	
		
		float acdotac;
		float acdotab;
		
		acdotab = ac.dot(ab);
		acdotac = ac.dot(ac);
				
		projection.x = ac.x * (acdotab/acdotac);
		projection.y = ac.y * (acdotab/acdotac);
				
		ad = ac.sub(projection);
				
		if(ad.dot(ad) <= (c1.radius +c2.radius) * (c1.radius + c2.radius) ){

			return true;
			
		}
		else				
		return false;
		
		
		
	
	}

}
