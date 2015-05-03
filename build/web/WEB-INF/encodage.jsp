<%-- 
    Document   : encodage
    Created on : 03-mai-2015, 23:52:33
    Author     : zyn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
        <link rel='stylesheet' type='text/css' href='css/form.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <title>Encodage</title>
    </head>
    <body>
        <header>
            <h1>Encodage</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            
            
            <section id='encodage'>
                <form id='form-encodage' action='FrontController'>
                    <label>Equipe HOME</label>
                    <input type='number' name='id-home'/>
                    
                    <label>Score HOME</label>
                    <input type='number' name='score-home'/>
                    <br/>
                    
                    <label>Equipe Visiteur</label>
                    <input type='number' name='id-visiteur' />

                    <label>Score Visiteur</label>
                    <input type='number' name='score-visiteur'/>
                    <br/>
                    
                    
                    <input type='hidden' name='cible' value='encodage' />
                    
                    <input id='submit' type='submit' name='submit' value='Encoder'/>
                </form>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
