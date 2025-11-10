package com.tetris.controller;

import com.tetris.db.Database;
import com.tetris.model.Board;
import com.tetris.model.Theme;
import com.tetris.util.SoundManager;
import com.tetris.view.GameFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 * Controlador principal do jogo.
 * Faz a ponte entre o modelo (Board) e a interface (GameFrame).
 */
public class GameController extends KeyAdapter implements ActionListener {

    private static final int INITIAL_DELAY = 400;
    private final GameFrame gameFrame;
    private final Board board;
    private final Timer timer;
    private int currentThemeIndex = 0;
    private String playerName = "";

    public GameController(GameFrame gameFrame, Board board) {
        this.gameFrame = gameFrame;
        this.board = board;
        this.timer = new Timer(getDelayForLevel(), this);
        this.gameFrame.getGamePanel().addKeyListener(this);
        this.gameFrame.getGamePanel().setFocusable(true);
    }

    public void startGameFromUI() {
        if ((!board.isStarted() || board.isGameOver())) {
            if (playerName == null || playerName.trim().isEmpty()) {
                if (gameFrame.getOverlayPanel() != null) {
                    gameFrame.getOverlayPanel().requestFocusForName();
                }
                return;
            }

            board.start();
            if (!timer.isRunning()) timer.start();

            // 🎵 Inicia a música de fundo apenas quando o jogo começa
            SoundManager.stopMusic();
            SoundManager.playMusic("tetris_theme.wav");

            gameFrame.getGamePanel().requestFocusInWindow();
            updateView();
        }
    }

    public void setPlayerName(String name) {
        this.playerName = name == null ? "" : name.trim();
    }

    public void start() {
        timer.start();
        gameFrame.getGamePanel().requestFocusInWindow();
        updateView();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (board.isStarted() && !board.isPaused() && !board.isGameOver()) {
            board.movePieceDown();
        }

        if (board.isGameOver()) {
            timer.stop();

            // 🎵 Para a música ao perder
            SoundManager.stopMusic();

            Database.createTable();
            String playerToSave = (playerName == null || playerName.trim().isEmpty()) ? "Jogador1" : playerName;
            Database.saveGame(playerToSave, board.getScore(), board.getLevel(), board.getLinesCleared());
            updateView();
            return;
        }

        timer.setDelay(getDelayForLevel());
        updateView();
    }

    private void updateView() {
        gameFrame.getGamePanel().getBoardPanel().updateBoard(board);
        gameFrame.getGamePanel().getInfoPanel().updateInfo(board);
        gameFrame.getOverlayPanel().updateBoard(board);

        Theme currentTheme = Theme.AVAILABLE_THEMES[currentThemeIndex];
        gameFrame.getGamePanel().updateTheme(currentTheme);
        gameFrame.getOverlayPanel().updateTheme(currentTheme);
        gameFrame.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        // Trocar tema
        if (keycode == KeyEvent.VK_T) {
            currentThemeIndex = (currentThemeIndex + 1) % Theme.AVAILABLE_THEMES.length;
            updateView();
            return;
        }

        // Alternar Ghost Piece
        if (keycode == KeyEvent.VK_G) {
            board.toggleGhostPiece();
            updateView();
            return;
        }

        // 🔈 Alternar som (liga/desliga)
        if (keycode == KeyEvent.VK_S) {
            SoundManager.toggleSound();

            // Se o som foi reativado e o jogo está ativo, toca novamente a música
            if (SoundManager.isSoundEnabled() && board.isStarted() && !board.isPaused() && !board.isGameOver()) {
             SoundManager.playMusic("tetris_theme.wav");
            }

            return;
        }

        // Iniciar jogo
        if ((!board.isStarted() || board.isGameOver()) && keycode == KeyEvent.VK_ENTER) {
            if (playerName == null || playerName.trim().isEmpty()) {
                if (gameFrame.getOverlayPanel() != null) {
                    gameFrame.getOverlayPanel().requestFocusForName();
                }
                return;
            }

            board.start();
            if (!timer.isRunning()) timer.start();

            // Reinicia a música sem duplicar
            SoundManager.stopMusic();
            SoundManager.playMusic("tetris_theme.wav");

            updateView();
            return;
        }

        if (!board.isStarted() || board.isGameOver()) return;

        // Pausar / Retomar
        if (keycode == KeyEvent.VK_P) {
            board.togglePause();

            if (board.isPaused()) {
                SoundManager.stopMusic();
            } else {
                SoundManager.playMusic("tetris_theme.wav");
            }

            updateView();
            return;
        }

        if (board.isPaused()) return;

        switch (keycode) {
            case KeyEvent.VK_LEFT:
                board.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                board.moveRight();
                break;
            case KeyEvent.VK_DOWN:
                board.movePieceDown();
                break;
            case KeyEvent.VK_UP:
                board.rotateRight();
                break;
            case KeyEvent.VK_Z:
                board.rotateLeft();
                break;
            case KeyEvent.VK_SPACE:
                board.dropDown();
                break;
        }

        updateView();
    }

    private int getDelayForLevel() {
        return Math.max(100, INITIAL_DELAY - (board.getLevel() - 1) * 30);
    }
}
