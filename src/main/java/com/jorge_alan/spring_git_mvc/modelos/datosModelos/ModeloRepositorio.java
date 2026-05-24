package com.jorge_alan.spring_git_mvc.modelos.datosModelos;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import java.util.List;
import java.util.Set;

public class ModeloRepositorio {

    // datos coleccionados para la base de datos
    // estos incluyen de igual manera para el cmd
    private Set<String> repositorios;
    private Set<RamaModelo> ramasLocales;
    private Set<RamaModelo> ramasRemotas;
    private List<StashModelo> stashes;
    private Set<RemotoModelo> remotosUrl;

    // repositorio seleccionado para mostrar
    private EstadoSituacion situacion;
    private String repositorioActual;
    private boolean activo;

    public ModeloRepositorio() {
        this.repositorios = Sets.newHashSet();
        this.ramasLocales = Sets.newHashSet();
        this.ramasRemotas = Sets.newHashSet();
        this.stashes = Lists.newArrayList();
        this.remotosUrl = Sets.newHashSet();
        this.situacion = new EstadoSituacion();
        this.repositorioActual = "";
        this.activo = false;
    }

    public ModeloRepositorio(EstadoSituacion situacion) {
        this.situacion = situacion;
    }

    public Set<String> getRepositorios() {
        return repositorios;
    }

    public void setRepositorios(Set<String> repositorios) {
        this.repositorios = repositorios;
    }

    public Set<RamaModelo> getRamasLocales() {
        return ramasLocales;
    }

    public void setRamasLocales(Set<RamaModelo> ramasLocales) {
        this.ramasLocales = ramasLocales;
    }

    public Set<RamaModelo> getRamasRemotas() {
        return ramasRemotas;
    }

    public void setRamasRemotas(Set<RamaModelo> ramasRemotas) {
        this.ramasRemotas = ramasRemotas;
    }

    public List<StashModelo> getStashes() {
        return stashes;
    }

    public void setStashes(List<StashModelo> stashes) {
        this.stashes = stashes;
    }

    public String getRepositorioActual() {
        return repositorioActual;
    }

    public void setRepositorioActual(String repositorioActual) {
        this.repositorioActual = repositorioActual;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public EstadoSituacion getSituacion() {
        return situacion;
    }

    public Set<RemotoModelo> getRemotosUrl() {
        return remotosUrl;
    }

    public void setRemotosUrl(Set<RemotoModelo> remotosUrl) {
        this.remotosUrl = remotosUrl;
    }

}
