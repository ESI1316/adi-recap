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
