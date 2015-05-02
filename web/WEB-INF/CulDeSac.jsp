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
        <title>CulDeSac</title>
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
    </head>
    <body>
        <header>
            <h1>Championnat XXXXXX: Résultats</h1>
        </header>
        
        <div id="center">
            <nav>
                <ul>
                    <li><a href="FrontController?cible=resultat">Liste</a></li>
                    <li><a href="FrontController?cible=encodage">Encodage</a></li>
                </ul>
            </nav>
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
