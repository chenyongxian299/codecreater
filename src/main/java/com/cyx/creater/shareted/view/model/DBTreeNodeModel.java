package com.cyx.creater.shareted.view.model;

import javax.swing.*;

public class DBTreeNodeModel {
    private ImageIcon icon;

    private Object nodeObject;

    public DBTreeNodeModel(ImageIcon icon, Object nodeObject) {
        this.icon = icon;
        this.nodeObject = nodeObject;
    }

    public Object getNodeObject() {
        return nodeObject;
    }

    public void setNodeObject(Object nodeObject) {
        this.nodeObject = nodeObject;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return nodeObject == null ? "" : nodeObject.toString();
    }
}
