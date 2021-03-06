/*
 * Carrega dados para p�gina de cadastro de contrato
 * Lista de Gestores
 * Lista de Fiscais � a mesma de gestores
 * Lista de Usos
 * Lista de Fontes pagantes
 * Lista de Recursos 
 */

package web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OutroDAO;
import dao.UsuarioDAO;
import entity.Outro;
import entity.Usuario;

public class TelaNovoContrato implements Logica{
	@SuppressWarnings("unchecked")
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String tabelaRecurso = "recurso",
					 tabelaUso = "uso",
					 tabelaFonte = "fontepagante";
		
//		Tenta recuperar uma lista da sess�o		
		List<Outro> teste = (List<Outro>) pedido.getSession().getAttribute(tabelaFonte);
		List<Usuario> gestores = (List<Usuario>) pedido.getSession().getAttribute("gestores");
		
		if(teste == null || gestores == null){
//			Para evitar acessos desnecess�rios ao banco de dados toda vez que chamar a p�gina
//			Se a lista n�o existe, significa que deve carregar todas as outras tamb�m
			pedido.getSession().setAttribute(tabelaRecurso, new OutroDAO(tabelaRecurso).getAll());
			pedido.getSession().setAttribute(tabelaUso, new OutroDAO(tabelaUso).getAll());
			pedido.getSession().setAttribute(tabelaFonte, new OutroDAO(tabelaFonte).getAll());
			pedido.getSession().setAttribute("gestores", new UsuarioDAO().getAllGestor());
		}		
		
		return "/Gestor geral/novoContrato.jsp";
	}

}
