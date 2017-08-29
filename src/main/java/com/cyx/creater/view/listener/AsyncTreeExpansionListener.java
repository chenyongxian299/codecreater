package com.cyx.creater.view.listener;

import com.cyx.creater.utils.ThreadUtil;
import com.sun.org.apache.bcel.internal.generic.IADD;

import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;

public class AsyncTreeExpansionListener implements TreeExpansionListener {
    public interface IAsyncExpansionListener{
        void treeExpanded(TreeExpansionEvent event);
        void treeCollapsed(TreeExpansionEvent event);
    }

    private IAsyncExpansionListener iAsyncExpansionListener;
    public AsyncTreeExpansionListener(IAsyncExpansionListener iAsyncExpansionListener){
        this.iAsyncExpansionListener = iAsyncExpansionListener;
    }
    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        ThreadUtil.executorService.execute(new Runnable() {
            @Override
            public void run() {
                iAsyncExpansionListener.treeExpanded(event);
            }
        });
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        ThreadUtil.executorService.execute(new Runnable() {
            @Override
            public void run() {
                iAsyncExpansionListener.treeCollapsed(event);
            }
        });
    }
}
