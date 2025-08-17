package com.sistema.examenes.servicios;

import java.util.List;
import java.util.Set;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;

public interface UsuarioService {
	
	
	public Usuario guardarUsuario(Usuario usuario , Set<UsuarioRol> usuarioRoles) throws Exception;
	
	public Usuario obtenerUsuario(String username);
	
	public void eliminarUsuario(Long Id);
	
	List<Usuario> obtenerTodosLosUsuarios();
	
	// Nuevo método de búsqueda
    List<Usuario> buscarUsuariosPorNombre(String username);
    
     // Nuevo método para actualizar
    Usuario actualizarUsuario(Usuario usuario);

}
