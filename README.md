# Aplicatie Gestiune Service Auto

### Autor 
Cristian Mardare
### Descriere
Proiect examen acreditare 

Aplicatie pentru gestionarea activitatii unui service auto.
S-au considerat 3 entitati:
- Inregistrare vehicul si deschidere comanda service.
- Gestionare activitate service.
- Gestionare piese de schimb si materiale.

Entitatile trebuie sa retina si sa comunice informatii intre ele.

## Componenta si tehnologii
### Baze de date
 - NoSQL - MongoDB
 - MySql - MariaDb
### Backend
 - Microservicii dezvoltate cu JAVA 17 - Spring Boot
### Frontend
- Aplicatie Angular v12.

### Servicii de mesaje
- RabbitMQ
- Websockets

### Alte tehnologii
 - Docker - pentru rularea bazelor de date si a serviciului RabbitMQ


### Rulare aplicatie
#### Baze de date si mesaje
MongoDB:

<code>
docker run -d --name mongo-serv -p 27017:27017 mongo
</code>

MariaDB:

<code>
docker run -p 127.0.0.1:3306:3306  --name mariadb -e MARIADB_ROOT_PASSWORD=Password123! -d mariadb:latest
<br>
docker exec -it mariadb mariadb --user root -pPassword123!
<br>
create database vehicles;
</code>

RabbitMQ

<code>
docker run -d --hostname my-autoserv-rabbit --name autoserv-rabbit -p 8082:15672 -p 5672:5672 rabbitmq:management
</code>
#### Backend
Se pornesc proiectele vehicle-provider, vehicle-service si vehicle-shop
### Frontend
In proiectul service-client se ruleaza comanda
<code>
ng serve --port 4444
</code>

Se deschide un browser la adresa http://localhost:4444