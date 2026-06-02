package com.jorge_alan.spring_git_mvc.componentes.customs;

import com.jorge_alan.spring_git_mvc.componentes.extension.IconoExtension;
import com.jorge_alan.spring_git_mvc.componentes.extension.IconoExtensionImpl;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstanteIcono;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CampoEdicion extends JTextField {

    private int round;
    private final Color bordeInferior = new Color(68, 170, 238);
    private final Color sourceBackground = getBackground();
    private final Color borderSource = Color.BLACK;
    private Color filterBackGround;
    private float sizeFont;
    //configuracion para el hintText
    private String hintText;
    private final Color hintColorText = new Color(170, 170, 170);
    private boolean hayFocus;
    //configuracion para el raton
    private boolean ratonFocus;

    //valores para verificar si hay un error u otro tipo de dato
    private boolean hayError;
    private boolean hayExito;
    private final Color bordeError = new Color(255, 0, 82);
    private final Color bordeExito = new Color(111, 175, 79);
    private IconoExtension icono = new IconoExtensionImpl();
    //iconos
    private Icon iconoError;
    private Icon iconoObligatorio;
    private Icon iconoExito;

    public CampoEdicion() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        addFocusListener(FocusOption());
        addMouseListener(MouseOption());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Map<Key, Object> hints = new HashMap();
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int width = getWidth();
        int heigth = getHeight();
        //generar un textfield redondo
        RoundRectangle2D areaSource = new RoundRectangle2D.Double(0, 0, width, heigth, round, round);
        g2d.setColor(Color.WHITE);
        g2d.fill(areaSource);
        super.paintComponent(g2d);
        //linea bordeado para abajo
        Rectangle2D rectInferior = new Rectangle2D.Double(0, heigth - 2, width, heigth - 2);
        ComprobarIcono(g2d, width, heigth);
        g2d.fill(rectInferior);
        //se dibuja la letra para el hint
        if (!hayFocus && getText().isEmpty()) {
            HintText(g2d);
        }
        g2d.dispose();
    }

    //Configuraciones
    //Eventos privados
    private FocusAdapter FocusOption() {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                hayFocus = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                hayFocus = false;
                hayError = false;
                hayExito = false;
                repaint();
            }
        };
    }

    private MouseAdapter MouseOption() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ratonFocus = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ratonFocus = false;
                repaint();
            }

        };
    }

    private void HintText(Graphics2D g2d) {
        Insets padding = getInsets();
        g2d.setColor(hintColorText);
        FontMetrics fm = g2d.getFontMetrics();
        if (hintText != null) {
            Rectangle2D textRect = fm.getStringBounds(hintText, g2d);
            double yo = getHeight() - padding.top - padding.bottom;
            g2d.drawString(hintText, padding.right, getHeight() - padding.bottom * 2);
        }
    }

    private void ComprobarIcono(Graphics2D g2d, int w, int h) {
        int margin_pix = 5;
        Image imagen_temp = null;
        int width_image = -1;
        int right_margin = 0;
        //todo el padding sera para lo visual del padding derecha
        if (hayFocus && hayError && !getText().isEmpty()) {//dibujar el icono dependiendo del estado
            imagen_temp = ((ImageIcon) icono.MostrarIconoDesktop(ConstanteIcono.ICONO_INFORMATION_TEXT_ERROR, 20, 20)).getImage();
            width_image = imagen_temp.getWidth(this);
            g2d.setColor(bordeError);
        } else if (hayFocus || !getText().isEmpty()) {
            imagen_temp = ((ImageIcon) icono.MostrarIconoDesktop(ConstanteIcono.ICONO_INFORMATION_TEXT_OBLIGATORY, 20, 20)).getImage();
            width_image = imagen_temp.getWidth(this);
            g2d.setColor(bordeInferior);
        } else if (hayFocus && hayExito && !getText().isEmpty()) {
            imagen_temp = ((ImageIcon) icono.MostrarIconoDesktop(ConstanteIcono.ICONO_INFORMATION_TEXT_SUCCESS, 20, 20)).getImage();
            width_image = imagen_temp.getWidth(this);
            g2d.setColor(bordeExito);
        } else {
            imagen_temp = ((ImageIcon) icono.MostrarIconoDesktop(ConstanteIcono.ICONO_INFORMATION_TEXT_OBLIGATORY, 20, 20)).getImage();
            width_image = imagen_temp.getWidth(this);
            g2d.setColor(borderSource);
        }
        int heigth_image = h / 2 - imagen_temp.getHeight(this) + margin_pix * 2;
        right_margin = imagen_temp.getWidth(this);
        g2d.drawImage(imagen_temp, w - width_image, heigth_image, this);
        setBorder(new EmptyBorder(10, 10, 10, right_margin));
    }

    //propiedades para el JTextField
    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public float getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(float sizeFont) {
        this.sizeFont = sizeFont;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public boolean isHayError() {
        return hayError;
    }

    public void setHayError(boolean hayError) {
        this.hayError = hayError;
    }

    public boolean isHayExito() {
        return hayExito;
    }

    public void setHayExito(boolean hayExito) {
        this.hayExito = hayExito;
    }

    public Icon getIconoError() {
        return iconoError;
    }

    public void setIconoError(Icon iconoError) {
        this.iconoError = iconoError;
    }

    public Icon getIconoObligatorio() {
        return iconoObligatorio;
    }

    public void setIconoObligatorio(Icon iconoObligatorio) {
        this.iconoObligatorio = iconoObligatorio;
    }

    public Icon getIconoExito() {
        return iconoExito;
    }

    public void setIconoExito(Icon iconoExito) {
        this.iconoExito = iconoExito;
    }

    public Color getFilterBackGround() {
        return filterBackGround;
    }

    public void setFilterBackGround(Color filterBackGround) {
        this.filterBackGround = filterBackGround;
    }

}
