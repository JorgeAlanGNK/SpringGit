package com.jorge_alan.spring_git_mvc.datos.sql_extension.operators;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.datos.buffers.ConfigurationFactory;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.ExtensionQuery;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

public class ConsultaRepos implements UsuarioGitDB {

    private ExtensionQuery connectionBuilder;

    public ConsultaRepos(ConfigurationFactory factory) {
        this.connectionBuilder = new ExtensionQuery(factory);
    }

    @Override
    public CompletableFuture<GitToken> ObtenerTokenDatos(String token) {
        Supplier<GitToken> execute = () -> {
            GitToken temp = null;
            String query = "SELECT id_token, git_token, fecha_caducidad, url_repo, seleccionar_token, organizacion FROM GitRepositorios WHERE git_token = ?";
            try (Connection conn = connectionBuilder.Database();
                    PreparedStatement ps = connectionBuilder.SendProperties(conn, query,
                            connectionBuilder.FormatoColumnas(query));
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    temp = new GitToken();
                    temp.setId_token(rs.getInt("id_token"));
                    temp.setGit_token(rs.getString("git_token"));
                    temp.setFecha_caducidad(rs.getString("fecha_caducidad"));
                    temp.setUrl_repo(rs.getString("url_repo"));
                    temp.setSeleccionar_token(rs.getInt("seleccionar_token"));
                    temp.setOrganizacion(rs.getString("organizacion"));
                }
            } catch (Exception e) {
                connectionBuilder.FormatError(e, query);
            }
            return temp;
        };
        return CompletableFuture.supplyAsync(execute);
    }

    @Override
    public CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio) {
        String query = "INSERT INTO GitRepositorios" +
                "(git_nombre_local, git_nombre_url, es_activo, id_token, sesion_activa)" +
                "VALUES(?, ?, ?, ?, 1);";
        return CompletableFuture.supplyAsync(() -> {
            try (Connection conn = connectionBuilder.Database();
                    PreparedStatement ps = connectionBuilder.SendProperties(conn, query,
                            connectionBuilder.FormatoColumnas(query))) {
                boolean hayDatos = ps.executeUpdate() > 0;
                return hayDatos;
            } catch (Exception e) {
                connectionBuilder.FormatError(e, query);
            }
            return false;
        });
    }

    @Override
    public CompletableFuture<Integer> ListarReposActivos() {
        Supplier<Integer> taskAsync = () -> {
            String consultas = "SELECT COUNT(id_repositorio) AS cantidad_repositorio FROM GitRepositorios WHERE sesion_activa = 1";
            Integer cantidad = null;
            try (Connection conn = connectionBuilder.Database();
                    PreparedStatement ps = connectionBuilder.SendProperties(conn, consultas, null);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cantidad = rs.getInt("cantidad_repositorio");
                }
                return cantidad;
            } catch (Exception e) {
                connectionBuilder.FormatError(e, consultas);
                return 0;
            }
        };
        return CompletableFuture.supplyAsync(taskAsync);
    }

    @Override
    public CompletableFuture<List<GitRepositorio>> ActivarRepositorios() {
        Supplier<List<GitRepositorio>> taskAsync = () -> {
            List<GitRepositorio> resultQuery = Lists.newArrayList();
            String consultas = "SELECT id_repositorio, git_nombre_local, sesion_activa FROM GitRepositorios WHERE sesion_activa = 1";
            try (Connection conn = connectionBuilder.Database();
                    PreparedStatement ps = connectionBuilder.SendProperties(conn, consultas, connectionBuilder.FormatoColumnas(consultas));
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GitRepositorio tempModelo = new GitRepositorio();
                    tempModelo.setId_repositorio(rs.getInt("id_repositorio"));
                    tempModelo.setGit_nombre_local(rs.getString("git_nombre_local"));
                    tempModelo.setSesion_activa(rs.getInt("sesion_activa"));
                    resultQuery.add(tempModelo);
                }
                return resultQuery;
            } catch (Exception e) {
                connectionBuilder.FormatError(e, consultas);
                return Lists.newArrayList();
            }
        };
        return CompletableFuture.supplyAsync(taskAsync);
    }

    public CompletableFuture<GitRepositorio> ObtenerRuta(String rutaLocal) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "SELECT git_nombre_local, sesion_activa FROM GitRepositorios WHERE git_nombre_local = ?";
            GitRepositorio resultado = null;
            try (Connection conn = connectionBuilder.Database();
                    PreparedStatement ps = conn.prepareStatement(query);
                    ResultSet rs = ps.executeQuery()) {
                resultado = new GitRepositorio();
                while (rs.next()) {
                    resultado.setGit_nombre_local(rs.getString("git_nombre_local"));
                    resultado.setSesion_activa(rs.getInt("sesion_activa"));
                }
            } catch (Exception e) {
                connectionBuilder.FormatError(e, query);
            }
            return resultado;
        });
    }
}
