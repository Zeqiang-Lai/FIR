import java.io.Serializable;
import java.util.Date;

/**
 * Created by 91632 on 2017/5/17 0017.
 */

/*历史记录文件信息*/

public class RecordData implements Serializable{

    Date startTime; // 游戏开始时间
    Date endTime;   // 游戏结束时间
    String costTime;

    String winner;  // 赢家
    int[][] chessBoard; // 棋盘

    public RecordData(int[][] chessBoard, String winer, Date startTime, Date endTime, String costTime) {
        this.chessBoard = chessBoard;
        this.winner = winer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.costTime = costTime;
    }
}
