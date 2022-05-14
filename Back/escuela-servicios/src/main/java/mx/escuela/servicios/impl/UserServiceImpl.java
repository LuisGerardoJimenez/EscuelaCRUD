package mx.escuela.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.util.ConstantesGlobales;
import mx.escuela.datos.dao.tables.IUsuariosRepository;
import mx.escuela.datos.dao.tables.impl.Usuarios;
import mx.escuela.servicios.IUsuarioService;

@Slf4j
@Component
public class UserServiceImpl implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuariosRepository usuariosRespository;

	private Usuarios usuarios;

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			log.info("Login:: Usuario:" + username);			
			Usuarios usuarios = this.findByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<>();
			log.info("Login:: Usuario:" + usuarios.getUsuario() + " Password:" + usuarios.getContrasenia() + " Rol:"
					+ usuarios.getRol());
			authorities.add(new SimpleGrantedAuthority(usuarios.getRol()));

			return new org.springframework.security.core.userdetails.User(usuarios.getUsuario(), usuarios.getContrasenia(),
					authorities);
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al cargar usuario: ", e);
			return null;
		}
	}

	@Override
	public Usuarios getUser() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				if (this.usuarios == null || this.usuarios.getUsuario() == null
						|| !this.usuarios.getUsuario().equals(authentication.getName())) {
					this.usuarios = this.findByUsername(authentication.getName());
				}
				return this.usuarios;
			}
		} catch (Exception e) {
			log.error("Ocurri\u00F3 un error al obtener usuario: ", e);
			return null;
		}
		return null;
	}

	public Usuarios findByUsername(String username) {
		Usuarios usuario = this.usuariosRespository.findByUserAndDistinctStatus(username,
				ConstantesGlobales.BOOLEAN_INACTIVO);
		Assert.notNull(usuario, String.format("El usuario %s no existe", username));
		return usuario;
	}


}
