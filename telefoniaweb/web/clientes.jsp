<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Clientes</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="wint=device-width,initial-scale=01">
        
        <style><%@include file="estilo.css"%></style>
    </head>
    <body>
        <div class="margem">
            <div class="cabecalho">
                <h1>Cadastro de clientes</h1>
            </div>

            <div class="row">
                <div class="col-100">
                    <a href='cliente.jsp'><button id="novo" class="botao">+ Novo Registro</button></a>
                </div>
            </div>
            <div class="container">
                <table>
                    <thead>
                        <tr>
                            <th style ="text-align:center">Id</th>
                            <th style ="text-align:center">Nome</th>
                            <th style ="text-align:center">Sexo</th>
                            <th style ="text-align:center">Telefone</th>
                            <th style ="text-align:center">E-mail</th>
                            <th style ="text-align:center">Editar</th>
                            <th style ="text-align:center">Excluir</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="cliente" items="${clientes}">

                        <tr>
                            <td style ="text-align:center"><c:out value="${cliente.id}"/></td>
                            <td style ="text-align: left"><c:out value="${cliente.nome}"/></td>
                            <td style ="text-align:center"><c:out value="${cliente.sexo}"/></td>
                            <td style ="text-align:center"><c:out value="${cliente.telefone}"/></td>
                            <td style ="text-align:center"><c:out value="${cliente.email}"/></td>                            
                            <td style ="text-align:center"><a href='editar?id=<c:out value="${cliente.id}"/>'>&#128394;</a></td>
                            <td style ="text-align:center"><a href='excluir?id=<c:out value="${cliente.id}"/>' onclick="return confirm('confirma a exclusão do REGISTRO')">&#128465;</a></td>
                        </tr>                                        
                                </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                       </div>
                    </body>
               </html>
             