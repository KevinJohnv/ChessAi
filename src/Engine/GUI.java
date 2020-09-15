package Engine;

import javax.swing.*;
import java.awt.*;


/*Base GUI class*/
public class GUI extends JFrame{

    public static Painter painter;
    public static ActionLog log;
    public static final int SQUARE_SIZE = 60;
    public final int SPACER_WIDTH = 300;
    public final int LOG_HEIGHT = 100;
    public final int STATUS_HEIGHT = 40;
    public static CapturePanel[] capturePanels;

    public GUI(){
        super("Chess AI");
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem new_game = new JMenuItem("New Game");
        file.add(new_game);
        menu.add(file);
        this.setJMenuBar(menu);
        
        new Engine();
        //status panel to show whos turn - moves made - and a button to end turn
        StatusPanel status = new StatusPanel(this.getWidth(), STATUS_HEIGHT);
        this.getContentPane().add(status, BorderLayout.NORTH);

        capturePanels = new CapturePanel[2];
        //capture panels to show total captures each player has made
        capturePanels[0] = new CapturePanel(SPACER_WIDTH, this.getHeight(),1);
        this.getContentPane().add(capturePanels[0], BorderLayout.WEST);

        capturePanels[1] = new CapturePanel(SPACER_WIDTH, this.getHeight(),2);
        this.getContentPane().add(capturePanels[1], BorderLayout.EAST);

        JPanel full_board = new JPanel();
        full_board.setBackground(Color.BLACK);
        full_board.setPreferredSize(new Dimension((SQUARE_SIZE) * 8 + 40,(SQUARE_SIZE * 8) + 40));
        full_board.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        for(char i = 'A'; i != 'I';i++){
            JLabel column = new JLabel(i + "",SwingConstants.CENTER);
            column.setForeground(Color.WHITE);
            constraints.gridy = i - 64;
            constraints.weighty = 1;
            constraints.weightx = 1;
            constraints.gridx = 0;
            full_board.add(column,constraints);
        }

        for(int i = 1; i < 9;i++){
            JLabel row = new JLabel(i + "",SwingConstants.CENTER);
            row.setForeground(Color.WHITE);
            constraints.gridx = i;
            constraints.gridy = 0;
            constraints.weighty = 1;
            constraints.weightx = 1;
            full_board.add(row,constraints);
        }

        painter = new Painter();
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 8;
        constraints.gridheight = 8;
        full_board.add(painter,constraints);
        this.getContentPane().add(full_board, BorderLayout.CENTER);

        this.setVisible(true);
        this.requestFocus();
        painter.repaint();
        
        //a scrollable text area to log the actions of the game
        log = new ActionLog(this.getWidth(), LOG_HEIGHT);
        this.getContentPane().add(log, BorderLayout.SOUTH);
        
        this.pack();
        this.setLocationRelativeTo(null);
        
        
    }

}
