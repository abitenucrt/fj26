package br.com.caelum.notasfiscais.mb;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@RequestScoped
@Named
public class LoginBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	@Inject
	private UsuarioDao dao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	Event<Usuario> eventLogin;
	
	public String efetuaLogin(){
		boolean loginValido = dao.existe(this.usuario);
		if(loginValido){
			usuarioLogado.logar(usuario);
			eventLogin.fire(usuario);
			return "notafiscal?faces-redirect=true";
		}else{
			usuarioLogado.deslogar();
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	public String sair(){
		usuarioLogado.deslogar();
		return "login?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
