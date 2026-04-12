package com.jorge_alan.spring_git_mvc.modelos;

import javax.swing.JPanel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaModelo {

    @Builder
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static final class EstructuraPanel {
        @Getter
        private String etiqueta;
        @Getter
        private JPanel actualComponente;
    }
}
