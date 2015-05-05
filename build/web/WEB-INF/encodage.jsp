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
        <link rel='stylesheet' type='text/css' href='css/encodage.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <script src="js/encodage.js"></script>
        <title>Encodage</title>
    </head>
    <body>
        <header>
            <h1>Encodage</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            
            <h1> PAS FINI </h1>
            <section id='encodage'>
                <form id='form-encodage'>
                        <label>Equipe HOME</label>
                        <input type='number' name='id-home' id='id-home'/>
                        <br/>

                        <label>Score HOME</label>
                        <input type='number' name='score-home' id='score-home'/>
                        <br/>

                        <label>Equipe Visiteur</label>
                        <input type='number' name='id-visiteur' id='id-visiteur' />
                        <br/>


                        <label>Score Visiteur</label>
                        <input type='number' name='score-visiteur' id='score-visiteur'/>
                        <br/>

                        <label>Journée</label>
                        <input type='number' name='journee' id='journee'/>
                        <br/>
                        
                        <button id='add-rencontre'>Ajouter</button>
                </form>
                <ul id='rencontres'>
                    
                </ul>
                
                <button id='add-all'>Enregistrer</button>
                <button id='cancel'>Annuler</button>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
