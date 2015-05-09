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

Exemple : 

```JSP
<c:set var="col" value="${requestScope.collection"></c:set> <!-- set a collection from requestScope -->
<c:if test="${not empty col}"> <!-- Test if col is not empty (or exists) -->
  statements
</c:if>

```
