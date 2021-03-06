package web;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContratoDAO;
import entity.Contrato;
import entity.Outro;
import entity.Usuario;
import utilidades.FormatarCampo;

public class CadastrarContrato implements Logica {

	@Override
	@SuppressWarnings("unchecked")
	public String executa(HttpServletRequest pedido, HttpServletResponse resposta) throws Exception {
		final String formatoData = "yyyy-MM-dd";

		Date assinatura = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataAssinatura")),
				ordemDeServico = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataOs")),
				garantia = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataGarantia")),
				vencimento = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataVencimento")),
				vencimentoGarantia = new SimpleDateFormat(formatoData).parse(pedido.getParameter("dataVencimentoGarantia"));

		List<Usuario> usuarios = (ArrayList<Usuario>) pedido.getSession().getAttribute("gestores");
		Usuario gestor = usuarios.get(Integer.parseInt(pedido.getParameter("gestor"))),
				fiscal = usuarios.get(Integer.parseInt(pedido.getParameter("fiscal")));

		FormatarCampo format = new FormatarCampo();

		BigDecimal valor = new BigDecimal(format.stringToDecimal(pedido.getParameter("valor")));

		List<Outro> lRecurso = (ArrayList<Outro>) pedido.getSession().getAttribute("recurso");
		List<Outro> lUso = (ArrayList<Outro>) pedido.getSession().getAttribute("uso");
		List<Outro> lFonte = (ArrayList<Outro>) pedido.getSession().getAttribute("fontepagante");

		Outro recurso = lRecurso.get(Integer.parseInt(pedido.getParameter("recurso"))),
				fontepagante = lFonte.get(Integer.parseInt(pedido.getParameter("fontepagante"))),
				uso = lUso.get(Integer.parseInt(pedido.getParameter("uso")));

		String cnpj = format.cnpjToBd(pedido.getParameter("cnpjEmpresa"));

		Contrato c = new Contrato(pedido.getParameter("numero"), Integer.parseInt(pedido.getParameter("portaria")),
				gestor, fiscal, cnpj, pedido.getParameter("nomeEmpresa"), pedido.getParameter("objeto"), recurso,
				fontepagante, uso, assinatura, ordemDeServico, garantia, vencimento, vencimentoGarantia, valor);

		new ContratoDAO().inserir(c);
		return "sistema?logica=TelaPrincipalGestorGeral";
	}

}
