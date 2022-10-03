Alle Bausteine um HTTP Endpunkte zu definieren und zu bestimmen: 
-Pfad
-Query Parameter 
-HTTP Verb: GET, POST, PUT, DELETE (HEAD, OPTIONS, PATCH, TRACE) für die Operationen auf den Ressourcen
-Request Body

REST: Im Mittelpunkt sind die Ressourcen, also die Entitäten im Projekt, in Mehrzahl
Produkte 
Kunden
Bestellungen

Lade alle Produkte vom Server 
GET /api/products

Lade Produkte mit bestimmer ID
GET /api/products/{id}

Lade Produkte mit bestimmten Tag
GET /api/products?tag={tag}

Erzeuge neues Produkt
POST /api/products

Update Produkt
PUT /api/products/{id}

Lösche Produkt
DELETE /api/products/{id}

Füge Tags zu Produkt hinzu (tags als Subressource, weil Liste von Werten innerhalb des Produkts)
PUT /api/products/{id}/tags

Bestelle Produkt --> Erzeuge neue Bestellung, in Ressourcen denken!
POST /api/orders

Füge Produkt zu Bestellung hinzu
PUT /api/orders/{id}/products

-Man kann Use Cases haben, die nicht dem Restkonzept folgen, z.B. login/logout wären keine Ressourcen, würde man aber trotzdem
als Endpunkte definieren

-Tags updaten/löschen über Subressourcen  
-Partielle Updates über PUT, wenn Attribut "" empty String dann gelöscht, 
nichts mitgeschickt -> erhaltet man null (wegen Jackson Parser)