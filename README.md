Claro — aquí tienes un **README.md profesional y completo** para tu proyecto **Library--AndresJPF** listo para subir a GitHub:

---

```markdown
# 📚 Library API (Java + Jakarta EE + MySQL)

Una aplicación backend en Java con **servlets**, **MySQL** y **CRUD REST** para gestionar una biblioteca simple.  
Incluye endpoints para usuarios, autores, libros y préstamos, diseñada para conectar fácilmente con frontends como **Astro**.

---

## 🚀 Tecnologías

✔ Java (Servlets / Jakarta EE)  
✔ Apache Tomcat 11  
✔ MySQL  
✔ GSON para JSON  
✔ Maven  
✔ REST API básica  
✔ Script de **población de datos de prueba** en Python

---

## 📁 Estructura del proyecto

```

Library--AndresJPF/
├─ Controller/
├─ DAO/
├─ Database/
├─ Model/
├─ resources/
├─ webapp/
├─ pom.xml
├─ README.md

````

---

## 🧠 Endpoints disponibles

### ✔ Users
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/users` | Lista todos los usuarios |
| GET | `/users/{id}` | Devuelve un usuario por ID |
| POST | `/users` | Crea un nuevo usuario |
| PUT | `/users/{id}` | Actualiza un usuario |
| DELETE | `/users/{id}` | Elimina (lógico) un usuario |

---

### ✔ Authors
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/authors` | Lista todos los autores |
| GET | `/authors/{id}` | Devuelve un autor por ID |
| POST | `/authors` | Crea un nuevo autor |
| PUT | `/authors/{id}` | Actualiza un autor |
| DELETE | `/authors/{id}` | Elimina (lógico) un autor |

---

### ✔ Books
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/books` | Lista todos los libros |
| GET | `/books/{id}` | Devuelve un libro por ID |
| POST | `/books` | Crea un nuevo libro |
| PUT | `/books/{id}` | Actualiza un libro |
| DELETE | `/books/{id}` | Elimina (lógico) un libro |

---

### ✔ Loans
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/loans` | Lista todos los préstamos |
| GET | `/loans/{id}` | Devuelve un préstamo por ID |
| POST | `/loans` | Crea un nuevo préstamo |

---

## 🛠 Configuración de base de datos

1. Asegúrate de tener MySQL instalado y ejecutándose.
2. Crea la base de datos:

```sql
CREATE DATABASE library;
USE library;
````

3. Ejecuta el script DDL para crear las tablas con defaults correctos:

```sql
-- Table: authors
CREATE TABLE authors (
  id_author INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  lastname VARCHAR(100),
  nationality VARCHAR(50),
  a_status BOOLEAN NOT NULL DEFAULT TRUE
);

-- Table: users
CREATE TABLE users (
  id_user INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  lastname VARCHAR(100),
  email VARCHAR(100),
  phone VARCHAR(20),
  u_status BOOLEAN NOT NULL DEFAULT TRUE
);

-- Table: books
CREATE TABLE books (
  id_book INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(150),
  isbn VARCHAR(20),
  year INT,
  id_author INT,
  b_status ENUM('A','U','L') NOT NULL DEFAULT 'A',
  FOREIGN KEY (id_author) REFERENCES authors(id_author)
);

-- Table: loans
CREATE TABLE loans (
  id_loan INT AUTO_INCREMENT PRIMARY KEY,
  loan_date DATE,
  return_date DATE,
  id_user INT,
  id_book INT,
  FOREIGN KEY (id_user) REFERENCES users(id_user),
  FOREIGN KEY (id_book) REFERENCES books(id_book)
);
```

---

## ⚙️ Conexión desde Java

Asegúrate de configurar tu clase `ConnectionDB.java` así:

```java
Class.forName("com.mysql.cj.jdbc.Driver");
Connection conn = DriverManager.getConnection(
  "jdbc:mysql://localhost:3306/library",
  "root",
  "1234"
);
```

---

## 🧪 Población de datos de prueba

Incluye un script en Python (`populate_library.py`) que:

✔ Limpia la base de datos
✔ Inserta autores, usuarios, libros
✔ Genera préstamos válidos evitando duplicados

Ejecuta:

```bash
pip install mysql-connector-python
python populate_library.py
```

---

## 🚅 Despliegue en Tomcat

1. Instala Apache Tomcat 11.
2. Asegúrate de que el driver de MySQL (`mysql-connector-j.jar`) se incluya en el WAR.
3. `Clean & Build` desde Maven.
4. Despliega el WAR en Tomcat.
5. Accede:

```
http://localhost:8080/Library-Backend/users
```

---

## 📦 Integración con Astro

Para iniciar el Front-End debes tener NodeJS instalado y luego ir a la carpeta "Library--AndresJPF\FrontEnd" y ejecuta los comandos
```js
npm install
npm run dev
```

Puedes consumir este backend desde tu frontend Astro:

```js
const res = await fetch("http://localhost:8080/Library-Backend/users");
const users = await res.json();
```

---

## 📌 Notas finales

✔ El proyecto está diseñado para principiantes
✔ Fuerte lógica de integridad referencial
✔ Base lista para frontend moderno con JSON
✔ Puedes expandir fácilmente a PUT, DELETE, autenticación, etc.

---

## 🧑‍💻 Autor

**Andres JPF**
📍 Barranquilla 🇨🇴
