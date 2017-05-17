import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by 91632 on 2017/5/17 0017.
 */
public class ChessBoard extends JPanel {

    final int hLineNum = 14;
    final int vLineNum = 14;
    final int margin = 20;
    final int chessSize = 22;

    public double lineGap;

    public Date startTime;
    public Date endTime;
    public int[][] chessBoard;
    public String winner;

    public ChessBoard(int width, int[][] chessBoard, String winner, Date startTime, Date endTime) {
        this.lineGap = (width - margin * 2) / vLineNum;
        this.chessBoard = chessBoard;
        this.winner = winner;
        this.startTime = startTime;
        this.endTime = endTime;

        this.setBackground(new Color(0xf1d38d));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer.drawBoard(g, vLineNum, hLineNum, margin, lineGap);
        Drawer.drawChess(g, hLineNum, vLineNum, chessBoard, lineGap, margin, chessSize, this);
    }

}
