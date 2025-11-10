package com.tetris.util;

import java.net.URL;
import javax.sound.sampled.*;

public class SoundManager {

    private static Clip backgroundMusic;

    private static boolean soundEnabled = true; // 🔊 sons ativados por padrão

    public static void toggleSound() {
        soundEnabled = !soundEnabled;
        if (!soundEnabled) {
            stopMusic();
            System.out.println("🔇 Sons desativados");
        } else {
            System.out.println("🔊 Sons ativados");
        }
    }

    public static boolean isSoundEnabled() {
        return soundEnabled;
    }


    // 🔊 Toca efeitos curtos (pontuação, linha, etc.)
    public static void playSound(String soundFile) {
        try {
            URL soundURL = SoundManager.class.getClassLoader().getResource("com/tetris/view/resources/sounds/" + soundFile);
            if (soundURL == null) {
                System.out.println("Som não encontrado: sounds/" + soundFile);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Erro ao tocar som: " + e.getMessage());
        }
    }

    // 🎵 Toca música de fundo em loop
    public static void playMusic(String musicFile) {
        stopMusic();
        try {
            URL musicURL = SoundManager.class.getClassLoader().getResource("com/tetris/view/resources/music/" + musicFile);
            if (musicURL == null) {
                System.out.println("Música não encontrada: music/" + musicFile);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicURL);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Erro ao tocar música: " + e.getMessage());
        }
    }

    // ⏹️ Parar música
    public static void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }
}
