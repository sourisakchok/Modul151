![img.png](img.png)
Frage
Als Hashing Funktion des Passwortes wird bcrypt verwendet. Gegen was schützt ein
Salt und wie unterscheidet sich dieser zu einem Pepper?
Antwort:
Ein Salt ist eine zufällige Zeichenfolge, die zu jedem Passwort hinzugeefügt und zusammen mit dem Passwort-Hash gespeichert wird, um gegen Rainbow-Table-Angriffe zu schützen. Ein Pepper ist eine weitere geheime Zeichenfolge, die serverseitig gespeichert wird und nicht in der Datenbank erscheint, um die Sicherheit zu erhöhen, indem er eine zusätzliche Ebene der Geheimhaltung bietet. Während ein Salt jeden Passwort-Hash einzigartig macht, fungiert ein Pepper als globaler geheimer Schlüssel für alle Passwörter.


