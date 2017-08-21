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
                loginFrame.setVisible(false);
                indexFrame.setVisible(true);
            }
        });
        btnConnTest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host = txtHost.getText();
                String port = txtPort.getText();
                String userName = txtUserName.getText();
                String password = new String(pwdPassword.getPassword());
                String schemaName = txtSchemaName.getText();
                host = "jdbc:mysql://" + host + ":" + port + "/" + schemaName + "?serverTimezone=GMT&characterEncoding=utf8&useUnicode=true&useSSL=false";
                parameter = new ConnectionParameter(host, userName, password);
                parameter.setSchemaName(schemaName);
                connection = JDBConnection.getInstance(parameter);
                if (connection.isConnected(parameter)) {
                    JOptionPane.showMessageDialog(null, "连接成功", "成功", JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "连接失败", "错误", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            //TODO exception
        }
        loginFrame = new JFrame("小猪快跑");
        loginFrame.setContentPane(new Login().plLogin);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

        indexFrame = new JFrame("IndexFrame");
        indexFrame.setContentPane(new IndexFrame().getPlIndex());
        indexFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        indexFrame.pack();
        indexFrame.setVisible(false);
    }
}
