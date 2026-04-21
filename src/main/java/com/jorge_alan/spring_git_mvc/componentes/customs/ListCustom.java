package com.jorge_alan.spring_git_mvc.componentes.customs;

import javax.swing.AbstractCellEditor;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class ListCustom extends JTree {
    
    public ListCustom() {
        setOpaque(false);
        setDragEnabled(false);
        add(new JScrollPane(this));
    }
}

