<!-- 
	Parte 3 da recupera��o de senha 
	Escolher nova senha
-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta charset="ISO-8859-1">
<title>Sistema de Gest�o de Contratos da CTB - Recuperar senha</title>
<link rel="stylesheet" type="text/css" href="css/vendors.min.css" />
<link rel="stylesheet" type="text/css" href="css/algaworks.min.css" />
<link rel="stylesheet" type="text/css" href="css/application.css" />
</head>

<body class="aw-layout-simple-page">
	<div class="aw-layout-simple-page__container">
		<div align="center">
			<img
				src="http://www.ctb.ba.gov.br/themes/admindireta/images/logo-ctb.png" />
		</div>
		<br />
		<form action="sistema?logica=AlterarSenha" th:object="${userdetails}" method="POST">
			<div class="form-group has-feedback">
				<input type="password" class="form-control input-lg" placeholder="Crie sua senha" name="senha" id="senha">
				<span class="form-control-feedback" aria-hidden="true"></span>
				<div id="mensagemsenha">
					<label>Senha deve ter mais que 6 caracteres</label>
				</div>
			</div>

			<div class="form-group has-feedback" id="confirmacaosenhadiv">
				<input type="password" class="form-control input-lg" placeholder="Confirme sua senha" name="confirmacaosenha" id="confirmacaosenha"> 
				<span class="form-control-feedback" aria-hidden="true"></span>
				<div id="mensagemconfirmacaosenha">
					<label>Senhas n�o conferem</label>
				</div>
			</div>
			<br />
			<div class="form-group" id="botaocadastrar">
				<button type="submit" class="btn btn-primary btn-lg aw-btn-full-width">Confirmar</button>
			</div>
			<div class="aw-simple-panel__footer"><br/></div>
		</form>
		
		<script type="text/javascript" src="script.js"></script>
	</div>
</body>
</html>