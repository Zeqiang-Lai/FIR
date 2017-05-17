import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;

/**
 * Created by 91632 on 2017/5/14 0014.
 */
public class GamePanel extends JPanel {

    final int hLineNum = 14;
    final int vLineNum = 14;
    final int margin = 20;
    final int chessSize = 22;

    private double lineGap = 25;
    private int nowUser = 1; // 1 - black; -1 - white

    private boolean hasClick = false;

    private Date startTime = new Date();
    private Date nowTime = new Date();
    private int[][] chessBoard = new int[hLineNum + 2][vLineNum + 2];
    private String costTime;

    private JLabel timePassed;
    private JLabel whoPlay;

    Timer timeTicker = new Timer(1000, e -> {
        nowTime = new Date();
        int s = (int) ((nowTime.getTime() - startTime.getTime()) % (60 * 1000)) / 1000;
        int m = (int) (nowTime.getTime() - startTime.getTime()) / (60 * 1000);

        costTime = "";
        if (m != 0) costTime += m + ":";
        costTime += s;

        timePassed.setText(costTime);
    });

    public GamePanel(int width, JLabel timePassed, JLabel whoPlay) {
        this.setBackground(new Color(0xf1d38d));

        this.lineGap = (width - margin * 2) / vLineNum ;
        this.timePassed = timePassed;
        this.whoPlay = whoPlay;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Drawer.drawBoard(g, vLineNum, hLineNum, margin, lineGap);
        Drawer.drawChess(g, hLineNum, vLineNum, chessBoard, lineGap, margin, chessSize, this);
    }

    public void mouseClick(MouseEvent e) {

        System.out.println("X=" + e.getX() + ", Y=" + e.getY());

        int x = (int) ((e.getX() - margin + lineGap / 2) / lineGap);
        int y = (int) ((e.getY() - margin + lineGap / 2) / lineGap);
        if (x > hLineNum + 1 || y > vLineNum + 1 || x < 0 || y < 0) return;
        boolean win = false;
        String winUser = null;

        if (!hasClick) {
            startTime = new Date();
            timeTicker.start();
            hasClick = true;
        }

        if (chessBoard[x][y] == 0) {
            chessBoard[x][y] = nowUser;
            if (win = checkWin(x, y, nowUser)) {
                if (nowUser == 1) winUser = "black";
                else winUser = "white";
            }
            nowUser = -nowUser;
        }

        // change the text of whoPlay label
        if (nowUser == 1) whoPlay.setText("轮到黑方");
        else whoPlay.setText("轮到白方");

        this.repaint();

        if (win) {
            JOptionPane.showMessageDialog(this, winUser);
            try {
                IOProcessor.saveData(chessBoard, winUser, startTime, nowTime, costTime);
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(this, "保存文件发生错误");
                e1.printStackTrace();
            }
            restart();
        }

        this.repaint();
    }

    private void restart() {
        hasClick = false;
        timeTicker.stop();
        for (int i = 0; i <= hLineNum; i++)
            for (int j = 0; j <= vLineNum; j++)
                chessBoard[i][j] = 0;
    }

    private boolean checkWin(int x, int y, int nowUser) {

        /*k = 1 horizontal
        * k = 2 vertical
        * k = 3 left diagonal
        * k = 4 right diagonal*/

        for (int k = 1; k <= 4; k++) {
            int num = 1; // 连在一起的棋子数
            int p = 0, q = 0;
            switch (k) {
                case 1:
                    p = 1;
                    q = 0;
                    break;
                case 2:
                    p = 0;
                    q = 1;
                    break;
                case 3:
                    p = -1;
                    q = 1;
                    break;
                case 4:
                    p = 1;
                    q = 1;
                    break;
            }

            for (int i = 1; i <= 4; i++) {
                if (x + i * p > hLineNum + 1 || y + i * q > vLineNum + 1 || x + i * p < 0 || y + i * q < 0) break;
                if (chessBoard[x + i * p][y + i * q] != nowUser) break;
                num++;
            }
            p = -p;
            q = -q;
            for (int i = 1; i <= 4; i++) {
                if (x + i * p > hLineNum + 1 || y + i * q > vLineNum + 1 || x + i * p < 0 || y + i * q < 0) break;
                if (chessBoard[x + i * p][y + i * q] != nowUser) break;
                num++;
            }

            if (num == 5) return true;
        }

        return false;
    }

}
