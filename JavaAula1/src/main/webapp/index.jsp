<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Person" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pessoas</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Lista de Pessoas</h1>
        
       <!-- Formulário para adicionar uma nova pessoa em linha -->
        <form action="addPerson" method="post" class="form-inline mb-4">
            <div class="form-group mr-2">
                <label for="name" class="sr-only">Nome:</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Nome" required style="width: 400px;">
            </div>
            <div class="form-group mr-2">
                <label for="age" class="sr-only">Idade:</label>
                <input type="text" class="form-control" id="age" name="age" placeholder="Idade" required style="width: 200px;">
            </div>
            <div class="form-group mr-2">
                <label for="email" class="sr-only">Email:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required style="width: 400px;">
            </div>
            <button type="submit" class="btn btn-primary">Criar</button>
        </form>
        
        <!-- Verificar e exibir mensagem de erro -->
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="alert alert-danger">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
        
             
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Idade</th>
                    <th>Email</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
              	List<Person> persons = (List<Person>) request.getAttribute("getPersons");
              	if (persons != null) {
                	for (Person person : persons) {
                %>
                <tr>
                   <td><%= person.getId() %></td>
                    <td>
                        <form action="editPerson" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= person.getId() %>">
                            <input type="text" class="form-control" name="name" value="<%= person.getName() %>" required>
                        	</td>
                        	<td>
                            	<input type="text" class="form-control" name="age" value="<%= person.getAge() %>" required>
                        	</td>
                        	<td>
                            	<input type="email" class="form-control" name="email" value="<%= person.getEmail() %>" required>
                        	</td>
                        	<td>
                            <button type="submit" class="btn btn-warning">Editar</button>
                        </form>
                        <form action="deletePerson" method="post" style="display:inline;">
                            <input type="hidden" name="id" value="<%= person.getId() %>">
                            <button type="submit" class="btn btn-danger">Apagar</button>
                        </form>
                        </td>
                </tr>
                <%
                        } // fim do loop
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">Nenhuma pessoa encontrada.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>