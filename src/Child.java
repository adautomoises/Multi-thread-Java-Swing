import java.net.URL;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Child extends Thread {

  private long tempoBrincando;
  private long tempoQuieta;
  private Semaphore cheio;
  private Semaphore vazio;
  private Boolean bola;

  public String getNome() {
    return nome;
  }

  private String nome;

  private JLabelWrapper JLabelWrapper;

  public Child(String nome, long tempoBrincando, long tempoQuieta, Boolean bola, Semaphore cheio,
      Semaphore vazio,
      JLabelWrapper JLabelWrapper
  ) {
    this.tempoBrincando = tempoBrincando * 1000;
    this.tempoQuieta = tempoQuieta * 1000;
    this.cheio = cheio;
    this.vazio = vazio;
    this.bola = bola;
    this.nome = nome;
    this.JLabelWrapper = JLabelWrapper;
  }

  public enum ChildState {
    REST_1,
    REST_2,
    WAITING,
    WAITING_SPACE,
    PLAYING_1,
    PLAYING_2
  }

  @Override
  public void run() {
    while (true) {
      if (bola) {
        changeIcon(ChildState.PLAYING_1);
        System.out.println(this.getNome() + " está com a bola!");
      } else {
        System.out.println(this.getNome() + " não está com a bola!");
        try {
          System.out.println(this.getNome() + " tenta pegar a bola no cesto...");
          changeIcon(ChildState.WAITING);
          JLabelWrapper.childTime.setText("Esperando a bola");
          cheio.acquire();
          // Mostrar quantidade de bola nos cestos
          JLabelWrapper.ballsQnt.setText(
              Integer.toString(Integer.parseInt(JLabelWrapper.ballsQnt.getText()) - 1)
          );
          JLabelWrapper.childTime.setText("");
          System.out.println(this.getNome() + " consegue pegar a bola no cesto!");
          vazio.release();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      long tempoInicialBrincando = System.currentTimeMillis();
      long tempo = 0;
      long tempo2 = tempoBrincando;

      changeIcon(ChildState.PLAYING_1);

      System.out.println(this.getNome() + " começou a brincar: " + tempo);


      boolean flag = false;

      long upTimer = 150;
      long upTimerReset = System.currentTimeMillis();
      int upTimerLimit = 150;
      while (tempo < tempoBrincando) {
        String k = Integer.toString(Integer.parseInt(String.valueOf(tempo2 /1000)));
        JLabelWrapper.childTime.setText("TBrincando: " + k + "s");
        if (upTimer >= upTimerLimit) {
          if (flag) {
            changeIcon(ChildState.PLAYING_1);
          } else {
            changeIcon(ChildState.PLAYING_2);
          }
          upTimerReset = System.currentTimeMillis();
          flag = !flag;
        }
        tempo2 = tempoBrincando - tempo;
        tempo = System.currentTimeMillis() - tempoInicialBrincando; // 10ms ... 200ms .. 201ms
        upTimer = System.currentTimeMillis() - upTimerReset;
      }
      if (flag) {
        changeIcon(ChildState.PLAYING_1);

      }

      System.out.println(this.getNome() + " terminou de brincar: " + tempo);

      try {
        changeIcon(ChildState.WAITING_SPACE);
        System.out.println(this.getNome() + " tenta colocar a bola no cesto...");
        JLabelWrapper.childTime.setText("Esperando espaço");

        vazio.acquire();
        JLabelWrapper.ballsQnt.setText(
            Integer.toString(Integer.parseInt(JLabelWrapper.ballsQnt.getText()) + 1)
        );
        JLabelWrapper.childTime.setText("");
        System.out.println(this.getNome() + " consegue colocar a bola no cesto!");
        this.bola = false;
        cheio.release();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      changeIcon(ChildState.REST_1);

      long tempoInicialQuieta = System.currentTimeMillis();
      tempo = 0;
      tempo2 = tempoQuieta;

      System.out.println(this.getNome() + " foi se aquietar: " + tempo);


      boolean quietFlag = false;

      long quietTimer = 150;
      long quietTimerReset = System.currentTimeMillis();
      int quietTimerLimit = 150;
      while (tempo < tempoQuieta) {
        String k = Integer.toString(Integer.parseInt(String.valueOf(tempo2 /1000)));
        JLabelWrapper.childTime.setText("TQuieta: " + k + "s");

        if (quietTimer >= quietTimerLimit) {
          if (quietFlag) {
            changeIcon(ChildState.REST_1);
          } else {
            changeIcon(ChildState.REST_2);
          }
          quietTimerReset = System.currentTimeMillis();
          quietFlag = !quietFlag;
        }
        tempo2 = tempoQuieta - tempo;
        tempo = System.currentTimeMillis() - tempoInicialQuieta;
        quietTimer = System.currentTimeMillis() - quietTimerReset;
      }
      if (quietFlag) {
        changeIcon(ChildState.REST_1);

      }

      System.out.println(this.getNome() + " terminou de se aquietar: " + tempo);
    }
  }

  private void changeIcon(ChildState state) {
    ImageIcon childIcon = getChildIcon(state);
    JLabelWrapper.childLabel.setIcon(childIcon);
    JLabelWrapper.childLabel.setText(getNome());
  }

  public static ImageIcon getChildIcon(ChildState state) {
    // Switch case que adiciona op valor da url para a imagem da criança
    URL imageChildURL;
    switch (state) {
      case WAITING:
        imageChildURL = Main.class.getResource("/image/no_ball.png");
        break;
      case WAITING_SPACE:
        imageChildURL = Main.class.getResource("/image/no_space.png");
        break;
      case REST_1:
        imageChildURL = Main.class.getResource("/image/kite_1.png");
        break;
      case REST_2:
        imageChildURL = Main.class.getResource("/image/kite_2.png");
        break;
      case PLAYING_1:
        imageChildURL = Main.class.getResource("/image/ball_1.png");
        break;
      case PLAYING_2:
        imageChildURL = Main.class.getResource("/image/ball_2.png");
        break;
      default:
        imageChildURL = null;
    }

    assert imageChildURL != null;

    return new ImageIcon(
        imageChildURL
    );
  }
}
