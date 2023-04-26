import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.concurrent.Semaphore;

class Main {

  public static Integer MAX_PERMITIONS = 0;

  public static void setMaxPermitions(Integer maxPermitions) {
    MAX_PERMITIONS = maxPermitions;
  }

  static class SemaphoreWrapper {
    //QUANTIDADE DE BOLAS NO CESTO K
    Semaphore cheio = new Semaphore(0);
    //QUANTIDADE DE ESPAÇOS VAZIOS NO CESTO K
    Semaphore vazio = new Semaphore(MAX_PERMITIONS );
  }

  public static void main(String[] args) {
    SemaphoreWrapper wrapper = new SemaphoreWrapper();

    //JANELA INICIAL DA APLICAÇÃO
    JFrame frameAplicacao = new JFrame("Janela da Aplicação");
    frameAplicacao.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frameAplicacao.setSize(1600, 900);
    JPanel panelAplicacao = new JPanel(null);
    panelAplicacao.setSize(500, 500);
    JButton botaoAbrirModalCriarThread = new JButton("Abrir Modal Criar Thread");
    botaoAbrirModalCriarThread.setBounds(0, 0, 1600, 50);

    URL imageURL = Main.class.getResource("/image/background.jpg"); // carrega a imagem
    assert imageURL != null;
    ImageIcon backgroundIcon = new ImageIcon(imageURL); // cria um ImageIcon com a imagem carregada
    JLabel backgroundAplicacao = new JLabel(backgroundIcon); // cria um JLabel com o ImageIcon
    backgroundAplicacao.setSize(frameAplicacao.getSize());

    //ADICIONANDO COMPONENTES NA JANELA INICIAL DA APLICAÇÃO
    panelAplicacao.add(backgroundAplicacao);
    panelAplicacao.add(botaoAbrirModalCriarThread);
    frameAplicacao.add(panelAplicacao);

    //FRAME MODAL DE CRIAÇÃO DA THREAD
    JFrame frameModalCriarThread = new JFrame("Frame Modal Criar Thread");
    frameModalCriarThread.setSize(400, 200);
    frameModalCriarThread.setLocationRelativeTo(null);

    JFrame childFrame = new JFrame("Testando");
    childFrame.setSize(400, 200);

    //PANEL MODAL DE CRIAÇÃO DA THREAD COM LAYOUT VERTICALIZADO
    JPanel panelModalCriarThread = new JPanel();
    BoxLayout layoutModalCriarThread = new BoxLayout(panelModalCriarThread, BoxLayout.Y_AXIS);
    panelModalCriarThread.setLayout(layoutModalCriarThread);
    panelModalCriarThread.setSize(400, 200);
    //COMPONENTES DE CRIAÇÃO DA THREAD
    JLabel labelNomeDaCrianca = new JLabel("Nome da Criança:");
    labelNomeDaCrianca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JTextField textFieldNomeDaCrianca = new JTextField();
    textFieldNomeDaCrianca.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JLabel labelTempoBrincando = new JLabel("Tempo Brincando (em segundos):");
    labelTempoBrincando.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JTextField textFieldTB = new JTextField();
    textFieldTB.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JLabel labelTempoQuieto = new JLabel("Tempo Quieto (em segundos):");
    labelTempoQuieto.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JTextField textFieldTQ = new JTextField();
    textFieldTQ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JCheckBox checkBoxBola = new JCheckBox("Bola");
    checkBoxBola.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JButton buttonCriarThread = new JButton("Criar Thread");
    buttonCriarThread.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    //ADICIONANDO COMPONENTES NO MODAL DE CRIAÇÃO DA THREAD
    panelModalCriarThread.add(labelNomeDaCrianca);
    panelModalCriarThread.add(textFieldNomeDaCrianca);
    panelModalCriarThread.add(labelTempoBrincando);
    panelModalCriarThread.add(textFieldTB);
    panelModalCriarThread.add(labelTempoQuieto);
    panelModalCriarThread.add(textFieldTQ);
    panelModalCriarThread.add(checkBoxBola);
    panelModalCriarThread.add(buttonCriarThread);
    frameModalCriarThread.add(panelModalCriarThread);

    //CRIANDO O MODAL DE CRIAÇÃO DA THREAD
    JDialog modalCriarThread = new JDialog(frameModalCriarThread, "Modal Criar Thread");
    modalCriarThread.setSize(400, 200);

    //FRAME MODAL DE INICIALIZAR A APLICAÇÃO
    JFrame frameModalIniciarAplicacao = new JFrame("Frame Modal Iniciar Aplicação");
    frameModalIniciarAplicacao.setSize(400, 150);
    frameModalIniciarAplicacao.setLocationRelativeTo(null);

    //MODAL DE CRIAÇÃO DA THREAD COM LAYOUT VERTICALIZADO
    JPanel panelModalIniciarAplicacao = new JPanel();
    BoxLayout layoutModalIniciarAplicacao = new BoxLayout(panelModalIniciarAplicacao,
        BoxLayout.Y_AXIS);
    panelModalIniciarAplicacao.setLayout(layoutModalIniciarAplicacao);
    panelModalIniciarAplicacao.setSize(400, 150);

    //COMPONENTES DE INICIALIZAR A APLICAÇÃO
    JLabel labelCapacidadeCestoK = new JLabel("Capacidade do Cesto de Bolas:");
    labelCapacidadeCestoK.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JTextField textFieldCapacidadeCestoK = new JTextField();
    textFieldCapacidadeCestoK.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JLabel labelQuantidadeInicialN = new JLabel("Quantidade de crianças inicialmente:");
    labelQuantidadeInicialN.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JTextField textFieldQuantidadeInicialN = new JTextField();
    textFieldQuantidadeInicialN.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
    JButton buttonAbrirModalInstanciarThreads = new JButton("Inicializar Threads");
    buttonAbrirModalInstanciarThreads.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    //ADICIONAR COMPONENTES NO PANEL MODAL DE INICIALIZAR A APLICAÇÃO
    panelModalIniciarAplicacao.add(labelCapacidadeCestoK);
    panelModalIniciarAplicacao.add(textFieldCapacidadeCestoK);
    panelModalIniciarAplicacao.add(buttonAbrirModalInstanciarThreads);
    frameModalIniciarAplicacao.add(panelModalIniciarAplicacao);

    //CRIANDO O MODAL DE INICIALIZAR A APLICAÇÃO
    JDialog modalIniciarAplicacao = new JDialog(frameModalIniciarAplicacao,
        "Modal Iniciar Aplicação");
    modalIniciarAplicacao.setSize(400, 200);

    //FRAME MODAL DE INSTANCIAR THREADS
    JFrame frameModalInstanciarThreads = new JFrame("Frame Modal Instanciar Threads");
    frameModalInstanciarThreads.setSize(400, 200);

    //MODAL DE INSTANCIAR THREADS COM LAYOUT VERTICALIZADO
    JPanel panelModalInstanciarThreads = new JPanel();
    BoxLayout layoutModalInstanciarThreads = new BoxLayout(panelModalInstanciarThreads,
        BoxLayout.Y_AXIS);
    panelModalInstanciarThreads.setLayout(layoutModalInstanciarThreads);
    panelModalInstanciarThreads.setSize(400, 200);

    //CRIANDO O MODAL DE INICIALIZAR A APLICAÇÃO
    JDialog modalInstanciarThreads = new JDialog(frameModalInstanciarThreads,
        "Modal Instanciar Threads");
    modalInstanciarThreads.setSize(400, 200);

    //FUNÇÕES DOS BOTÕES DA JANELA DE APLICAÇÃO
    //ABRE O MODAL CRIAÇÃO DA THREAD CRIANÇA
    botaoAbrirModalCriarThread.addActionListener((e -> {
      frameModalCriarThread.setVisible(true);
    }));
    //ABRE O MODAL INICIALIZAR APLICACAO
    frameModalIniciarAplicacao.setVisible(true);

    JLabel ballsMaxLabel = new JLabel("Quantidade Máxima: NaN");
    JLabel ballsLabel = new JLabel("Bolas no Cesto:");
    JLabel ballsQntLabel = new JLabel("0");

    ballsLabel.setSize(350, 350);
    ballsLabel.setLocation(450, 50);

    ballsMaxLabel.setSize(350, 350);
    ballsMaxLabel.setLocation(450, 75);

    ballsQntLabel.setSize(350, 350);
    ballsQntLabel.setLocation(550, 50);

    panelAplicacao.add(ballsMaxLabel);
    panelAplicacao.add(ballsLabel);
    panelAplicacao.add(ballsQntLabel);

    int componentCount2 = panelAplicacao.getComponentCount();
    panelAplicacao.setComponentZOrder(backgroundAplicacao, componentCount2 - 1);
    panelAplicacao.repaint();

    // INICIALIZAR THREADS
    buttonAbrirModalInstanciarThreads.addActionListener(e -> {
      // INICIALIZA O CESTO COM X ESPAÇOS VAZIOS
      setMaxPermitions(Integer.parseInt(textFieldCapacidadeCestoK.getText()));
      frameAplicacao.setVisible(true);
      frameModalIniciarAplicacao.setVisible(false);
      wrapper.vazio = new Semaphore(MAX_PERMITIONS);
      ballsMaxLabel.setText("Quantidade Máxima: " + MAX_PERMITIONS);
    });

    class ChildSpacer {

      int spacer;

      public ChildSpacer(int spacer) {
        this.spacer = spacer;
      }
    }

    ChildSpacer childSpacer = new ChildSpacer(0);

    //INSTANCIA UMA THREAD CRIANÇA
    buttonCriarThread.addActionListener((e -> {

      ImageIcon childIcon = getChildIcon();
      JLabel childLabel = new JLabel();
      JLabel childName = new JLabel(textFieldNomeDaCrianca.getText());
      JLabel childTime = new JLabel();

      childLabel.setIcon(childIcon);
      Dimension size = childLabel.getPreferredSize();

      childLabel.setBounds(childSpacer.spacer, 370, size.width, size.height);
      childName.setBounds(childSpacer.spacer + 450, 250, size.width, size.height);
      childTime.setBounds(childSpacer.spacer + 450, 270, size.width, size.height);

      // TODO: Randomize this initial position?
      childSpacer.spacer = childSpacer.spacer + 150;

      panelAplicacao.add(childLabel);
      panelAplicacao.add(childName);
      panelAplicacao.add(childTime);
      childLabel.setVisible(true);
      childName.setVisible(true);
      childTime.setVisible(true);

      int componentCount = panelAplicacao.getComponentCount();
      panelAplicacao.setComponentZOrder(backgroundAplicacao, componentCount - 1);
      panelAplicacao.repaint();

      frameModalCriarThread.setVisible(false);

      JLabelWrapper JLabelWrapper = new JLabelWrapper(childTime, childLabel, panelAplicacao,
          backgroundAplicacao, ballsQntLabel);

      Child thread = new Child(textFieldNomeDaCrianca.getText(),
          Integer.parseInt(textFieldTB.getText()), Integer.parseInt(textFieldTQ.getText()),
          checkBoxBola.isSelected(), wrapper.cheio, wrapper.vazio, JLabelWrapper
      );
      thread.start();
    }));
  }

  private static ImageIcon getChildIcon() {
    URL imageChildURL = Main.class.getResource("/image/no_space.png");
    assert imageChildURL != null;

    return new ImageIcon(
        imageChildURL
    );
  }
}