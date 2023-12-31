![img.png](img.png)
Frage
Als Hashing Funktion des Passwortes wird bcrypt verwendet. Gegen was schützt ein
Salt und wie unterscheidet sich dieser zu einem Pepper?
Antwort:
Ein Salt ist eine zufällige Zeichenfolge, die zu jedem Passwort hinzugeefügt und zusammen mit dem Passwort-Hash gespeichert wird, um gegen Rainbow-Table-Angriffe zu schützen. Ein Pepper ist eine weitere geheime Zeichenfolge, die serverseitig gespeichert wird und nicht in der Datenbank erscheint, um die Sicherheit zu erhöhen, indem er eine zusätzliche Ebene der Geheimhaltung bietet. Während ein Salt jeden Passwort-Hash einzigartig macht, fungiert ein Pepper als globaler geheimer Schlüssel für alle Passwörter.

frage 2
Nebst der Umsetzung von A2 wird erwartet, dass folgende Frage beantwortet wird:
Was spricht dagegen, das JWT eines Users im localstorage abzuspeichern? Was wäre
eine bessere Alternative?

Antwort 2:
Eine bessere Alternative zur Speicherung von JWTs im LocalStorage, welche oft aufgrund ihrer Anfälligkeit für Cross-Site Scripting (XSS) Angriffe kritisiert wird, ist die Verwendung von HttpOnly-Cookies. Diese Cookies können nicht von Client-seitigem JavaScript gelesen werden, wodurch sie resistenter gegen XSS-Angriffe sind. Zudem bieten sie zusätzliche Sicherheit gegen Man-in-the-Middle-Angriffe, da sie so konfiguriert werden können, dass sie nur über sichere HTTPS-Verbindungen gesendet werden.

Q6:
Die verbersserungsvorschläge sind das ich nichtbenutze variabeln imports sct remove.
Die lambda anpasse 
das ich etwas kommenteirensoll 
fazit ist das es sicher mal speicher optimiert und versucht das es klarer istjedoch will es auch das ich variablen entfernen obwoll ich es benutze mit der begründung das es nicht benutzt wird.