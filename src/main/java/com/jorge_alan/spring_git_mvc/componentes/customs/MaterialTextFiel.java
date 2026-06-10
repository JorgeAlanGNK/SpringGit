package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Font;
import java.text.AttributedCharacterIterator.Attribute;
import java.awt.font.TextAttribute;
import java.util.Map;


import javax.swing.JTextField;

import com.google.common.collect.Maps;



public class MaterialTextFiel extends JTextField {

    private String nameFont;
    private int typeFont;
    private int sizeFont;

    public String getNameFont() {
        return nameFont;
    }

    public void setNameFont(String nameFont) {
        this.nameFont = nameFont;
    }

    public int getTypeFont() {
        return typeFont;
    }

    public void setTypeFont(int typeFont) {
        this.typeFont = typeFont;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    @Override
    public Font getFont() {
        Map<Attribute, Object> nuevo = Maps.newHashMap();
        nuevo.put(TextAttribute.FONT, nameFont);
        nuevo.put(TextAttribute.SIZE, sizeFont);
        super.getFont();
        return new Font(nuevo);
    }

}
