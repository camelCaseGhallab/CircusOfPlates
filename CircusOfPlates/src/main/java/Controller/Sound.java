package Controller;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    public void heartSound() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Heart.wav"));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        System.out.println("ok");
    }
}
