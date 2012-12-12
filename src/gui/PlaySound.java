package gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.print.DocFlavor.URL;


public class PlaySound {   
    public void play(String soundFile) throws IOException {
    	File a= new File(soundFile); 
    	java.net.URL url=a.toURL();
    	AudioClip ac=Applet.newAudioClip(url); 
    	ac.play(); 
    }
}
