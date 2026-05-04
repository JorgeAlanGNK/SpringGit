package com.jorge_alan.spring_git_mvc.datos;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ConsultaRepos implements UsuarioGitDB {

    private final String fileSqlite;

    public ConsultaRepos() {
        this.fileSqlite = "jdbc:sqlite:C:\\ProyectosTemp\\springMVCGit\\base de datos\\gitUserDB.db";
    }

    private Connection Database() throws SQLException, Exception {
        return DriverManager.getConnection(this.fileSqlite);
    }

    @Override
    public List<GitToken> ObtenerTokensActivos() {
        List<GitToken> resultado = Lists.newArrayList();
        try (Connection conn = Database(); PreparedStatement consultaTokens = conn.prepareStatement("SELECT id_token, token_ref, fecha_caducidad, descripcion, nombre_repo FROM GitToken WHERE seleccionar_token = 1"); ResultSet filasToken = consultaTokens.executeQuery();) {
            for (;filasToken.next();) {
                GitToken tokenTemp = new GitToken();
                tokenTemp.setId_token(filasToken.getInt(1));
                tokenTemp.setToken_ref(filasToken.getString(2));
                tokenTemp.setFecha_caducidad(filasToken.getDate(3));
                tokenTemp.setDescripcion(filasToken.getString(4));
                tokenTemp.setNombre_repo(filasToken.getString(5));
                resultado.add(tokenTemp);
            }
        } catch (Exception ex) {
            System.getLogger(ConsultaRepos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return resultado;
    }

    @Override
    public GitToken ObtenerTokenDatos(String token) {
        GitToken temp = new GitToken();
        try (Connection conn = Database(); PreparedStatement consultaToken = conn.prepareStatement(""); ResultSet tokenSet = consultaToken.executeQuery())
        {
            
        } catch (Exception e) {
        }
        return temp;
    }

}
