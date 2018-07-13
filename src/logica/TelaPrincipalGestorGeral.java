package logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import entity.Usuario;

public class TelaPrincipalGestorGeral implements Logica{

	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		pedido.getSession().setAttribute("contratos", new ContratoDAO().getAllRecente(5));
		pedido.getSession().setAttribute("vencimento90", new ContratoDAO().getVencimento90());
		
		return "/" + ((Usuario) pedido.getSession().getAttribute("usuario")).getCargo().getNome() + "/index.jsp";
	}
	


}