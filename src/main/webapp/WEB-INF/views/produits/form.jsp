<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire produit</title>
</head>
<body>
    <h1>
        <c:choose>
        <c:when test="${empty produit.id}">Ajouter un produit</c:when>
        <c:otherwise>Modifier un produit</c:otherwise>
        </c:choose>
    </h1>
    <form method="post" action="${ctx}/produits">
        <input type="hidden" name="id" value="${produit.id}">
        <label>Nom :</label>
        <input type="text" name="nom" value="<c:out value='${produit.nom}'/>" required>
        <c:if test="${not empty erreurs.nom}">
            <p style="color:red">${erreurs.nom}</p>
        </c:if>
        <br><br>
        <label>Description:</label>
        <textarea name="description" maxlength="255"><c:out value="${produit.description}"/></textarea>
        <c:if test="${not empty erreurs.description}">
            <p style="color:red">${erreurs.description}</p>
        </c:if>
        <br><br>
        <label>Prix:</label>
        <input type="number" name="prix" step="0.01" min="0.01" value="${produit.prix}" required>
        <c:if test="${not empty erreurs.prix}">
            <p style="color:red">${erreurs.prix}</p>
        </c:if>
        <br><br>
        <label>Stock:</label>
        <input type="number" name="stock" min="0" value="${produit.stock}">
        <c:if test="${not empty erreurs.stock}">
        <p style="color:red">${erreurs.stock}</p>
        </c:if>
        <br><br>
        <button type="submit">Enregistrer</button>
    </form>
    <br>
    <a href="${ctx}/produits">Retour à la liste</a>
</body>
</html>