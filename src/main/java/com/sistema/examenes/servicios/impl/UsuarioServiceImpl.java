package com.sistema.examenes.servicios.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.excepciones.UsuarioFoundException;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Autowired
	private RolRepository rolRepository;


	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
		Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal != null) {
			System.out.println("El usuario ya existe");
			throw new UsuarioFoundException ("El usuario ya está presente");
		}
		else {
			for(UsuarioRol usuarioRol:usuarioRoles) {
				rolRepository.save(usuarioRol.getRol());				
			}
			usuario.getUsuariosRoles().addAll(usuarioRoles);
			usuarioLocal = usuarioRepository.save(usuario);
		}
		return usuarioLocal;
	}


	@Override
	public Usuario obtenerUsuario(String username) {
			return usuarioRepository.findByUsername(username);
	}


	@Override
    public void eliminarUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }
	
	@Override
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll(); // Retorna la lista completa de usuarios
    }
	
	@Override
    public List<Usuario> buscarUsuariosPorNombre(String username) {
        return usuarioRepository.findByUsernameContaining(username);
    }
	
	// para actualizar
	@Override
    public Usuario actualizarUsuario(Usuario usuario) {
        Usuario existente = usuarioRepository.findById(usuario.getId()).orElse(null);
        if (existente != null) {
            existente.setUsername(usuario.getUsername());
            existente.setNombre(usuario.getNombre());
            existente.setApellido(usuario.getApellido());
            existente.setEmail(usuario.getEmail());
            existente.setTelefono(usuario.getTelefono());
            existente.setCtlc(usuario.getCtlc());

            return usuarioRepository.save(existente);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + usuario.getId());
        }
    }

}
