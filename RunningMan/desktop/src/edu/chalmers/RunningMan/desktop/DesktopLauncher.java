package edu.chalmers.RunningMan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chalmers.RunningMan.RunningMan;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = RunningMan.TITLE;
        config.width = RunningMan.V_WIDTH * RunningMan.SCALE;
        config.height = RunningMan.V_HEIGHT * RunningMan.SCALE;

		new LwjglApplication(new RunningMan(), config);
	}
}
