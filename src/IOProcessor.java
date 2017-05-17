import javax.swing.*;
import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 91632 on 2017/5/16 0016.
 */
public class IOProcessor {

    public static String fileName = "data\\";

    public static void saveData(int[][] chessBoard, String winer, Date startTime, Date nowTime, String costTime) throws IOException {

        Date now = new Date();
        Format format = new SimpleDateFormat("yyyy-MM-dd--HHmmss");
        String nowDate = format.format(now);
        fileName += nowDate;
        System.out.println(fileName);

        RecordData data = new RecordData(chessBoard, winer, startTime, nowTime, costTime);

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(data);
        out.close();

    }

    public static RecordData readData(File file) throws IOException, ClassNotFoundException {

        RecordData data = null;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        data = (RecordData) in.readObject();

        return data;
    }
}
