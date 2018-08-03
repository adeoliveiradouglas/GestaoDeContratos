/*
 * Classe usada para formatar o valor que vem da tela para um tipo interno do banco
 * Para fazer: retornar o valor para o formato da tela
 * formato da tela: XXX.XXX.XXX,XX
 * formato do banco: XXXXXXXXX.XX
 *  
 */

package utilidades;

import java.math.BigDecimal;
import java.util.Date;

public class FormatarCampo{

	public FormatarCampo(){}
	
	public String stringToDecimal(String parameter) {
//		Tirar pontos do valor e mudar v�rgula para ponto
		parameter = parameter.replace(".", "");
		parameter = parameter.replace(",", ".");
		
		return parameter;
	}
	
	public String decimalToString(BigDecimal b) {
//		pontos do valor e mudar v�rgula para ponto
		String parameter = "" + b,
				aux = "0,0";
		
		try {
			parameter = parameter.replace(".", ",");
					
			int ivirgula = parameter.indexOf(","),
				iaux = 0;
			
			aux = parameter.substring(0, ivirgula);
			
					
//		if(aux.length() > 3 && aux.length() < 7 )
			
			for (int i = ivirgula; i > 0; --i){
				switch(iaux){
					case 3:			
						aux = aux.substring(0, i) + "." + aux.substring(i, aux.length());
						break;
					case 6:
						aux = aux.substring(0, i) + "." + aux.substring(i, aux.length());
						break;
				}
				
				++iaux;
			}
			aux = aux + parameter.substring(ivirgula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return aux;
	}

	public String cnpjToBd(String cnpj) {
//		verifica tamanho do campo do cnpj
		if(cnpj.length() > 18){
			cnpj = cnpj.substring(0, 18);
		}
		return cnpj;
	}
	
	public String dataToString(Date data){
		String antigo = "" + data,
			   novo = "";
		
		try {
			novo = antigo.substring(8, 10) + "/" + antigo.substring(5, 7) + "/" + antigo.substring(0, 4);
		} catch (Exception e) {
//			e.printStackTrace();
		}
		
		return novo;
	}
}