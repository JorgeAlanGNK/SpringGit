package com.jorge_alan.spring_git_mvc.modelos;

import javax.swing.JPanel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaModelo {

    //las estructuras pertenecen a paneles y operaciones de componentes
    @Builder
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    public static final class EstructuraPanel {
        @Getter private String etiqueta;
        @Getter private JPanel actualCollapse;
        @Getter @Setter private boolean esVisible;
    }

    @NoArgsConstructor
    public static final class RamaModelo {
        @Getter @Setter private String nombreRama;
        @Getter @Setter private List<String> Carpeta;
        @Getter @Setter private boolean carpeta;
        @Getter @Setter private boolean origin;
    }

    @NoArgsConstructor
    public static class StashModelo {
       @Getter @Setter private List<String> stashes;
    }
}
