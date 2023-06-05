<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style><%@include file="estilo.css"%></style>
        <c:out value="${cliente.id}"/>
    </head>
    <body>
        <div class="margem">
            <c:out value="${mensagem}" />
            <div class="cabecalho">
                <h1>Cadastro do Cliente</h1>
            </div>
            <div class="container">
                <c:if test="${cliente == null}">

                    <form action="inserir" method="post">
                    </c:if>
                    <c:if test="${cliente != null}">
                        <form action="editar" method="post">
                            <input type="hidden" id="id" name="id" value="<c:out value='${cliente.id}'/>">
                        </c:if>
                        <div class="row">
                            <div class="col-25">
                                <label for="nome">Nome</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="nome" name="nome" value="<c:out value='${cliente.nome}'/>">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label>Sexo</label>
                            </div>
                            <div class="col-75 boxBackground">
                                <input type="radio" id="masculino" name="sexo" value="M">
                                <label for="masculino">Masculino</label><br>

                                <input type="radio" id="feminino" name="sexo" value="F">
                                <label for="feminino">Feminino</label><br>
                            </div>
                        </div>
                        <script>  
                            var opcaoSelecionada = 'M'; 
                            if (opcaoSelecionada === 'M') {
                                document.getElementById('masculino').checked = true;
                            } else if (opcaoSelecionada === 'F') {
                                document.getElementById('feminino').checked = true;
                            }
                        </script>
                        <div class="row">
                            <div class="col-25">
                                <label for="nome">Telefone</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="nome" name="telefone" value="<c:out value='${cliente.telefone}'/>">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-25">
                                <label for="nome">E-mail</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="nome" name="email" value="<c:out value='${cliente.email}'/>">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-100">
                                <input type="submit" value="Salvar">
                            </div>
                        </div>

                    </form>
            </div>            
        </div>
        <div style="text-align: center;">
            <a href="listar">
                <button id="novo" class="botao">Voltar</button>
            </a>
        </div>
    </body>
</html>
