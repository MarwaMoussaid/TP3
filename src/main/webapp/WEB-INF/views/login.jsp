
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
</head>
<body>

    <h1>Connexion</h1>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>

   <form method="post" action="${pageContext.request.contextPath}/login" autocomplete="off">

        <label>Nom d'utilisateur :</label>
        <input type="text" name="username" value="<c:out value='${username}'/>">
        <br><br>
        <label>Mot de passe :</label>
       <input type="password" name="password" autocomplete="new-password">
        <br><br>
        <button type="submit">Se connecter</button>
    </form>
</body>
</html>
