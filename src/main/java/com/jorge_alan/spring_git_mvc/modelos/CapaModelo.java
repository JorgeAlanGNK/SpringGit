package com.jorge_alan.spring_git_mvc.modelos;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaModelo {

    // las estructuras pertenecen a paneles y operaciones de componentes
    
    @Builder
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor
    public static final class EstructuraNavegador {
        @Getter @Setter
        public Map<String, List<String>> navegaciones;
    }

    //la nomenclatura modelo funciona para realizar operaciones JPanel
    //falta realizar información detallada
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static final class RamaModelo {
        @Getter @Setter
        private String nombreRama;
        @Getter @Setter
        private List<String> carpetas;
        
        @Getter @Setter
        private boolean carpeta;
        @Getter @Setter
        private boolean origin;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static final class StashModelo {
        private String stash;
        @Getter @Setter
        private int indexStash;
    }
}
