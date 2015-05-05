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
        <link rel='stylesheet' type='text/css' href='css/form.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="http://cdn.jsdelivr.net/jquery.validation/1.13.1/jquery.validate.min.js"></script>
        <script src="js/verification.js"></script>
        <script src="js/autoload.js"></script>
        <title>Championnat XXXXXX: R&eacute;sultats</title>
    </head>
    <body>
      
        
        <header>
            <h1>Championnat XXXXXX: R&eacute;sultats</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            
            <c:set var="clubs" value="${requestScope.clubs}"></c:set>
            <section id='search'>
                <form id='form-liste' action="FrontController">
                    
                    <label>No de club</label>
                    <select id='select-club' name="club" class="select-club">
                        <option value=''>Aucun club choisi</option>
                        
                        <c:if test="${not empty clubs}">
                            <c:forEach var="club" items="${clubs}">
                                <option value='${club.num}'>${club.nom}</option>
                            </c:forEach>
                        </c:if>
                                
                    </select>
                    <br/>
                    
                    <label>No d'équipe</label>
                    <select id='select-equipe' name="equipe" class="select-equipe"></select>
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
                    <input value="Rechercher" type="submit" class="submit"/>
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
                        
                        <p class="match">
                            <span class="home ${scoreH > scoreV ? 'vainqueur' : ''}">
                                ${equipeH} - ${scoreH}
                            </span>
                            
                            <span class="visiteur ${scoreV > scoreH ? 'vainqueur' : ''}">
                                ${scoreV} - ${equipeV}
                            </span>
                        </p>
                        
                    </c:forEach>
                </c:if>

            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
