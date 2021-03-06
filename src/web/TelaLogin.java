/*
 * Redireciona para tela de login
 * inicia monitoramento de vencimento dos contratos 
 */

package web;

import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import utilidades.AvisoVencimento;

public class TelaLogin implements Logica{
	private static boolean monitorandoVencimento = false;
	
	@Override
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		Usuario logado = (Usuario) pedido.getSession().getAttribute("usuario");
		pedido.getSession().setAttribute("pilhaPaginas", new Stack<String>());
		if(logado != null){
			return "sistema?logica=TelaPrincipal";
		}
		
		if(!monitorandoVencimento){//se não estiver monitorando os contratos
//			Inicia monitoramento dos vencimentos de contratos e avisar seus responsáveis
			AvisoVencimento monitorando = new AvisoVencimento();
			Thread monitorar = new Thread(monitorando);
			monitorar.start();
			
			monitorandoVencimento = true;
		}
		
		return "/login.jsp";
	}

}
