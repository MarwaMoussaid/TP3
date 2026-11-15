<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des produits</title>
</head>
<body>
    <h1>Gestion des produits</h1>
    <c:if test="${not empty sessionScope.flash}">
        <p style="color:green"><c:out value="${sessionScope.flash}"/></p>
        <c:remove var="flash" scope="session"/>
    </c:if>
    <a href="${ctx}/produits?action=new">Ajouter un produit</a>
    <br><br>
    <c:choose>
        <c:when test="${empty produits}">
            <p>Aucun produit disponible.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="8">
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Description</th>
                    <th>Prix</th>
                    <th>Stock</th>
                    <th>Actions</th>
                </tr>
                <c:forEach items="${produits}" var="produit">
                    <tr>
                        <td>${produit.id}</td>
                        <td><c:out value="${produit.nom}"/></td>
                        <td><c:out value="${produit.description}"/></td>
                        <td><fmt:formatNumber value="${produit.prix}" minFractionDigits="2" maxFractionDigits="2"/></td>
                        <td>
                            <c:if test="${produit.stock == 0}">
                                <span style="color:red">${produit.stock}</span>
                            </c:if>
                            <c:if test="${produit.stock > 0}">
                                ${produit.stock}
                            </c:if>
                        </td>
                        <td>
                            <a href="${ctx}/produits?action=edit&id=${produit.id}">Modifier</a>
                            <form method="post" action="${ctx}/produits?action=delete" style="display:inline">
                                <input type="hidden" name="id" value="${produit.id}">
                                <button type="submit" onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?')">Supprimer</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
    <br>
    <a href="${ctx}/dashboard">Retour au tableau de bord</a>
</body>
</html>