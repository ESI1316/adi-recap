# adi-recap

## Plugin JQuery Cookie

Télécharger le fichier : 

https://github.com/carhartl/jquery-cookie/blob/master/src/jquery.cookie.js

Pour l'utilisation : 

https://github.com/carhartl/jquery-cookie/ 

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

## JSP

### JSP File Include

```JSP
<%@ include file="yourPath" %>
```

### Java in your JSP

We can use Java in our JSP files but it is not recommended. **Use JSTL instead** . 

### JSP Directive

The line to include your JSP is a directive. A directive is delimited by <%@ and %>

We can also import a Java package for example :

```JSP
<%@ page import="java.io.*" %>
```

To include the JSTL, we also use a directive (see JSTL).

### Expression

An expression is delimited by <%= and %>. 

```JSP
<%= new Date() %> <!-- Prints current date -->
```

### Declaration 

A decleration is delimited by <%! and %>.

```JSP 
<%! Date myDate = new Date(); %>
```

You can now access the variable myDate.

### Scriptlet

A scriplet is a Java code fragment (delimited by <% and %>. For example, a loop to print 10 times "Hello World !" :

```JSP
<% for (int i = 0; i < 10; ++i) 
   { %>
   Hello World !
<% } %>
```

## Expression Language

Allows you to access variables, parameters... without Java code.

### Syntax :

```JSP
"${expression}"
```

### Scopes :

```JSP
"${requestScope.parameter}" <!-- Access parameter from request -->
"${sessionScope.parameter}" <!-- Access parameter from session -->
```

### Access object variables (getters are needed)

```JAVA
public class MyClass
{
  private int property1;
  private int property2;
  
  public MyClass(int property1, int property2)
  {
    this.property1 = p1;
    this.property2 = p2;
  }
  
  public int getProperty1()
  {
    return this.property1;
  }
}
```

```JSP
"${requestScope.myClassObject.property1}" <!-- Access property1 -->
"${requestScope.myClassObject.property2}" <!-- Does not work ! No getter for property2 -->
```

### Conditions

```JSP
  "${1 < 2}" <!-- Returns true -->
  "${empty collection}" <!-- Returns true if collection is empty or does not exist -->
  "${not empty collection}" <!-- Returns true if collection is not empty or exists -->
  "${a == b}" <!-- Returns true if a equals b -->
  "${a < b ? a : b}" <!-- Returns a if a is lesser than b, b otherwise -->
  <!-- || and && and ! etc. can also be used -->
```


## JSTL

### Include JSTL

```JSP
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

**A library (.jar) is also needed !** (See netbeans libraries)

### Create variable

You can create variables using the set statement. You can also specify the scope of the variable :
* page (default if not specified)
* request
* session
* application

```JSP
<c:set var="varName" value="${varValue}" scope="page"></c:set>
<c:set var="varName2" >${varValue}</c:set> <!-- Also works -->

```

### Delete variable

You can remove variables using the remove statement. You can also specify the scope. If you don't specify the scope, the statement will remove a variable in the first scope where the variable is found.

```JSP
<c:remove var="varName" scope="page" /> <!-- You can't access varName anymore -->
```

**There is no really need to remove variable**

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

out statement is similar to JSP Java Expression <%= %> but is simpler to use.

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
  <!-- You can also specify a step parameter. step is the incrementation number for varStatus -->
  <!-- Two boolean values are available for varStatus : first and last (true if first element, or last one )-->
</c:forEach>
```

## Ajax 

Four type for the HTTP request :

* GET returns data.
* POST adds data.
* PUT updates data.
* DELETE deletes data.

```JAVASCRIPT
$.ajax({
   type: "requestType",    // POST, GET, PUT, DELETE.
   url: "yourUrl",         // The url
   data: variable,         // The data you pass to the server (url)
   dataType: 'json',       // Returns JSON
   success: function(json) // No need to parse json with JSON.parse(json) if dataType: 'json' is specified
   {
      // Statements if ajax succeeds
   }
   error: function()
   {
      // Statements if ajax fails
   }
});
```

Example :

```JAVASCRIPT
$.ajax({
          url: 'UpdateServlet?cible=equipe', // Request sent to UpdateServlet
                                             // with parameter cible of value equipe
          type: 'GET',            // Get data
          dataType: 'JSON',       // Returns JSON
          data: {club:clubValue}, // Pass parameter with name of club and value of clubValue
          success: function(json)
          {
              $.each(json, function(i, equipe) // For each object of the array contained in the json string
              {
                    $(equipeId).append("<option value='" + equipe.num
                        + "' >" + equipe.club.nom + " (" + equipe.num +")" + "</option>");
              });
          }
      });
```

On server side (Servlet UpdateServlet) : 

```JAVA
// Set response type to json, and encoding to utf-8
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");

// updateEquipe returns a collection from the model
Collection<dto.EquipeDto> equipes = updateEquipe(request, response);

// With the JSON-simple library, we serialize the collection to json
JSONArray array = new JSONArray(equipes);
response.getWriter().write(array.toString()); // Writes the results
```

To access a servlet, the url is not always the servlet name. You can change the url of the servlet
in the web.xml file.
