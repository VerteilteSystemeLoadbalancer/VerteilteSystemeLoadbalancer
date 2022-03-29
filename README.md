# VerteilteSystemeLoadbalancer

Kurzbeschreibung:
- Client kann eine Berechnung (10.000.000 mal SHA256) anfordern
- Client wählt durch Zufall einen Loadbalancer aus und schickt die Anforderung an ihn
- Loadbalancer verteilt diese Anforderungen an einen von mehreren Berechnungs-Server über RoundRobin
- Server führt die angeforderte Berechnung durch und sendet das Ergebnis anschließend zurück an den Loadbalancer
- Loadbalancer leitet das Ergebnis weiter an den Client
<br/>

Anforderungen:
- Programmiersprache Java
- Strategien: Round Robin & zufällige Zuweisung
- 3 Anwendungen (Client, Loadbalancer, Server) (können jeweils mehrfach gestartet werden)
<br/>

Voraussetzung:
- Client kennt alle Loadbalancer
- Loadbalancer kennt alle Server
<br/>

Zu Demozwecken laufen alle Systeme auf dem gleichen Computer. Es stellt allerdings kein Problem dar, die Systeme verteilt zu starten.

## Übersicht
<img src="skizze.png" alt="skizze" width="500"/>

## Ablauf Berechnung
<img src="ablauf.png" alt="ablauf" width="500"/>

## Queue-Thread
<img src="queue.png" alt="queue" width="200"/>
