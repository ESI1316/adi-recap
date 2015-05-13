<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erreur</title>
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
    </head>
    <body>
        
        <header>Erreur ! Recherche invalide</header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            <section>
                <p>${requestScope.msg}</p>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
