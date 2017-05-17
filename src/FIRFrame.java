import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by 91632 on 2017/5/14 0014.
 */
public class FIRFrame extends JFrame {

    JMenuBar menuBar = new JMenuBar();
    // 开始游戏
    JMenu menu1;
    JMenuItem menu1Item1;
    JMenuItem menu1Item2;

    // 历史纪录
    JMenu menu2;
    JMenuItem menu2Item1;

    JMenu menu3;  // 设置

    JPanel buttomIfo = new JPanel(new GridLayout(1, 2));
    JLabel lblBTime = new JLabel("black time", SwingConstants.CENTER);
    JLabel lblWTime = new JLabel("white time", SwingConstants.CENTER);

    JPanel topIfo = new JPanel(new GridLayout(2, 1));
    JLabel whoPlay = new JLabel("轮到黑方", SwingConstants.CENTER);
    JLabel timePassed = new JLabel("00:00", SwingConstants.CENTER);
    private JMenuItem menu3Item1;

    GamePanel board;

    public FIRFrame() {
        this.setTitle("五子棋");
        this.setSize(500, 600);
        this.setLayout(new BorderLayout(10, 0));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initialComponent();

    }

    private void initialComponent() {

        board = new GamePanel(this.getWidth(), timePassed, whoPlay);

        board.setDoubleBuffered(true);
        board.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.mouseClick(e);
            }
        });
        this.add(board, BorderLayout.CENTER);

        // ==== menubar ====
        {
            // === menu1 ===
            {
                menu1 = new JMenu("开始游戏");
                menu1Item1 = new JMenuItem("人机对战");
                menu1Item2 = new JMenuItem("玩家对战");

                menu1Item1.addActionListener(e-> menu1Item1ActionListener(e));
                menu1Item2.addActionListener(e->menu1Item2ActionListener(e));
                menu1.add(menu1Item1);
                menu1.add(menu1Item2);
            }
            menuBar.add(menu1);

            // === menu2 ===
            {
                menu2 = new JMenu("历史记录");
                menu2Item1 = new JMenuItem("查询");
                menu2Item1.addActionListener(e-> menu2Item1ActionListener(e));
                menu2.add(menu2Item1);
            }
            menuBar.add(menu2);

            // === menu3 ===
            {
                menu3 = new JMenu("设置");
                menu3Item1 = new JMenuItem("打开设置");
                menu3Item1.addActionListener(e->menu3Item1ActionListener(e));
                menu3.add(menu3Item1);
            }
            menuBar.add(menu3);
        }
        this.setJMenuBar(menuBar);

        // ==== buttomIfo ====
        {
            buttomIfo.add(lblBTime);
            buttomIfo.add(lblWTime);
        }
        buttomIfo.setBackground(new Color(0xf1d38d));
        this.add(buttomIfo, BorderLayout.SOUTH);

        // ==== topIfo ====
        {
            topIfo.add(timePassed);
            topIfo.add(whoPlay);
        }
        topIfo.setBackground(new Color(0xf1d38d));
        this.add(topIfo, BorderLayout.NORTH);


    }

    // 设置面板
    private void menu3Item1ActionListener(ActionEvent e) {
        new SettingDialog().setVisible(true);
    }

    // 历史记录
    private void menu2Item1ActionListener(ActionEvent e) {
        new HistoryDialog().setVisible(true);
        System.out.println("menu2 clicked");
    }

    // 玩家对战
    private void menu1Item2ActionListener(ActionEvent e) {
        board.restart();
    }

    // 人机对战
    private void menu1Item1ActionListener(ActionEvent e) {
        JOptionPane.showMessageDialog(this,"尽情期待：https://github.com/Zeqiang-Lai/FIR");
    }

}
