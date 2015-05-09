# adi-recap
ESI - Exercice récapitulatif d'ADI.

## Library GSON (Google) 

http://search.maven.org/#artifactdetails|com.google.code.gson|gson|2.3.1|jar

```JAVA
Gson gson = new Gson();
Object obj = new Object();
String json = gson.toJson(obj); // Serialize obj to json, works with a collection
Object obj2 = gson.fromJson(json); // deserializes json into object
```

## Library JSON-simple (Check lib folder)

```JAVA

Collection<Type> col = (ArrayList) getCollection(); // Get a collection of objects
JSONArray array = new JSONArray(col);
String json = array.toString(); // Serialize col to json
```

## JSP Include

```JSP
<%@ include file="yourPath" %>
```

## Expression Language

TODO

## JSTL

### Import JSTL

```JSP
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### Create variable

```JSP
<c:set var="varName" value="varValue"></c:set>
```

### if statement

```JSP
<c:if test="${your test}">
  statements
</c:if>
```

Example : 

```JSP
<c:set var="col" value="${requestScope.collection}"></c:set> <!-- set a collection from requestScope -->
<c:if test="${not empty col}"> <!-- Test if col is not empty (or exists) -->
  statements
</c:if>

```

### choose statement

```JSP
<c:choose>
  <!-- if -->
  <c:when test="${test1}"> 
    statements
  </c:when>
  
  <!-- else if -->
  <c:when test="${test2}">
    statements
  </c:when>
  
  <!-- else -->
  <c:otherwise>
    statements
  </c:otherwise>
  
</c:choose>
```

### out statement

```JSP
<c:out value="${valToDisplay}"><c:out> <!-- Display valToDisplay -->
```
 
### forEach statement

```JSP
<c:forEach var="oneItem" varStatus="i" begin="${1}" items="${collection}">
  <!-- Everything is optional -->
  <!-- varStatus : count variable : ${i.count} to access i value / ${i.index} to access i index (loop index) -->
  <!-- end="${value}" to set the ending value of varStatus -->
  <!-- When varStatus = end, the loops ends -->
</c:forEach>
```
