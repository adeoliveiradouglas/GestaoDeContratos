package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;

public class TelaPrincipalGestor implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("contratosRecentes", new ContratoDAO().getAllRecente(5));
		
		return "/Gestor/index.jsp";
	}

}
