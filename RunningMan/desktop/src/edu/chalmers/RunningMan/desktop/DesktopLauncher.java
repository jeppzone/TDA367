package edu.chalmers.RunningMan.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import edu.chalmers.RunningMan.RunningMan;
import static edu.chalmers.RunningMan.utils.Constants.V_HEIGHT;
import static edu.chalmers.RunningMan.utils.Constants.V_WIDTH;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = RunningMan.TITLE;
        config.width = V_WIDTH * RunningMan.SCALE;
        config.height = V_HEIGHT * RunningMan.SCALE;

		new LwjglApplication(new RunningMan(), config);
	}
}
