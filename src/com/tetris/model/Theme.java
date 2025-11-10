package com.tetris.model;

import java.awt.Color;

/**
 * Representa um tema visual para o jogo, contendo todas as cores necessárias.
 * Usamos um 'record' para uma definição concisa e imutável de um tema.
 */
public record Theme(
    String name,
    Color uiBackground,
    Color boardBackground,
    Color grid,
    Color[] pieceColors // Array com 8 cores: a primeira é para 'NoShape', as outras 7 para as peças.
) {
    // --- Temas Pré-definidos ---

    /**
     * Tema Dark Minimalista — tons frios, contraste suave e visual moderno.
     */
    public static final Theme DARK_MINIMAL = new Theme(
        "Dark Minimal",
        new Color(18, 18, 24),      // fundo UI
        new Color(25, 25, 35),      // fundo tabuleiro
        new Color(40, 40, 55),      // grid
        new Color[] {
            new Color(15, 15, 25),     // NoShape
            new Color(0x4FC3F7),       // Z - azul-claro
            new Color(0x81C784),       // S - verde-claro
            new Color(0x9575CD),       // Line - lilás
            new Color(0xFFD54F),       // T - amarelo suave
            new Color(0xE57373),       // Square - vermelho claro
            new Color(0x64B5F6),       // L - azul médio
            new Color(0xFFB74D)        // MirroredL - laranja queimado
        }
    );

    /**
     * Tema Retro Neon — inspirado em fliperamas dos anos 80.
     */
    public static final Theme RETRO_NEON = new Theme(
        "Retro Neon",
        new Color(5, 5, 15),        // fundo UI preto-azulado
        new Color(10, 10, 25),      // fundo tabuleiro escuro
        new Color(20, 20, 40),      // grid azul-acinzentado
        new Color[] {
            new Color(0, 0, 0),         // NoShape - preto puro
            new Color(0xFF0066),        // Z - rosa neon
            new Color(0x00FF00),        // S - verde neon
            new Color(0x00FFFF),        // Line - ciano neon
            new Color(0xFF00FF),        // T - magenta neon
            new Color(0xFFFF00),        // Square - amarelo neon
            new Color(0x0099FF),        // L - azul elétrico
            new Color(0xFF6600)         // MirroredL - laranja neon
        }
    );

    // Array com todos os temas disponíveis
    public static final Theme[] AVAILABLE_THEMES = {
        DARK_MINIMAL,
        RETRO_NEON
    };
}
