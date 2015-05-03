<%-- 
    Document   : newjsp
    Created on : 26-avr.-2015, 12:08:22
    Author     : Alain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Encodage de r&eacute;sultats</title>
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
    </head>
    <body>
        <header>
            <h1>Encodage de r&eacute;sultats</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            <section>
                 <p>La cible <b style="color: red">${requestScope.Cible}</b> 
            a été demandée mais n'est pas gérée dans le FrontController </p>
            </section>
        </div>
        <footer>
            
            <p>BLABLABLA</p>
        </footer>
    </body>
</html>
