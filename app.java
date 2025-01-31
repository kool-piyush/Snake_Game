import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


class Mole_game {
    int boardwidth = 600;
    int boardheight = 650;

    JFrame frame = new JFrame("Mole Game");
    JPanel textpanel = new JPanel();
    JLabel textlabel = new JLabel();
    JPanel boardpanel = new JPanel();

    JButton[] board = new JButton[9];

    ImageIcon planticon;
    ImageIcon marioicon;

    JButton currPlantTile;
    JButton currMariotTile;

    Random rand = new Random();
    Timer setMarioTimer;
    Timer setPlantTimer;

    int Score = 0;


    Mole_game() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        textlabel.setFont(new Font("Serif", Font.BOLD, 50));
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setText("Score : 0 ");
        textlabel.setOpaque(true);

        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel, BorderLayout.NORTH);

        boardpanel.setLayout(new GridLayout(3, 3));
        frame.add(boardpanel);

//        planticon =  new ImageIcon(getClass().getResource("./pirana.jpg"));
        Image plantimg = new ImageIcon(getClass().getResource("./pirana.jpg")).getImage();
        planticon = new ImageIcon(plantimg.getScaledInstance(170, 190, Image.SCALE_DEFAULT));

        Image marioimg = new ImageIcon(getClass().getResource("./MARRIO.png")).getImage();
        marioicon = new ImageIcon(marioimg.getScaledInstance(170, 190, Image.SCALE_DEFAULT));

        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardpanel.add(tile);
            tile.setFocusable(false);
//            tile.setIcon(marioicon);

            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if (tile == currMariotTile) {
                        Score += 10;
                        textlabel.setText("Score : " + Integer.toString(Score));
                    } else if (tile == currPlantTile) {
                        textlabel.setText("Game Over : " + Integer.toString(Score));
                        setMarioTimer.stop();
                        setPlantTimer.stop();
                        for (int i = 0; i < 9; i++) {
                            board[i].setEnabled(false);
                        }
                    }
                }
            });

        }
        setMarioTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currMariotTile != null) {
                    currMariotTile.setIcon(null);
                    currMariotTile = null;
                }

                int num = rand.nextInt(9);
                JButton tile = board[num];

                if (currPlantTile == tile) return;

                currMariotTile = tile;
                currMariotTile.setIcon(marioicon);
            }
        });

        setPlantTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currPlantTile != null) {
                    currPlantTile.setIcon(null);
                    currPlantTile = null;
                }

                int num = rand.nextInt(9);
                JButton tile = board[num];

                if (currMariotTile == tile) return;

                currPlantTile = tile;
                currPlantTile.setIcon(planticon);
            }
        });

        setPlantTimer.start();
        setMarioTimer.start();
        frame.setVisible(true);
    }
}
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Mole_game mole = new Mole_game();

    }
}
