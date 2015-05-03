<%-- 
    Document   : ident
    Created on : 03-mai-2015, 17:01:00
    Author     : zyn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
        <link rel='stylesheet' type='text/css' href='css/form.css'>
        <link rel='stylesheet' type='text/css' href='css/ident.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <title>Identification</title>
    </head>
    <body>
        <header>
            <h1>Identification</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            
            
            <section id='ident'>
                <c:choose>
                <c:when test="${empty sessionScope.connected}">
                    
                    <c:if test="${not empty requestScope.InvalidIdent}">
                        <p id="invalid-ident">${requestScope.InvalidIdent}</p>
                    </c:if>
                    <form id='form-ident' action="FrontController">
                        <label>Identifiant du club : </label>
                        <input type="text" name="club" /><br/>

                        <label>Mot de passe :</label>
                        <input type="password" name="password" /><br/>
                        <input type="hidden" name="cible" value="connexion"/>
                        <input value="Se connecter" type="submit" id="submit"/>
                    </form>
                
                </c:when>
                <c:otherwise>
                    <a id="deconnexion-button" href="FrontController?cible=deconnexion">Se déconnecter</a>
                </c:otherwise>
                </c:choose>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
