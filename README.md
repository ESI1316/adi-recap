# adi-recap

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

We can use Java in our JSP files but it is not recommended. *Use JSTL instead.* 

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
<%= new Date() %> <!-- Prints courant date -->
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

* A library (.jar) is needed ! * (See netbeans libraries)

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
