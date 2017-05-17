import javax.swing.*;
import java.awt.*;

/**
 * Created by 91632 on 2017/5/17 0017.
 */
public class Drawer {

    public static void drawBoard(Graphics g, int vLineNum, int hLineNum, int margin, double lineGap) {
        // draw board
        // vertical line
        for (int i = 0; i <= vLineNum; i++) {
            int x1 = 0 + margin;
            int y1 = (int) (i * lineGap + margin);
            int x2 = (int) (vLineNum * lineGap + margin);
            int y2 = (int) (i * lineGap + margin);
            g.drawLine(x1, y1, x2, y2);
        }

        // horizontal line
        for (int i = 0; i <= hLineNum; i++) {
            int x1 = (int) (i * lineGap + margin);
            int y1 = 0 + margin;
            int x2 = (int) (i * lineGap + margin);
            int y2 = (int) (hLineNum * lineGap + margin);
            g.drawLine(x1, y1, x2, y2);
        }

        // points
        int size = 8;
        int[] pos = {3, 12};
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                g.fillOval((int) (pos[i] * lineGap + margin - size / 2),
                        (int) (pos[j] * lineGap + margin - size / 2), size, size);
        g.fillOval((int) (7 * lineGap + margin - size / 2),
                (int) (7 * lineGap + margin - size / 2), size, size);
    }

    public static void drawChess(Graphics g, int hLineNum, int vLineNum, int[][] chessBoard, double lineGap, int margin, int chessSize, JPanel Panel) {

        String bSrc = "image\\black.png";
        String wSrc = "image\\white.png";
        Image blackChess = Toolkit.getDefaultToolkit().getImage(bSrc);
        Image whiteChess = Toolkit.getDefaultToolkit().getImage(wSrc);

        for (int i = 0; i <= hLineNum; i++)
            for (int j = 0; j <= vLineNum; j++) {


                int x = (int) (i * lineGap + margin - chessSize / 2);
                int y = (int) (j * lineGap + margin - chessSize / 2);

                if (chessBoard[i][j] == 0) continue;
                if (chessBoard[i][j] == 1) g.drawImage(blackChess, x, y, Panel);
                else g.drawImage(whiteChess, x, y, Panel);
            }
    }

}
