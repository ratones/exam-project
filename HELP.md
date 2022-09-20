# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.3/maven-plugin/reference/html/#build-image)

mariadb docker start command
docker run -p 127.0.0.1:3306:3306  --name mariadb -e MARIADB_ROOT_PASSWORD=Password123! -d mariadb:latest

connect to mariadb
docker exec -it mariadb mariadb --user root -pPassword123!

create db
create database vehicles;


