package servlet;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import dao.UsuarioNovoDAO;
import email.Email;
import entity.Usuario;
import sun.misc.BASE64Encoder;

@WebServlet("/cadastrarusuarioservlet")
public class CadastrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -325209724266609709L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws IOException, ServletException {
		String nome = pedido.getParameter("nome");
		String email = pedido.getParameter("email");
		UsuarioNovoDAO undao = new UsuarioNovoDAO();
		
		if (undao.getByEmail(email) == null && new UsuarioDAO().getByEmail(email) == null) {
			//se usu�rio com esse email N�O existe no sistema (usu�rio autorizado ou usu�rio a ser autorizado), ent�o pode ser adicionado			
			try {
				// insere no banco na tabela de novos usuarios
				undao.inserir(
					new Usuario(
						Integer.parseInt(pedido.getParameter("matricula")), 
						nome,
						email, 
						criptografa(pedido.getParameter("senha")), 
						pedido.getParameter("setor"), 
						pedido.getParameter("cargo")
					)
				);
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}

			// envia email informando cadastro
			new Email().enviarConfirmacaoCadastro(email, nome);

			// mostra p�gina informando confirma��o do cadastro
			pedido.getRequestDispatcher("/confirmacaocadastro.jsp").forward(pedido, resposta);
		
		}//Fim do if que testa se o usu�rio j� existe no sistema
		
		else 
			pedido.getRequestDispatcher("/usuarioexiste").forward(pedido, resposta);
	}//Fim do m�todo "service"
	
	private String criptografa(String senha){
		/*
		 * C�digo retirado do site http://www.guj.com.br/t/cadastro-de-usuario-com-senha-criptografada/192679
		 */
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
		}
		return senha;
	}
} //Fim da classe
