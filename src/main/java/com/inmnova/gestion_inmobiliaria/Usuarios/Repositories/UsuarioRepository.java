package com.inmnova.gestion_inmobiliaria.Usuarios.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.inmnova.gestion_inmobiliaria.Usuarios.Model.User;
@Repository
public class UsuarioRepository {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<User> getUserNames() {
        return jdbcTemplate.queryForList("Select nombre from user",User.class);        
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("Select id, nombre, apellido from user", new BeanPropertyRowMapper<User>(User.class));
    }

    public User getUserById(int id) {
        String sql = "Select * from user Where id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    public User updateUser(int id, User user) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(
            "UPDATE user set nombre = ?, apellido = ?, cedula = ?, fecha_de_nacimiento = ?, estado_civil = ?, direccion = ?, telefono = ?, correo_electronico = ? where id = ? ")) {
                //SET
                preparedStatement.setString(1, user.getNombre());
                preparedStatement.setString(2, user.getApellido());
                preparedStatement.setString(3, user.getCedula());
                preparedStatement.setDate(4, new java.sql.Date(user.getFechaNacimiento().getTime()));
                preparedStatement.setString(5, user.getEstadoCivil());
                preparedStatement.setString(6, user.getDireccion());
                preparedStatement.setString(7, user.getTelefono());
                preparedStatement.setString(8, user.getEmail());
                //WHERE
                preparedStatement.setString(9, String.valueOf(id));
                //UPDATE
                preparedStatement.executeUpdate();
                return user;
        }
    }

    public void deleteUser(int id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = conn.prepareStatement(
            "UPDATE user set nombre = ? where id = ? ")) {
                //SET
                preparedStatement.setString(1, "borrado");
                //WHERE
                preparedStatement.setString(2, String.valueOf(id));
                //UPDATE
                preparedStatement.executeUpdate();
        }
    }
}