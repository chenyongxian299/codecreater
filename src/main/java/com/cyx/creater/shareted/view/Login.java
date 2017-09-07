package com.cyx.creater.shareted.view;

import com.cyx.creater.shareted.bean.ConnectionParameter;
import com.cyx.creater.shareted.bean.DbConfigInfo;
import com.cyx.creater.shareted.dbhelper.C3P0Connection;
import com.cyx.creater.shareted.dbhelper.IConnection;
import com.cyx.creater.shareted.dbhelper.IDataSource;
import com.cyx.creater.shareted.dbhelper.JDBConnection;
import com.cyx.creater.shareted.utils.XmlUtil;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch14_combox.BEComboBoxUI;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import sun.rmi.runtime.Log;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Login {
    private static JFrame loginFrame;
    private static JFrame indexFrame;

    private JPanel plLogin;
    private JButton btnConnTest;
    private JTextField txtHost;
    private JTextField txtPort;
    private JPasswordField pwdPassword;
    private JTextField txtSchemaName;
    private JButton btnConn;
    private JButton btnCancel;
    private JTextField txtUserName;
    private JComboBox cbxConnectionName;

    private ConnectionParameter parameter;
    private IConnection connection;

    private static IndexFrame indexFrameObj;

    public static String[] DEFAULT_FONT = new String[]{
            "Table.font"
            , "TableHeader.font"
            , "CheckBox.font"
            , "Tree.font"
            , "Viewport.font"
            , "ProgressBar.font"
            , "RadioButtonMenuItem.font"
            , "ToolBar.font"
            , "ColorChooser.font"
            , "ToggleButton.font"
            , "Panel.font"
            , "TextArea.font"
            , "Menu.font"
            , "TableHeader.font"
            // ,"TextField.font"
            , "OptionPane.font"
            , "MenuBar.font"
            , "Button.font"
            , "Label.font"
            , "PasswordField.font"
            , "ScrollPane.font"
            , "MenuItem.font"
            , "ToolTip.font"
            , "List.font"
            , "EditorPane.font"
            , "Table.font"
            , "TabbedPane.font"
            , "RadioButton.font"
            , "CheckBoxMenuItem.font"
            , "TextPane.font"
            , "PopupMenu.font"
            , "TitledBorder.font"
            , "ComboBox.font"
    };

    public Login() {
        btnConn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
        btnConn.setForeground(Color.white);
        btnCancel.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        btnConnTest.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        btnCancel.addActionListener((event) -> System.exit(0));
        btnConn.addActionListener((event) -> {
            if (isSuccess()) {
                loginFrame.setVisible(false);
                indexFrame.setVisible(true);
                String host = txtHost.getText();
                String port = txtPort.getText();
                String userName = txtUserName.getText();
                String password = new String(pwdPassword.getPassword());
                String schemaName = txtSchemaName.getText();
                saveDbInfo(login.cbxConnectionName.getSelectedItem().toString());
                host = "jdbc:mysql://" + host + ":" + port + "/" + schemaName + "?serverTimezone=GMT&characterEncoding=utf8&useUnicode=true&useSSL=false";
                parameter = new ConnectionParameter(host, userName, password);
                parameter.setSchemaName(schemaName);
                parameter.setMinPoolSize(3);
                IDataSource iDataSource = C3P0Connection.getInstance(parameter);
                indexFrameObj.initDatabaseInfo(iDataSource);
            }
        });
        btnConnTest.addActionListener((event) -> isSuccess());
    }

    private IConnection getConn() {
        String host = txtHost.getText();
        String port = txtPort.getText();
        String userName = txtUserName.getText();
        String password = new String(pwdPassword.getPassword());
        String schemaName = txtSchemaName.getText();
        if (schemaName.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入数据库名称", "错误", JOptionPane.PLAIN_MESSAGE);
            return null;
        }
        host = "jdbc:mysql://" + host + ":" + port + "/" + schemaName + "?serverTimezone=GMT&characterEncoding=utf8&useUnicode=true&useSSL=false";
        parameter = new ConnectionParameter(host, userName, password);
        parameter.setSchemaName(schemaName);
        connection = JDBConnection.getInstance(parameter);
        return connection;
    }

    private boolean isSuccess() {
        if (this.getConn().isConnected(parameter)) {
            JOptionPane.showMessageDialog(null, "连接成功", "成功", JOptionPane.PLAIN_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "连接失败", "错误", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }

    public static void initMenu() {
        JMenu jm = new JMenu("工具");     //创建JMenu菜单对象
        JMenuItem t1 = new JMenuItem("前端");  //菜单项
        JMenuItem t2 = new JMenuItem("后端");//菜单项
        jm.add(t1);   //将菜单项目添加到菜单
        jm.add(t2);    //将菜单项目添加到菜单
        JMenuBar br = new JMenuBar();  //创建菜单工具栏
        br.add(jm);      //将菜单增加到菜单工具栏
        loginFrame.setJMenuBar(br);  //为 窗体设置  菜单工具栏
    }

    public static void main(String[] args) {
        try {
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            FontUIResource font = new FontUIResource("微软雅黑", 0, 14); // 字体，样式（正常，斜体，加粗），字号
            for (int i = 0; i < DEFAULT_FONT.length; i++) {
                UIManager.put(DEFAULT_FONT[i], font);
            }
            UIManager.put("RootPane.setupButtonVisible", false);
            UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
        } catch (Exception e) {
            //TODO exception
        }
        initApp();

    }

    private ItemListener itemListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            Object obj = e.getItem();
            if (obj instanceof DbConfigInfo) {
                DbConfigInfo dbConfigInfo = (DbConfigInfo) obj;
                login.txtHost.setText(dbConfigInfo.getHost());
                login.txtPort.setText(String.valueOf(dbConfigInfo.getPort()));
                login.txtUserName.setText(dbConfigInfo.getUsername());
                login.pwdPassword.setText(dbConfigInfo.getPassword());
                login.txtSchemaName.setText(dbConfigInfo.getSchema());
            } else {
           /*     DbConfigInfo dbConfigInfo = new DbConfigInfo();
                dbConfigInfo.setConnectionName(obj.toString());*/
            }
        }
    };

    private void saveDbInfo(String connName) {
        // String connName = ((DbConfigInfo)cbxConnectionName.getSelectedItem()).getConnectionName();
        String host = txtHost.getText();
        String port = txtPort.getText();
        String userName = txtUserName.getText();
        String password = new String(pwdPassword.getPassword());
        String schemaName = txtSchemaName.getText();
        DbConfigInfo dbConfig = new DbConfigInfo();
        dbConfig.setConnectionName(connName);
        dbConfig.setHost(host);
        dbConfig.setPort(Integer.valueOf(port));
        dbConfig.setUsername(userName);
        dbConfig.setPassword(password);
        dbConfig.setSchema(schemaName);
        XmlUtil.writeDBConfig(dbConfig);
    }

    private static Login login = new Login();

    private static void initApp() {
        List<DbConfigInfo> dbConnList = XmlUtil.readDBConfig();
        login.cbxConnectionName.setEditable(true);
        login.cbxConnectionName.addItemListener(login.itemListener);
        login.cbxConnectionName.setUI(new BEComboBoxUI());
        for (DbConfigInfo dbConfigInfo : dbConnList) {
            login.cbxConnectionName.addItem(dbConfigInfo);
        }

        loginFrame = new JFrame("code creater");
        loginFrame.setContentPane(login.plLogin);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

        initMenu();

        indexFrameObj = new IndexFrame();
        indexFrame = new JFrame("IndexFrame");
        indexFrame.setContentPane(indexFrameObj.getPlIndex());
        indexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        indexFrame.pack();
        indexFrame.setVisible(false);
    }
}
