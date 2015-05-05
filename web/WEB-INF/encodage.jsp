
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="javax.swing.JOptionPane"%>
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
        <script src="js/autoload.js"></script>
        <title>Encodage</title>
    </head>
    <body>
        <header>
            <h1>Encodage</h1>
        </header>
        
        <div id="center">
            <%@ include file="/WEB-INF/navbar.jsp" %>
            
            <c:set var="clubs" value="${requestScope.clubs}"></c:set>
          
            <section id='encodage' action='FrontController'>
            <c:choose>  
                <c:when test="${not empty sessionScope.connected}">
                    <form id='form-encodage'>

                        <label>Club home</label>
                        <select id='select-club-home' class="select-club" name="club-home">
                            <option value=''>Aucun club choisi</option>

                            <c:if test="${not empty clubs}">
                                <c:forEach var="club" items="${clubs}">
                                    <option value='${club.num}'>${club.nom}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <br/>

                        <label>Equipe HOME</label>
                        <select id='select-equipe-home' name="equipe-home" class="select-equipe"></select>
                        <br/>

                        <label>Score HOME</label>
                        <input type='number' name='score-home' id='score-home'/>
                        <br/>

                        <label>Club visiteur</label>
                        <select id='select-club-visiteur' class="select-club" name="club-visiteur">
                            <option value=''>Aucun club choisi</option>

                            <c:if test="${not empty clubs}">
                                <c:forEach var="club" items="${clubs}">
                                    <option value='${club.num}'>${club.nom}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <br/>

                        <label>Equipe visiteur</label>
                        <select id='select-equipe-visiteur' name="equipe-visiteur" class="select-equipe"></select>
                        <br/>

                        <label>Score Visiteur</label>
                        <input type='number' name='score-visiteur' id='score-visiteur'/>
                        <br/>

                        <label>Date pr&eacute;vue (AAAA-MM-JJ)</label>
                        <input type="date" name="datePrevue" id='datePrevue'/>
                        <br/>

                        <label>Date r&eacute;elle (AAAA-MM-JJ)</label>
                        <input type="date" name="dateReelle" id='dateReelle'/>
                        <br/>

                        <label>Journée</label>
                        <input type='number' name='journee' id='journee'/>
                        <br/>
                        
                        <input type='hidden' cible='encodage'/>
                        <input type='submit' id='save' value='Enregistrer' />
                    </form>
                </c:when>
                <c:otherwise>
                    <p id='not-connected'>Vous n'&ecirc;tes pas connect&eacute; !</p>
                </c:otherwise>
            </c:choose>
            </section>
        </div>
        <%@ include file="/WEB-INF/footer.jsp" %>
    </body>
</html>
