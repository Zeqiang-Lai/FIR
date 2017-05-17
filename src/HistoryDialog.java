import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by 91632 on 2017/5/17 0017.
 */
public class HistoryDialog extends JDialog {

    //历史记录
    JList recordList = new JList();
    Vector<String> records = new Vector<>();
    Dictionary<String,File> dicRecords = new Hashtable<>();

    public HistoryDialog() {
        this.setTitle("历史记录");
        this.setSize(250, 350);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        getRecords();
        initialComponent();
    }

    private void getRecords() {

        File dataDir = new File(SettingIfo.fileName); // 数据文件夹
        String[] files = dataDir.list();
        for (int i = 0; i < files.length; i++) {
            File file = new File(dataDir, files[i]);
            String fileName = file.getName();
            records.add(fileName);
            dicRecords.put(fileName,file);
        }

    }

    private void initialComponent() {

        recordList.setListData(records);
        recordList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = recordList.getSelectedIndex();
                String fileName = records.get(index);
                if (index != -1) {
                    if (e.getClickCount() == 2) {
                        new HisContentDialog(dicRecords.get(fileName)).setVisible(true);
                    }
                }
            }
        });
        this.add(new JScrollPane(recordList));

    }

}
