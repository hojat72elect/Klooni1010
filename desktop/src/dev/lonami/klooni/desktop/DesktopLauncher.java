package dev.lonami.klooni.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import dev.lonami.klooni.Klooni;

class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Klooni 1010!";
        config.width = Klooni.GAME_WIDTH;
        config.height = Klooni.GAME_HEIGHT;
        config.addIcon("ic_launcher/icon128.png", Files.FileType.Internal);
        config.addIcon("ic_launcher/icon32.png", Files.FileType.Internal);
        config.addIcon("ic_launcher/icon16.png", Files.FileType.Internal);
        new LwjglApplication(new Klooni(null), config);
    }
}
