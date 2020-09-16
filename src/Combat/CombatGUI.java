package Combat;

import Chess.Pieces.BasePiece;
import Dice.Dice;
import Engine.Engine;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CombatGUI extends JFrame {

    private BasePiece piece_one;
    private BasePiece piece_two;
    private int[] combat_stats;
    private JPanel dicePanel;
    private int roll_count;
    private int current_roll;
    private Random random;
    private Dice currentDice;

    /*REDO OR REWRITE WHOLE CLASS, I WROTE IT POORLY*/

    public CombatGUI(BasePiece piece_one,BasePiece piece_two){
        setPiece_one(piece_one);
        setPiece_two(piece_two);
        combat_stats = Engine.get_Rolls(piece_one.getID(),piece_two.getID());
        random = new Random();

        setSize(new Dimension(400,400));
        setLayout(new BorderLayout());
        JLabel player_1 = new JLabel(new ImageIcon(piece_one.getImage()));
        this.getContentPane().add(player_1,BorderLayout.WEST);

        JLabel player_2 = new JLabel(new ImageIcon(piece_two.getImage()));
        this.getContentPane().add(player_2,BorderLayout.EAST);
        this.setVisible(true);


        dicePanel = new JPanel();
        this.getContentPane().add(dicePanel,BorderLayout.CENTER);
        dicePanel.setLayout(new GridLayout(1,1));
        dicePanel.add(new JLabel(new ImageIcon(getRandom_Dice().getImage())));

        JLabel stats_needed = new JLabel(String.format("Dice Rolls Needed: "),SwingConstants.CENTER);
        stats_needed.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        for(int i : combat_stats)
            stats_needed.setText(stats_needed.getText() + " " + i);

        this.getContentPane().add(stats_needed,BorderLayout.NORTH);

        JPanel options = new JPanel();
        JButton roll = new JButton("Roll");

        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roll_count = random.nextInt(5) + 5;
                current_roll = 0;
                randomize_roll.start();
            }
        });
        roll.setSize(new Dimension(100,50));
        options.add(roll);

        JButton leave = new JButton("Leave");
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        leave.setSize(new Dimension(100,50));
        options.add(leave);
        this.getContentPane().add(options,BorderLayout.SOUTH);
    }

    public ActionListener update_dice = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(current_roll++ > roll_count)
                randomize_roll.stop();
            dicePanel.invalidate();
            dicePanel.removeAll();
            dicePanel.add(new JLabel(new ImageIcon(getRandom_Dice().getImage())));
            dicePanel.revalidate();
        }
    };
    private Timer randomize_roll = new Timer(200,update_dice);

    public Dice getRandom_Dice(){
        this.currentDice = Engine.dice[random.nextInt(6)];
        return currentDice;
    }

    public BasePiece getPiece_one() {
        return piece_one;
    }

    public void setPiece_one(BasePiece piece_one) {
        this.piece_one = piece_one;
    }

    public BasePiece getPiece_two() {
        return piece_two;
    }

    public void setPiece_two(BasePiece piece_two) {
        this.piece_two = piece_two;
    }
}