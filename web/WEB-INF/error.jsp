<%-- 
    Document   : error
    Created on : 28-avr.-2015, 9:45:55
    Author     : zyn
--%>

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
        <h1>Erreur ! Recherche invalide</h1>
        <p>${requestScope.msg}</p>
    </body>
</html>
