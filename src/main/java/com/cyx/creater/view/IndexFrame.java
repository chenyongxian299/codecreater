package com.cyx.creater.view;

import com.cyx.creater.Creater;
import com.cyx.creater.TemplateManagement;
import com.cyx.creater.bean.ColumnInfo;
import com.cyx.creater.bean.SchemataInfo;
import com.cyx.creater.bean.TableInfo;
import com.cyx.creater.dao.ColumnsDao;
import com.cyx.creater.dbhelper.BeetlSql;
import com.cyx.creater.dbhelper.IDataSource;
import com.cyx.creater.resource.FileTemplateReader;
import com.cyx.creater.resource.FileTemplateWriter;
import com.cyx.creater.resource.TemplateVariable;
import com.cyx.creater.resource.XmlTemplateVariable;
import com.cyx.creater.service.IColumnDescribe;
import com.cyx.creater.service.ISchemaDescribe;
import com.cyx.creater.service.imp.ColumnService;
import com.cyx.creater.service.imp.SchemaService;
import com.cyx.creater.service.ITableDescribe;
import com.cyx.creater.service.imp.TableService;
import com.cyx.creater.utils.FileUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.sql.core.*;
import org.jb2011.lnf.beautyeye.ch16_tree.BETreeUI;
import org.jb2011.lnf.beautyeye.ch2_tab.BETabbedPaneUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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

    private static String RIGHT_NODE_NAME = "";
    private JPanel plIndex;
    private JTree tree1;
    private JTabbedPane tabRight;

    private JPopupMenu pMenu = new JPopupMenu();
    private DialogJoin dialogJoin = new DialogJoin();

    private ISchemaDescribe schemaService;
    private ITableDescribe tableService;
    private IColumnDescribe columnService;

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

    public void initDatabaseInfo(IDataSource dataSource) {
        JMenu copyMenu = new JMenu("复制");
        JMenuItem mCopyTableName = new JMenuItem("复制表名");
        JMenuItem mCopyColumnName = new JMenuItem("复制列名");
        JMenuItem mCopyColumnsName = new JMenuItem("复制表下列名");
        copyMenu.add(mCopyTableName);
        copyMenu.add(mCopyColumnName);
        copyMenu.add(mCopyColumnsName);

        JMenu mFormat = new JMenu("格式化");
        JMenuItem mFormatColumns = new JMenuItem("格式化列名");
        mFormat.add(mFormatColumns);
        JMenuItem mItemPaste = new JMenuItem("打开tab");
        //JMenuItem mItemCut = new JMenuItem("剪切");
        mItemPaste.addActionListener(menuActionListener);
        mCopyColumnsName.addActionListener(menuActionListener);
        pMenu.add(copyMenu);
        pMenu.add(mItemPaste);
        pMenu.add(mFormat);
        tree1.add(pMenu);
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
        tree1.addMouseListener(ml);
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
                System.out.println(columnsName);
                try {
                    create();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    MouseListener ml = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {//只响应鼠标右键单击事件
                pMenu.show(tree1, e.getX(), e.getY());//在鼠标位置显示弹出式菜单
                int selRow = tree1.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree1.getPathForLocation(e.getX(), e.getY());
                if (selRow != -1) {
                    String childrenName = getChildrenName(selPath.getLastPathComponent());
                    System.out.println(childrenName);
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

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    private Creater.ICreateResult createResult = new Creater.ICreateResult() {
        @Override
        public void createSuccess() {
            System.err.println("---");
        }

        @Override
        public void createFail(Exception e) {
            System.err.println(e);
        }
    };

    private void create() throws FileNotFoundException {
        Creater creater = Creater.getInstance();
        TemplateVariable variable = new XmlTemplateVariable();
        creater.bindListener(createResult);
        List<File> fileList = FileUtil.eachFile(System.getProperty("user.dir") + File.separator + "template");
        for (File file : fileList) {
            FileTemplateReader reader = new FileTemplateReader(file.getAbsolutePath(), "utf8");
            FileTemplateWriter writer = new FileTemplateWriter(System.getProperty("user.dir") + File.separator + "template_r" + File.separator + file.getName(), "utf8");
            TemplateManagement management = new TemplateManagement(reader, writer, variable);
            creater.registTemplateManagement(file.getAbsolutePath(), management).start(file.getAbsolutePath());
        }
    }
}
