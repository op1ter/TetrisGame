package com.tetris.view;

import com.tetris.util.SoundManager;
import java.awt.Dimension;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

/**
 * A janela principal do jogo (o JFrame).
 * Utiliza um JLayeredPane para sobrepor o painel do jogo e o painel de overlays.
 */
public class GameFrame extends JFrame {

    private GamePanel gamePanel;
    private OverlayPanel overlayPanel;
    private JLayeredPane layeredPane;
    private com.tetris.controller.GameController controller;

    private void initComponents() {
        // Cria o painel em camadas
        layeredPane = new JLayeredPane();
        
        // Cria os nossos painéis
        gamePanel = new GamePanel();
        overlayPanel = new OverlayPanel();

        // Define o tamanho dos painéis para que ocupem toda a janela
        Dimension size = gamePanel.getPreferredSize();
        layeredPane.setPreferredSize(size);
        gamePanel.setBounds(0, 0, size.width, size.height);
        overlayPanel.setBounds(0, 0, size.width, size.height);

        // Adiciona os painéis ao JLayeredPane
        layeredPane.add(gamePanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(overlayPanel, JLayeredPane.PALETTE_LAYER);

        // Adiciona o JLayeredPane à janela
        add(layeredPane);

        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // --- 🧩 Adiciona o ícone da janela ---
        URL iconURL = getClass().getResource("/com/tetris/view/resources/icon.jpeg");
        if (iconURL != null) {
            setIconImage(new ImageIcon(iconURL).getImage());
        } else {
            System.err.println("⚠️ Ícone não encontrado em /com/tetris/view/resources/icon.jpeg");
        }
        // --- fim da configuração do ícone ---

        pack();
        setLocationRelativeTo(null);
    }

    // --- Métodos de acesso para o Controller ---
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public OverlayPanel getOverlayPanel() {
        return overlayPanel;
    }

    public void setController(com.tetris.controller.GameController controller) {
        this.controller = controller;
        if (this.overlayPanel != null) {
            this.overlayPanel.setController(controller);
        }
    }

    public com.tetris.controller.GameController getController() {
        return controller;
    }

    public GameFrame() {
        initComponents();
        SoundManager.playMusic("tetris_theme.wav");  // coloque o arquivo em /resources/music/
    }

}
