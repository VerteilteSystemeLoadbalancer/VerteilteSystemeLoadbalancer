# VerteilteSystemeLoadbalancer

Kurzbeschreibung:
- Client kann eine Berechnung (10.000.000 mal SHA256) anfordern
- Client wählt durch Zufall einen Loadbalancer aus und schickt die Anforderung an ihn
- Loadbalancer verteilt diese Anforderungen an einen von mehreren CalculationServer über RoundRobin
- CalculationServer führt die angeforderte Berechnung durch und sendet das Ergebnis anschließend zurück an den Loadbalancer
- Loadbalancer leitet das Ergebnis weiter an den Client
<br/>

Voraussetzung:
- Client kennt alle Loadbalancer
- Loadbalancer kennt alle CalculationServer
<br/>

Anforderungen:
- Programmiersprache Java
- Strategien: Round Robin & zufällige Zuweisung
- 3 Anwendungen: [Client](src/de/dhbw/loadbalancer/system/Client.java), [Loadbalancer](src/de/dhbw/loadbalancer/system/Loadbalancer.java), [CalculationServer](src/de/dhbw/loadbalancer/system/CalculationServer.java)

<br/>



Zu Demozwecken laufen alle Systeme auf dem gleichen Computer. Es stellt allerdings kein Problem dar, die Systeme verteilt zu starten.

## Übersicht
<img src="skizze.png" alt="skizze" width="500"/>

## Ablauf Berechnung
In der Skizze ist der Ablauf einer Anfrage über alle 3 Anwendungen abgebildet.
<img src="ablauf.png" alt="ablauf" width="500"/>

## Queue-Thread
In der folgenden Skizze ist der Ablauf der [Queue](src/de/dhbw/loadbalancer/system/queue/Queue.java) eines CalculationServer.
<br/><br/>
<img src="queue.png" alt="queue" width="200"/>
