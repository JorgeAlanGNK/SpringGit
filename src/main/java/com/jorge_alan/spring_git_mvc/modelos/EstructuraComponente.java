package com.jorge_alan.spring_git_mvc.modelos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.EstructuraNavegador;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EstructuraComponente {
    
    @NoArgsConstructor
    public static final class EstructuraNavegacion {
        
        @Getter
        private static final EstructuraNavegador navegantes = IniciarNavegacion();

        private static EstructuraNavegador IniciarNavegacion() {
            Map<String, List<String>> iniciarNavegacion = new HashMap<>();
            iniciarNavegacion.put("Archivos", Lists.newArrayList("Generar nuevo repositorio...", "Buscar repositorio existente..."));
            return EstructuraNavegador.builder().navegaciones(iniciarNavegacion).build();
        }

        public Map<String, List<String>> getNavegaciones() {
            return getNavegantes().getNavegaciones();
        }

        public List<String> getSubItems(String nombreNavegacion) {
            return getNavegantes().getNavegaciones().get(nombreNavegacion);
        }

    }
}
