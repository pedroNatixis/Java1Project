<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página de Layout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="#">Meu Banco</a>
            <div class="collapse navbar-collapse justify-content-end">
                <ul class="navbar-nav">
                    <% 
                        // Obter o nome de utilizador da sessão
                        String loggedInUser = (String) session.getAttribute("loggedInUser");
                    %>
                    <% if (loggedInUser != null) { %>
                        <li class="nav-item">
                            <span class="navbar-text">Bem-vindo, <%= loggedInUser %>!</span>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logOut">Log Out</a>
                        </li>
                    <% } else { %>
                        <li class="nav-item">
                            <a class="nav-link" href="#signInModal" data-toggle="modal" data-target="#signInModal">Sign In</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#signUpModal" data-toggle="modal" data-target="#signUpModal">Sign Up</a>
                        </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container mt-5">
        <h1>Bem-vindo ao Meu Banco</h1>
        <p>Faça login para explorar nossas funcionalidades.</p>
    </div>

    <!-- Modal Sign In -->
    <div class="modal fade" id="signInModal" tabindex="-1" role="dialog" aria-labelledby="signInModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="signInModalLabel">Sign In</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="logIn" method="post">
                        <div class="form-group">
                            <label for="username">Nome de utilizador:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Senha:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Sign Up -->
    <div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="signUpModalLabel">Sign Up</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="registerUser" method="post">
                        <div class="form-group">
                            <label for="username">Nome de utilizador:</label>
                            <input type="text" class="form-control" id="username" name="username" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="password">Senha:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Registar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
