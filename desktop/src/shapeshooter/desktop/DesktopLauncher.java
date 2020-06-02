package shapeshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import shapeshooter.ShapeShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1600;
		config.width = 1920;
		config.width = 1280;
		config.height = 900;
		config.height = 1080;
		config.height = 720;
		config.vSyncEnabled = true;
		new LwjglApplication(new ShapeShooter(), config);
	}
}
