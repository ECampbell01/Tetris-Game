/*
 * Tetris Game
 * Class that plays the music
 * 12/10/2024
 * Author: Ethan Campbell
 */

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class MusicPlayer {

    private Clip clip;

    public void playMusic(String filePath) {
        try {
            // Load the audio file
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            
            // Get a clip and open the audio stream
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Start playing the audio in a loop
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}