package com.cyx.creater.shareted.view;

import com.cyx.creater.code.Creater;
import com.cyx.creater.code.TemplateManagement;
import com.cyx.creater.shareted.bean.ColumnInfo;
import com.cyx.creater.shareted.bean.SchemataInfo;
import com.cyx.creater.shareted.bean.TableInfo;
import com.cyx.creater.shareted.dbhelper.BeetlSql;
import com.cyx.creater.shareted.dbhelper.IDataSource;
import com.cyx.creater.code.resource.FileTemplateReader;
import com.cyx.creater.code.resource.FileTemplateWriter;
import com.cyx.creater.code.resource.ITemplateVariable;
import com.cyx.creater.code.resource.XmlTemplateVariable;
import com.cyx.creater.shareted.service.IColumnDescribe;
import com.cyx.creater.shareted.service.ISchemaDescribe;
import com.cyx.creater.shareted.service.imp.ColumnService;
import com.cyx.creater.shareted.service.imp.SchemaService;
import com.cyx.creater.shareted.service.ITableDescribe;
import com.cyx.creater.shareted.service.imp.TableService;
import com.cyx.creater.shareted.view.model.DBTableModel;
import com.cyx.creater.shareted.view.model.bean.TableColumn;
import com.cyx.creater.utils.Console;
import com.cyx.creater.utils.FileUtil;
import com.cyx.creater.utils.SystemUtil;
import javafx.scene.control.Tab;
import org.beetl.sql.core.*;
import org.dom4j.rule.Mode;
import org.jb2011.lnf.beautyeye.ch16_tree.BETreeUI;
import org.jb2011.lnf.beautyeye.ch2_tab.BETabbedPaneUI;
import org.jb2011.lnf.beautyeye.ch4_scroll.BEScrollPaneUI;
import org.jb2011.lnf.beautyeye.ch5_table.BETableUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndexFrame {
    private enum NodeType {
        Root,
        Schema,
        Table,
        Column
    }

    /*private enum TreeMenuAccess {
        Children, Parents, ALL
    }

    private enum DBTreeMenuCategory {
        //Schema, Table, Column, Field;
        private TreeMenuAccess treeMenuAccess;

        private DBTreeMenuCategory(TreeMenuAccess treeMenuAccess) {
            this.treeMenuAccess = treeMenuAccess;
        }

    }
*/
    private static String RIGHT_NODE_NAME = "";
    private JPanel plIndex;
    private JTree tree1;
    private JTabbedPane tabRight;
    private JToolBar tbrMenu;
    private JTabbedPane tabLeft;
    private JScrollPane scrollbar;

    private JPopupMenu pMenu = new JPopupMenu();
    private DialogJoin dialogJoin = new DialogJoin();

    private ISchemaDescribe schemaService;
    private ITableDescribe tableService;
    private IColumnDescribe columnService;

    private Map<String, List<MenuElement>> menuMap = new HashMap<>();

    public IndexFrame() {
        tree1.setUI(new BETreeUI());
        tabRight.setUI(new BETabbedPaneUI());
    }

    public JPanel getPlIndex() {
        return plIndex;
    }

    public void setPlIndex(JPanel plIndex) {
        this.plIndex = plIndex;
    }

    public JTree getTree1() {
        return tree1;
    }

    public void setTree1(JTree tree1) {
        this.tree1 = tree1;
    }

    private void initRightClickMenu() {
        JMenu mCopyMenu = new JMenu("复制");
        JMenuItem mCopyTableName = new JMenuItem("复制表名");
        JMenuItem mCopyColumnName = new JMenuItem("复制列名");
        JMenuItem mCopyColumnsName = new JMenuItem("复制表下列名");
        mCopyMenu.add(mCopyTableName);
        mCopyMenu.add(mCopyColumnName);
        mCopyMenu.add(mCopyColumnsName);
        JMenuItem mGenerateMenu = new JMenuItem("生成");
        JMenu mFormat = new JMenu("格式化");
        JMenuItem mFormatColumns = new JMenuItem("格式化列名");
        mFormat.add(mFormatColumns);
        JMenuItem mShowTableInfo = new JMenuItem("查看表详情");
        JMenuItem mOpenTab = new JMenuItem("打开tab");
        mOpenTab.addActionListener(menuActionListener);
        mCopyColumnsName.addActionListener(menuActionListener);
        mGenerateMenu.addActionListener(menuActionListener);
        mShowTableInfo.addActionListener(menuActionListener);
        pMenu.add(mCopyMenu);
        pMenu.add(mOpenTab);
        pMenu.add(mFormat);
        pMenu.add(mGenerateMenu);
        pMenu.add(mShowTableInfo);
        tree1.add(pMenu);
        List<MenuElement> menuList = new ArrayList<>();
        menuList.add(mCopyMenu);
        menuList.add(mOpenTab);
        menuList.add(mFormat);
    }

    public void initDatabaseInfo(IDataSource dataSource) {
        initRightClickMenu();
        tree1.setScrollsOnExpand(true);
        BeetlSql beetlSql = BeetlSql.getInstance();
        SQLManager sqlManager = beetlSql.registerSqlManager("/sql/mysql", dataSource);
        schemaService = new SchemaService(sqlManager);
        tableService = new TableService(sqlManager);
        columnService = new ColumnService(sqlManager);
        List<SchemataInfo> schemataInfoList = schemaService.getSchemataDescribe();
        new Thread(() -> {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库");
            for (SchemataInfo schemataInfo : schemataInfoList) {
                DefaultMutableTreeNode schemataNode = new DefaultMutableTreeNode(schemataInfo);
                root.add(schemataNode);
            }
            tree1.setModel(new DefaultTreeModel(root));
        }).start();
        tree1.addMouseListener(rightClickMenuAdapter);
    }

    ActionListener menuActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("打开弹窗")) {
                // dialogJoin.setVisible(true);
            } else if (e.getActionCommand().equals("打开tab")) {
                JPanel jPanel = new JPanel();
                tabRight.add(jPanel, "新建-");
            } else if ("复制表下列名".equals(e.getActionCommand())) {
                String columnsName = columnService.getColumnsNameStr(RIGHT_NODE_NAME);
                List<ColumnInfo> columnInfos = columnService.getColumnsDescribe(RIGHT_NODE_NAME);
                SystemUtil.setClipbordContents(columnsName);
            } else if ("生成".equals(e.getActionCommand())) {
                try {
                    create();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if ("查看表详情".equals(e.getActionCommand())) {
                JScrollPane tabPane = new JScrollPane();
                tabPane.setUI(new BEScrollPaneUI());
                JTable jTable = new JTable();
                jTable.setUI(new BETableUI());
                List<TableColumn> tableColumns = new ArrayList<>();
                List<ColumnInfo> columnInfos = columnService.getColumnsDescribe(RIGHT_NODE_NAME);
                for (ColumnInfo columnInfo : columnInfos) {
                    TableColumn tableColumn = new TableColumn();
                    tableColumn.setColumnName(columnInfo.getColumnName());
                    tableColumn.setPrimaryKey("PRI".equals(columnInfo.getColumnKey()));
                    tableColumns.add(tableColumn);
                }
                DBTableModel dbTableModel = new DBTableModel(tableColumns);
                jTable.setModel(dbTableModel);
                jTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                tabPane.setViewportView(jTable);
                tabRight.add(tabPane, "详情");
            }
        }
    };

    MouseListener rightClickMenuAdapter = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {//只响应鼠标右键单击事件
                pMenu.show(tree1, e.getX(), e.getY());//在鼠标位置
                // 显示弹出式菜单
                int selRow = tree1.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree1.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {

                }
            } else {
                int selRow = tree1.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree1.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    IndexFrame.RIGHT_NODE_NAME = getNodeName(selPath.getLastPathComponent());
                    if (e.getClickCount() == 1) {
                        //mySingleClick(selRow, selPath);
                    } else if (e.getClickCount() == 2) {
                        loadData(selPath.getLastPathComponent());
                    }
                }
            }
        }
    };

    private String getNodeName(Object object) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) object;
        Object dataObj = defaultMutableTreeNode.getUserObject();
        return object.toString();
    }

    private NodeType getNodeType(Object object) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) object;
        Object dataObj = defaultMutableTreeNode.getUserObject();
        String childrenName = null;
        if (dataObj instanceof SchemataInfo) {
            return NodeType.Schema;
        } else if (dataObj instanceof TableInfo) {
            return NodeType.Table;
        } else if (dataObj instanceof ColumnInfo) {
            return NodeType.Column;
        } else {
            return NodeType.Root;
        }
    }

    private void showMenuItem(Object obj) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) obj;
        Object dataObj = defaultMutableTreeNode.getUserObject();
        if (dataObj instanceof SchemataInfo) {

        } else if (dataObj instanceof TableInfo) {

        } else if (dataObj instanceof ColumnInfo) {

        } else {

        }
    }

    private String getChildrenName(Object object) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) object;
        Object dataObj = defaultMutableTreeNode.getUserObject();
        String childrenName = null;
        if (dataObj instanceof SchemataInfo) {
            childrenName = tableService.getTablesNameStr(((SchemataInfo) dataObj).getSchemaName());
        } else if (dataObj instanceof TableInfo) {
            childrenName = columnService.getColumnsNameStr(((TableInfo) dataObj).getTableName());
        } else if (dataObj instanceof ColumnInfo) {

        }
        return childrenName;
    }


    private void loadData(Object object) {
        DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) object;
        Object dataObj = defaultMutableTreeNode.getUserObject();
        if (dataObj instanceof SchemataInfo) {
            List<TableInfo> tableInfos = tableService.getTablesDescribe(((SchemataInfo) dataObj).getSchemaName());
            for (TableInfo tableInfo : tableInfos) {
                DefaultMutableTreeNode tablesNode = new DefaultMutableTreeNode(tableInfo);
                defaultMutableTreeNode.add(tablesNode);
            }
        } else if (dataObj instanceof TableInfo) {
            List<ColumnInfo> columnInfos = columnService.getColumnsDescribe(((TableInfo) dataObj).getTableName());
            for (ColumnInfo columnInfo : columnInfos) {
                DefaultMutableTreeNode tablesNode = new DefaultMutableTreeNode(columnInfo);
                defaultMutableTreeNode.add(tablesNode);
            }
        } else if (dataObj instanceof ColumnInfo) {

        }
    }

    private Creater.ICreateResult createResult = new Creater.ICreateResult() {
        @Override
        public void createSuccess() {
            Console.log("创建成功");
        }

        @Override
        public void createFail(Exception e) {
            Console.log("创建失败");
        }
    };

    private void create() throws FileNotFoundException {
        Creater creater = Creater.getInstance();
        String xmlVariablePath = System.getProperty("user.dir") + File.separator + "appData" + File.separator + "template_var.xml";
        ITemplateVariable variable = new XmlTemplateVariable(xmlVariablePath);
        variable.putVariable("tableName", "iods_order_property");
        variable.putVariable("webRootDirRelative", "../../../");
        creater.bindListener(createResult);
        String templateDir = System.getProperty("user.dir") + File.separator + "template";
        List<File> fileList = FileUtil.eachFile(templateDir);
        for (File file : fileList) {
            FileTemplateReader reader = new FileTemplateReader(file.getAbsolutePath(), "utf8");
            FileTemplateWriter writer = new FileTemplateWriter(System.getProperty("user.dir") + File.separator + "template_r" + FileUtil.getEndPath(templateDir, file.getPath()), "utf8");
            TemplateManagement management = new TemplateManagement(reader, writer, variable);
            creater.registTemplateManagement(file.getAbsolutePath(), management);
        }
        creater.startAsync();
    }
}
