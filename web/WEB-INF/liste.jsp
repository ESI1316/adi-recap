<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.List"%>
<%@page import="dto.RencontreDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' type='text/css' href='css/gabarits.css'>
        <link rel='stylesheet' type='text/css' href='css/colorbox.css'>
        <link rel='stylesheet' type='text/css' href='css/accueil.css'>
        <link rel='stylesheet' type='text/css' href='css/liste.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <script src="js/autoload.js"></script>
        <title>Championnat XXXXXX: Résultats</title>
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
            
            
            <section id='search'>
                <form id='form' action="FrontController">
                    
                    <label>No de club</label>
                    <select name="club" id="select-club"></select>
                    <br/>
                    
                    <label>No d'équipe</label>
                    <select name="equipe" id="select-equipe"></select>
                    <br/>
                    
                    <label>No de journée</label>
                    <select name="jour" id="select-jour">
                        <option value="">Aucune journée choisie</option>
                        <c:forEach begin="1" end="14" varStatus="i">
                            <option value="${i.count}">${i.count}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    
                    <input type="hidden" name="cible" value="resultat"/>
                    <input type="submit" id="submit"/>
                </form>
        
        
                <c:set var="i" value ="${0}"></c:set>
                <c:if test="${not empty requestScope.rc}">
                    <c:forEach var="rencontre" items="${requestScope.rc}">
                        <c:set var="scoreH" value="${rencontre.scoreH}"></c:set>
                        <c:set var="scoreV" value="${rencontre.scoreV}"></c:set>
                        <c:set var="equipeH" value="${rencontre.equipeHome}"></c:set>
                        <c:set var="equipeV" value="${rencontre.equipeVisiteur}"></c:set>
                        <c:set var="journee" value="${rencontre.journee}"></c:set>
                        
                        <c:if test="${i != journee}">
                            <c:set var="i" value="${journee}"></c:set>
                            <span class="journee">
                                <c:out value="Journée " />
                                <c:out value="${i}" />
                            </span>
                        </c:if>
                        
                        <script>

                            var equipeH = '${equipeH}';
                            var equipeV = '${equipeV}';
                            var scoreH = '${scoreH}';
                            var scoreV = '${scoreV}';
                            var journee = '${journee}';
                            var home = $('<span class="home">' + equipeH + ' ' + scoreH + '</span>');
                            var visiteur = $('<span class="visiteur">' + scoreV + ' ' + equipeV + '</span>');
                            
                            if (scoreH > scoreV)
                            {
                                home.addClass('vainqueur');
                                visiteur.addClass('perdant');
                            }
                            else if (scoreV > scoreH)
                            {
                                visiteur.addClass('vainqueur');
                                home.addClass('perdant');
                            }
                             
                            var match = $('<p class="match"></p>');
                            match.append(home);
                            match.append(visiteur);
                            
                            
                            $('#search').append(match);
                            
                        </script>
                    </c:forEach>
                </c:if>

            </section>
        </div>
        <footer>
            
            <p>BLABLABLA</p>
        </footer>
    </body>
</html>
