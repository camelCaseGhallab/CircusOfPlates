package com.mycompany.circusofplates;

import View.CircusWorld;
import Controller.GameController;
import Controller.Sound;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class CircusOfPlates {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        Sound sound = new Sound();
        sound.heartSound();
    }
}
