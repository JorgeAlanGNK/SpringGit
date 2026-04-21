package com.jorge_alan.spring_git_mvc.modelos;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaModeloNegocio {
    
    @NoArgsConstructor
    @Data
    public static final class VisualizacionModelo {
        private List<CapaModelo.StashModelo> stashes = Lists.newArrayList();
        private List<CapaModelo.RamaModelo> ramas = Lists.newArrayList();
        private List<CapaModelo.RamaModelo> remotos = Lists.newArrayList();
    }
    
    
}
