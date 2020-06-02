package shapeshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class WaveManager {
	int currentWave = 0;
	int lastShape = 1;
	int width = 1600;
	int height = 900;
	long LastSpawn;
	long Time;
	Array<Wave> waves;
	public WaveManager(){
		LastSpawn = TimeUtils.nanoTime();
		Time = 500000000;
		
		waves = new Array<Wave>();
		waves.add(new Wave(2, 0, 0));
		generateWave(10);
		
	}
	
	public int update(){
		if(TimeUtils.nanoTime() - LastSpawn > Time){
			Wave wave = waves.get(currentWave);
			LastSpawn = TimeUtils.nanoTime();
			switch(lastShape){
			// TODO inc wave
			case 0:
				
				return 0;
			
				// circle
			case 1:		
				
				wave.numCircles --;
				if (wave.numCircles <=0){
					lastShape = 2;
				}
				return 1;
				
			case 2:
				wave.numTriangles --;
				if (wave.numTriangles <=0){
					lastShape = 3;
				}
				return 2;
				
			case 3:
				wave.numSquares --;
				if (wave.numSquares <=0){
					lastShape = 0;
				}
				return 3;
				
			// show upgrade menu / pause before next wave.
				case -1:
				
				return 0;
			default:
				return 0;
					
					
			}
			
			
		}
		
		else{
			return 0;
		}
	
		
		
	}
	private void generateWave(int i){
		for(int j =0; j < i; j ++){
			
			waves.add(new Wave(j, j, j));
		}
		
	}
	
	public MyCircle getCircle(){
		
		MyCircle c;
		c = new MyCircle(10,new Vector2(0, -1), new Vector2(MathUtils.random(10,width-10),height+20));
		return c;
	}
	
	public MyTriangle getTriangle(){
		MyTriangle t;
		t = new MyTriangle(MathUtils.random(10,width-10),height+20);
		return t;
	}

}
