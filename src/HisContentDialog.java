import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Date;

/**
 * Created by 91632 on 2017/5/17 0017.
 */
public class HisContentDialog extends JDialog {

    private Date startTime;
    private Date endTime;
    private int[][] chessBoard;
    private String winner;
    private String costTime;

    File record;

    ChessBoard board;

    JPanel buttomIfo = new JPanel(new GridLayout(3, 1));
    JLabel lblSTime;
    JLabel lblETime;
    JLabel lblCostTime;
    JLabel lblWiner;

    public HisContentDialog(File record) {
        this.setSize(600, 690);
        this.setLayout(new BorderLayout(20, 0));
        this.setTitle(record.getName());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        this.record = record;
        initialComponent();
    }

    private void initialComponent() {
        parseRecord();
        board = new ChessBoard(this.getWidth(), chessBoard, winner, startTime, endTime);
        add(board, BorderLayout.CENTER);

        //  === buttomIfo ===
        {
            lblSTime = new JLabel("     游戏开始时间：" + startTime.toString(), SwingConstants.CENTER);
            lblCostTime = new JLabel("游戏耗时：" + costTime, SwingConstants.CENTER);
            lblETime = new JLabel("     游戏结束时间：" + endTime.toString(), SwingConstants.CENTER);
            lblWiner = new JLabel("胜利方：" + winner, SwingConstants.CENTER);

            buttomIfo.add(lblSTime);
            buttomIfo.add(lblCostTime);
            buttomIfo.add(lblETime);
            buttomIfo.add(lblWiner);
        }
        buttomIfo.setBackground(new Color(0xf1d38d));
        add(buttomIfo,BorderLayout.SOUTH);
    }

    private void parseRecord() {
        RecordData data = null;

        try {
            data = IOProcessor.readData(record);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"读取记录文件失败");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"记录文件："+record.getName()+" 损坏");
        }

        this.chessBoard = data.chessBoard;
        this.winner = data.winner;
        this.startTime = data.startTime;
        this.endTime = data.endTime;
        this.costTime = data.costTime;
    }

}
