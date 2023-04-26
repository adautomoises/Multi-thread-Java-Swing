import javax.swing.JLabel;
import javax.swing.JPanel;

public class JLabelWrapper {

  JLabel childTime;
  JLabel childLabel;
  JPanel applicationPanel;
  JLabel backgroundPanel;
  final JLabel ballsQnt;


  public JLabelWrapper(JLabel childTime, JLabel childLabel, JPanel applicationPanel, JLabel backgroundPanel,
      JLabel ballsQnt) {
    this.childLabel = childLabel;
    this.applicationPanel = applicationPanel;
    this.backgroundPanel = backgroundPanel;
    this.ballsQnt = ballsQnt;
    this.childTime = childTime;
  }
}

