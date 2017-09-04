package com.cyx.creater.view;

import com.cyx.creater.bean.ConnectionParameter;
import com.cyx.creater.dbhelper.C3P0Connection;
import com.cyx.creater.dbhelper.IConnection;
import com.cyx.creater.dbhelper.IDataSource;
import com.cyx.creater.dbhelper.JDBConnection;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch16_tree.BETreeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private ConnectionParameter parameter;
    private IConnection connection;

    private static IndexFrame indexFrameObj;

    public Login() {
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
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            UIManager.put("TabbedPane.tabAreaInsets"
                    , new javax.swing.plaf.InsetsUIResource(3, 20, 2, 20));
        } catch (Exception e) {
            //TODO exception
        }
        initApp();
    }

    private static void initApp() {
        loginFrame = new JFrame("code creater");
        loginFrame.setContentPane(new Login().plLogin);
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
