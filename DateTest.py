import mysql.connector
import random
from datetime import date, timedelta

# =============================
# CONEXIÓN
# =============================
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="1234",
    database="library"
)

cursor = conn.cursor()

print("🔄 Limpiando base de datos...")

# =============================
# LIMPIAR TABLAS (orden correcto por FK)
# =============================
cursor.execute("SET FOREIGN_KEY_CHECKS = 0")
cursor.execute("TRUNCATE TABLE loans")
cursor.execute("TRUNCATE TABLE books")
cursor.execute("TRUNCATE TABLE users")
cursor.execute("TRUNCATE TABLE authors")
cursor.execute("SET FOREIGN_KEY_CHECKS = 1")

# =============================
# AUTORES
# =============================
print("✍ Insertando autores...")

authors = [
    ("Gabriel", "Garcia Marquez", "Colombia"),
    ("Julio", "Cortazar", "Argentina"),
    ("Mario", "Vargas Llosa", "Peru"),
    ("Jorge", "Luis Borges", "Argentina"),
    ("Isabel", "Allende", "Chile"),
    ("Pablo", "Neruda", "Chile"),
    ("Miguel", "de Cervantes", "España"),
    ("Carlos", "Fuentes", "Mexico"),
    ("Laura", "Esquivel", "Mexico"),
    ("Octavio", "Paz", "Mexico"),
    ("Ernest", "Hemingway", "USA"),
    ("Mark", "Twain", "USA"),
    ("Jane", "Austen", "UK"),
    ("George", "Orwell", "UK"),
    ("Fyodor", "Dostoevsky", "Russia"),
    ("Leo", "Tolstoy", "Russia"),
    ("Victor", "Hugo", "France"),
    ("Franz", "Kafka", "Czech Republic"),
    ("Albert", "Camus", "France"),
    ("J.K.", "Rowling", "UK"),
]

cursor.executemany("""
    INSERT INTO authors (name, lastname, nationality)
    VALUES (%s, %s, %s)
""", authors)

# Obtener IDs reales de autores
cursor.execute("SELECT id_author FROM authors")
author_ids = [row[0] for row in cursor.fetchall()]

# =============================
# USUARIOS
# =============================
print("👤 Insertando usuarios...")

users = [
    (f"User{i}", f"Lastname{i}", f"user{i}@mail.com", f"30000000{i}")
    for i in range(1, 31)
]

cursor.executemany("""
    INSERT INTO users (name, lastname, email, phone)
    VALUES (%s, %s, %s, %s)
""", users)

cursor.execute("SELECT id_user FROM users")
user_ids = [row[0] for row in cursor.fetchall()]

# =============================
# LIBROS
# =============================
print("📚 Insertando libros...")

books = []

for i in range(1, 41):
    title = f"Book Title {i}"
    isbn = f"ISBN-{1000+i}"
    year = random.randint(1950, 2024)
    author_id = random.choice(author_ids)

    books.append((title, isbn, year, author_id, "A"))

cursor.executemany("""
    INSERT INTO books (title, isbn, year, id_author, b_status)
    VALUES (%s, %s, %s, %s, %s)
""", books)

cursor.execute("SELECT id_book FROM books")
book_ids = [row[0] for row in cursor.fetchall()]

# =============================
# PRÉSTAMOS
# =============================
print("📖 Insertando préstamos...")

loans = []
borrowed_books = set()

for _ in range(25):
    loan_date = date.today() - timedelta(days=random.randint(1, 30))
    return_date = loan_date + timedelta(days=random.randint(5, 15))
    user_id = random.choice(user_ids)

    # Evitar prestar el mismo libro dos veces
    available_books = list(set(book_ids) - borrowed_books)
    if not available_books:
        break

    book_id = random.choice(available_books)
    borrowed_books.add(book_id)

    loans.append((loan_date, return_date, user_id, book_id))

cursor.executemany("""
    INSERT INTO loans (loan_date, return_date, id_user, id_book)
    VALUES (%s, %s, %s, %s)
""", loans)

# =============================
# ACTUALIZAR ESTADO DE LIBROS PRESTADOS
# =============================
print("🔄 Actualizando estado de libros...")

for book_id in borrowed_books:
    cursor.execute("""
        UPDATE books
        SET b_status = 'L'
        WHERE id_book = %s
    """, (book_id,))

# =============================
# CONFIRMAR
# =============================
conn.commit()
cursor.close()
conn.close()

print("✅ Base de datos poblada correctamente.")
print("📊 20 autores")
print("👤 30 usuarios")
print("📚 40 libros")
print(f"📖 {len(loans)} préstamos")