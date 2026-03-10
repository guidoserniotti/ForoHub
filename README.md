# ForoHub

Último desafío propuesto por el curso **Oracle Next Education (ONE)**: una **API REST** para un foro de dudas y respuestas, con **autenticación JWT** y **validaciones**.

## Características
- API REST para gestión de tópicos (temas) del foro.
- Autenticación con **JWT**.
- Validaciones de datos.
- Persistencia con **Spring Data JPA**.
- Migraciones de base de datos con **Flyway**.

## Tecnologías
- **Java 17**
- **Spring Boot 4.0.3**
- Spring Web (WebMVC)
- Spring Security
- Spring Validation
- Spring Data JPA
- **Flyway**
- **MySQL**
- Librería JWT: `com.auth0:java-jwt`

## Requisitos
- Java 17
- MySQL (local o remoto)
- Maven (o usar el wrapper incluido: `mvnw` / `mvnw.cmd`)

## Configuración (application.properties)
El proyecto usa MySQL y define un secreto para JWT con variable de entorno opcional.

- DB (por defecto):
  - `spring.datasource.url=jdbc:mysql://localhost/${DB_NAME}`
  - `spring.datasource.username=${DB_USERNAME}`
  - `spring.datasource.password=${DB_PASSWORD}`
- JWT:
  - `api.security.secret=${JWT_SECRET:123456}`

Recomendación: define la variable de entorno `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD` y `JWT_SECRET` en tu sistema para no usar los valores por defecto ni mis credenciales.

## Base de datos y migraciones (Flyway)
Las migraciones están en:

- `src/main/resources/db/migration/`
  - `V1__create-table-topics.sql`
  - `V2__create-table-users.sql`

Asegúrate de tener creada la base de datos `forohub` en MySQL (o ajusta la URL de conexión).

## Cómo ejecutar

### Opción 1: Maven Wrapper
```bash
./mvnw spring-boot:run
```

En Windows:
```bat
mvnw.cmd spring-boot:run
```

### Opción 2: Maven instalado
```bash
mvn spring-boot:run
```

## Autor
- GitHub: @guidoserniotti
