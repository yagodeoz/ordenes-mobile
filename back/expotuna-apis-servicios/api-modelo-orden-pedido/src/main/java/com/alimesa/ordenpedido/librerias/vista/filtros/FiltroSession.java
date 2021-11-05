package com.alimesa.ordenpedido.librerias.vista.filtros;

import java.io.IOException;
import java.security.Principal;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestGuardiaSession;
import com.alimesa.ordenpedido.librerias.vista.anotaciones.ITestServicioAutorizacion;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IGuardiaUsuarioSession;
import com.alimesa.ordenpedido.librerias.vista.interfaces.IServicioAutorizacion;

@WebFilter(dispatcherTypes = { DispatcherType.ERROR, DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE }, urlPatterns = { "/privadas/*" })
public class FiltroSession implements Filter {

	@Inject
	@ITestGuardiaSession
	private IGuardiaUsuarioSession guardiaUsuarioSession;

	@Inject
	@ITestServicioAutorizacion
	private IServicioAutorizacion serviciosMantenimientoSeguridad;

	public FiltroSession() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String dato = null;
		if (req.getSession(false) != null) {
			dato = (String) req.getSession(false).getAttribute(IGuardiaUsuarioSession.SEMILLA);
		}

		System.out.println("***************************************************");
		System.out.println("USARIO EN SESION: " + guardiaUsuarioSession.usuarioEnSession());
		System.out.println("URL: " + req.getRequestURI());
		System.out.println("En sesion" + req.getRequestURI().endsWith(IGuardiaUsuarioSession.PAGINA_PRINCIPAL));
		System.out.println("METODO " + req.getMethod().toUpperCase());
		System.out.println("ATRIBUTO " + dato);
		System.out.println("***************************************************");

		HttpSession session = req.getSession(false);

		Principal usuarioPrincipal = req.getUserPrincipal();
		System.out.println("USUARIO PRINCIPAL " + usuarioPrincipal);
		if (usuarioPrincipal == null) {
			System.out.println("Nadie autenticado");
			res.sendRedirect(req.getContextPath() + IGuardiaUsuarioSession.PAGINA_LOGIN_RED);
		} else {

			if (session == null || !guardiaUsuarioSession.usuarioEnSession()
					|| (req.getMethod().toUpperCase().equals("GET")
							&& req.getRequestURI().endsWith(IGuardiaUsuarioSession.PAGINA_PRINCIPAL)
							&& (dato == null && !guardiaUsuarioSession.validarSemilla(dato)))

			) {
				System.out.println("***************************************************");
				System.out.println("SE REDIRECCIONARA A LOGIN");
				System.out.println("***************************************************");
				res.sendRedirect(req.getContextPath() + IGuardiaUsuarioSession.PAGINA_LOGIN_RED);
			} else {

				if (req.getRequestURI().contains("RES_NOT_FOUND")) {
					System.out.println("***************************************************");
					System.out.println("RES_NOT_FOUND NO VALIDAR");
					System.out.println("***************************************************");
					chain.doFilter(request, response);
				} else {

					System.out.println(req.getUserPrincipal());
					String usuario = serviciosMantenimientoSeguridad.obtenerUsuarioAutenticado();
					if (usuario == null) {
						System.out.println("***************************************************");
						System.out.println("NO HAY USUARIO OJO");
						System.out.println("***************************************************");
						chain.doFilter(request, response);
					} else {
						System.out.println("***************************************************");
						System.out.println("PAGINA REQUEST " + req.getRequestURI());
						System.out.println("***************************************************");
						if (!req.getRequestURI()
								.equals(req.getContextPath() + IGuardiaUsuarioSession.PAGINA_PRINCIPAL)) {

							if (!(validaUsuarioOpcion(req.getRequestURI(), usuario, req.getContextPath()))) {

								res.sendRedirect(req.getContextPath() + IGuardiaUsuarioSession.PAGINA_NO_PERMITIDO);

							} else {

								chain.doFilter(request, response);

							}
						} else {
							chain.doFilter(request, response);

						}
					}
				}
			}
		}
	}

	public boolean validaUsuarioOpcion(String url, String usuario, String contextPath) {
		System.out.println("***************************************************");
		System.out.println("VALIDACION DE AUTORIZACION DE OPCION");
		System.out.println("USUARIO : " + usuario);
		System.out.println("URL : " + url);
		System.out.println("CONTEXTPATH : " + contextPath);
		System.out.println("***************************************************");
		return serviciosMantenimientoSeguridad.validarOpcionUsuario(url, usuario, contextPath);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
