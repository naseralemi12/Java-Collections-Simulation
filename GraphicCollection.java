import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class GraphicCollection extends JFrame {

  public JButton gButton, qButton, lButton, vButton, mButton;
  public JLabel emptyLabel, qWarningLabel, gLabel, qLabel, vLabel, lLabel, mLabel, lWarningLabel, vWarningLabel, mWarningLabel;
  public JPanel outerPanel, buttonPanel, messagePanel, emptyPanel, generatePanel, queuePanel, listPanel, vectorPanel, mapPanel;
  public CardLayout card;
  public Click obj = new Click();

  public Stack<Integer> randomNumsS = new Stack<Integer>();
  public Queue<Integer> randomNumsQ = new LinkedList<Integer>();
  public ArrayList<Integer> randomNumsL = new ArrayList<Integer>();
  public Vector<Integer> randomNumsV = new Vector<Integer>();
  public Map<Integer, Integer> randomNumsM = new HashMap<Integer, Integer>();
  public Random rand = new Random();
  public int randomKey;

  public void init() {
    outerPanel = new JPanel();
    buttonPanel = new JPanel();
    messagePanel = new JPanel();
    card = new CardLayout();

    outerPanel.setLayout(new BorderLayout(4, 4)); //vgap,hgap
    buttonPanel.setLayout(new GridLayout(1, 5, 3, 3)); //vgap,hgap
    messagePanel.setLayout(card);

    outerPanel.add(buttonPanel, BorderLayout.NORTH);
    outerPanel.add(messagePanel, BorderLayout.CENTER);
    add(outerPanel);

    gButton = new JButton("Generate");
    gButton.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 12));
    gButton.setForeground(Color.red);
    buttonPanel.add(gButton);
    gButton.addMouseListener(obj);

    qButton = new JButton("Queue");
    qButton.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 12));
    qButton.setForeground(Color.black);
    buttonPanel.add(qButton);
    qButton.addMouseListener(obj);

    lButton = new JButton("List");
    lButton.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 12));
    lButton.setForeground(Color.blue);
    buttonPanel.add(lButton);
    lButton.addMouseListener(obj);

    vButton = new JButton("Vector");
    vButton.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 12));
    vButton.setForeground(Color.black);
    buttonPanel.add(vButton);
    vButton.addMouseListener(obj);

    mButton = new JButton("Map");
    mButton.setFont(new Font("Monospaced", Font.ITALIC | Font.BOLD, 12));
    mButton.setForeground(Color.black);
    buttonPanel.add(mButton);
    mButton.addMouseListener(obj);

    emptyPanel = new JPanel();
    emptyPanel.setLayout(new BorderLayout());
    emptyLabel = new JLabel("Nothing to display yet.", JLabel.CENTER);
    emptyPanel.add(emptyLabel, BorderLayout.CENTER);
    messagePanel.add("emptyPanel", emptyPanel);

    generatePanel = new JPanel();
    generatePanel.setLayout(new BorderLayout());
    gLabel = new JLabel("", JLabel.CENTER);
    generatePanel.add(gLabel, BorderLayout.CENTER);
    messagePanel.add("generatePanel", generatePanel);

    queuePanel = new JPanel();
    queuePanel.setLayout(new BorderLayout());
    qLabel = new JLabel("", JLabel.CENTER);
    qWarningLabel = new JLabel("", JLabel.CENTER);
    qWarningLabel.setForeground(Color.red);
    queuePanel.add(qLabel, BorderLayout.CENTER);
    queuePanel.add(qWarningLabel, BorderLayout.SOUTH);
    messagePanel.add("queuePanel", queuePanel);

    listPanel = new JPanel();
    listPanel.setLayout(new BorderLayout());
    lLabel = new JLabel("", JLabel.CENTER);
    lWarningLabel = new JLabel("", JLabel.CENTER);
    lWarningLabel.setForeground(Color.red);
    listPanel.add(lLabel, BorderLayout.CENTER);
    listPanel.add(lWarningLabel, BorderLayout.SOUTH);
    messagePanel.add("listPanel", listPanel);

    vectorPanel = new JPanel();
    vectorPanel.setLayout(new BorderLayout());
    vLabel = new JLabel("", JLabel.CENTER);
    vWarningLabel = new JLabel("", JLabel.CENTER);
    vWarningLabel.setForeground(Color.red);
    vectorPanel.add(vLabel, BorderLayout.CENTER);
    vectorPanel.add(vWarningLabel, BorderLayout.SOUTH);
    messagePanel.add("vectorPanel", vectorPanel);

    mapPanel = new JPanel();
    mapPanel.setLayout(new BorderLayout());
    mLabel = new JLabel("", JLabel.CENTER);
    mWarningLabel = new JLabel("", JLabel.CENTER);
    mWarningLabel.setForeground(Color.red);
    mapPanel.add(mLabel, BorderLayout.CENTER);
    mapPanel.add(mWarningLabel, BorderLayout.SOUTH);
    messagePanel.add("mapPanel", mapPanel);
    setSize(650, 180);
    setVisible(true);
  }

  public static void main(String[] args) {
    GraphicCollection frame = new GraphicCollection();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.init();
  }

  class Click extends MouseAdapter {

    public void mouseClicked(MouseEvent e) {
      if (e.getSource() == gButton) {
        randomNumsS.clear();
        randomNumsQ.clear();
        randomNumsL.clear();
        randomNumsV.clear();
        randomNumsM.clear();

        qWarningLabel.setText("");
        lWarningLabel.setText("");
        vWarningLabel.setText("");
        mWarningLabel.setText("");

        for (int i = 0; i < 5 && randomNumsS.size() <= 5; ++i) {
          randomNumsS.push(1 + rand.nextInt(100));
        }

        gLabel.setText(randomNumsS.toString());
        card.show(messagePanel, "generatePanel");
      } else if (e.getSource() == qButton) {
        if (randomNumsQ.size() <= 5 && randomNumsS.size() > 0) {
          randomNumsQ.add(randomNumsS.pop());
          qLabel.setText(randomNumsQ.toString());
        } else {
          qWarningLabel.setText("Stack is empty and Queue looks full!");
        }

        card.show(messagePanel, "queuePanel");
      } else if (e.getSource() == lButton) {
        if (randomNumsL.size() <= 5 && randomNumsQ.size() > 0) {
          randomNumsL.add(randomNumsQ.remove());
          lLabel.setText(randomNumsL.toString());
        } else {
          lWarningLabel.setText("Queue is empty and List looks full!");
        }

        card.show(messagePanel, "listPanel");
      } else if (e.getSource() == vButton) {
        if (randomNumsV.size() <= 5 && randomNumsL.size() > 0) {
          randomNumsV.add(randomNumsL.remove(0));
          vLabel.setText(randomNumsV.toString());
        } else {
          vWarningLabel.setText("List is empty and Vector looks full!");
        }

        card.show(messagePanel, "vectorPanel");
      } else if (e.getSource() == mButton) {
        if (randomNumsM.size() <= 5 && randomNumsV.size() > 0) {
          randomKey = 1 + rand.nextInt(100);
          while (randomNumsM.containsKey(randomKey)) {
            randomKey = 1 + rand.nextInt(100);
          }

          randomNumsM.put(randomKey, randomNumsV.remove(0));
          mLabel.setText(randomNumsM.toString());
        } else {
          mWarningLabel.setText("Vector is empty and map looks full!");
        }

        card.show(messagePanel, "mapPanel");
      }
    }
  }
}
