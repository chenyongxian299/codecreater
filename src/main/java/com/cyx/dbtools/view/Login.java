package com.cyx.dbtools.view;

import com.cyx.dbtools.bean.ConnectionParameter;
import com.cyx.dbtools.dbhelper.IConnection;
import com.cyx.dbtools.dbhelper.JDBConnection;
import javafx.embed.swing.JFXPanel;
import oracle.jrockit.jfr.JFR;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
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

    private static IndexFrame indexFrameObj = new IndexFrame();

    public Login() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnConn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSuccess()) {
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
                    indexFrameObj.initDatabaseInfo(parameter);
                }
            }
        });
        btnConnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isSuccess();
            }
        });
    }

    private boolean isSuccess() {
        String host = txtHost.getText();
        String port = txtPort.getText();
        String userName = txtUserName.getText();
        String password = new String(pwdPassword.getPassword());
        String schemaName = txtSchemaName.getText();
        if (schemaName.equals("")) {
            JOptionPane.showMessageDialog(null, "请输入数据库名称", "错误", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        host = "jdbc:mysql://" + host + ":" + port + "/" + schemaName + "?serverTimezone=GMT&characterEncoding=utf8&useUnicode=true&useSSL=false";
        parameter = new ConnectionParameter(host, userName, password);
        parameter.setSchemaName(schemaName);
        connection = JDBConnection.getInstance(parameter);
        if (connection.isConnected(parameter)) {
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
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            //TODO exception
        }
        loginFrame = new JFrame("code creater");
        loginFrame.setContentPane(new Login().plLogin);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

        initMenu();

        indexFrame = new JFrame("IndexFrame");
        indexFrame.setContentPane(indexFrameObj.getPlIndex());
        indexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        indexFrame.pack();
        indexFrame.setVisible(false);

    }
}
