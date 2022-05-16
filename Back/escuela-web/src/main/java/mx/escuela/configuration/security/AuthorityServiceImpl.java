package mx.escuela.configuration.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.escuela.comun.util.ConstantesGlobales;

@Slf4j
@Service
public class AuthorityServiceImpl {

	private String roles = "";
	
	public boolean hasAccess(String path) {
		switch (path) {
			case ConstantesGlobales.ROL_ALL:
				roles = "%"+ConstantesGlobales.ROL_ADMIN+"%";
				break;
			case ConstantesGlobales.ROL_ADMIN:
				roles = "%"+ConstantesGlobales.ROL_ADMIN+"%";
				break;
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getAuthorities()
						.stream()
						.map(GrantedAuthority::getAuthority)
						.filter(auth -> _search(auth))
						.toArray(String[]::new)
						.length > 0;
		}
		return false;
	}
	
	private boolean _search(String auth) {
		log.debug(auth);
		return this.roles.contains("%" + auth + "%");
	}
}
