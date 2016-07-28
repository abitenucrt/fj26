package br.com.caelum.notasfiscais.listener;

import javax.enterprise.event.Observes;

import br.com.caelum.notasfiscais.modelo.Usuario;

public class LoginListener {
	
	public void onLogin(@Observes Usuario usuario){
		System.out.println("Usuario %s se logou no sistema" + usuario.getLogin());
	}

}
