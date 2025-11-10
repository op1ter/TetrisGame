Perfeito ✅
Aqui está a **versão atualizada do README** do seu projeto **Tetris em Java**, agora refletindo corretamente que é um **projeto individual de desenvolvimento**:

---

# 🎮 Projeto Tetris em Java

## 🧩 Descrição do Projeto

Este projeto é uma recriação do clássico **Tetris**, desenvolvida em **Java**, com foco em **orientação a objetos**, **organização modular** e **recursos multimídia** (sons e música de fundo).

O jogo foi criado como um **projeto individual de desenvolvimento**, com o objetivo de aplicar conceitos práticos de **Análise e Projeto Orientado a Objetos**, **Estrutura de Dados** e **interface gráfica com Swing**.

---

## 🏗️ Estrutura do Projeto

A estrutura segue o padrão de organização por pacotes e recursos:

```
Tetrisgame/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── game/
│   │   │   │   ├── Tetris.java
│   │   │   │   ├── Board.java
│   │   │   │   ├── Piece.java
│   │   │   │   ├── GamePanel.java
│   │   │   │   ├── Controller.java
│   │   │   │   ├── SoundManager.java
│   │   │   │   ├── ScoreManager.java
│   │   │   │   └── Utils.java
│   │   └── resources/
│   │       ├── sounds/
│   │       │   ├── rotate.wav
│   │       │   ├── line.wav
│   │       │   ├── drop.wav
│   │       │   ├── gameover.wav
│   │       │   └── music.wav
│   │       └── images/
│   │           ├── icon.png
│   │           └── background.png
└── README.md
```

---

## 🧠 Principais Classes

| Classe                  | Função                                                           |
| ----------------------- | ---------------------------------------------------------------- |
| **`Tetris.java`**       | Classe principal; inicializa o jogo e gerencia o loop principal. |
| **`Board.java`**        | Lógica do tabuleiro, verificação de linhas e pontuação.          |
| **`Piece.java`**        | Define os tetrominos (formas) e suas rotações.                   |
| **`GamePanel.java`**    | Renderização dos elementos gráficos do jogo.                     |
| **`Controller.java`**   | Captura e interpreta os comandos do jogador.                     |
| **`SoundManager.java`** | Gerencia os sons e músicas do jogo.                              |
| **`ScoreManager.java`** | Controla a pontuação e níveis do jogador.                        |

---

## 🔊 Sistema de Áudio

O sistema sonoro utiliza **Java Sound API** (`javax.sound.sampled`) para reproduzir **arquivos `.wav`**.
Os sons são carregados e gerenciados pela classe `SoundManager`.

| Ação            | Tipo   | Arquivo        |
| --------------- | ------ | -------------- |
| Rotação de peça | Efeito | `rotate.wav`   |
| Linha completa  | Efeito | `line.wav`     |
| Queda rápida    | Efeito | `drop.wav`     |
| Fim de jogo     | Efeito | `gameover.wav` |
| Música de fundo | Loop   | `music.wav`    |

A música de fundo pode tocar em **loop contínuo**, e os efeitos são executados sob demanda conforme os eventos do jogo.

---

## 🕹️ Controles

| Tecla     | Ação                              |
| --------- | --------------------------------- |
| ⬅️ / ➡️   | Move a peça para esquerda/direita |
| ⬇️        | Acelera a queda da peça           |
| ⬆️ ou `W` | Rotaciona a peça                  |
| `Espaço`  | Faz a peça cair instantaneamente  |
| `P`       | Pausa / Retoma o jogo             |
| `R`       | Reinicia o jogo                   |
| `Esc`     | Sai do jogo                       |

---

## 🧾 Regras e Pontuação

* Cada linha completada concede **100 pontos**.
* Linhas múltiplas rendem **bônus progressivos**.
* O nível e a velocidade aumentam conforme a pontuação.
* O jogo termina quando o tabuleiro é preenchido até o topo.

---

## 🖼️ Interface Gráfica

O jogo utiliza **Java Swing** para desenhar o tabuleiro e elementos visuais.
Recursos incluídos:

* Exibição de pontuação, nível e próxima peça.
* Fundo temático.
* Ícones personalizados.
* Efeitos sonoros integrados.

---

## ⚙️ Como Executar

### 1. Compilar o projeto

No terminal, dentro da pasta do projeto:

```bash
javac -d bin src/main/java/game/*.java
```

### 2. Executar o jogo

```bash
java -cp bin game.Tetris
```

Ou execute diretamente a classe `Tetris.java` pela sua IDE (IntelliJ, Eclipse, VS Code, etc).

---

## 🎧 Adição de Sons

Os arquivos `.wav` devem estar na pasta:

```
src/main/resources/sounds/
```

Para tocar sons, o código usa:

```java
SoundManager.playSound("line");
SoundManager.playSound("rotate");
SoundManager.playMusicLoop("music");
```

---

## 👤 Autor

**Desenvolvido individualmente por:**
🎓 **João Victor Felipe**
📚 Curso: *Análise e Desenvolvimento de Sistemas*

---

## 🧰 Tecnologias Utilizadas

* **Java 17+**
* **Swing / AWT**
* **Java Sound API**
* **Paradigma Orientado a Objetos**
* **Maven / Gradle (opcional)**

---

## 🏁 Licença

Este projeto é de uso **educacional** e pode ser modificado e redistribuído livremente para fins de aprendizado.
