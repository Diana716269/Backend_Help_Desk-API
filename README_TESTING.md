# Pruebas de Endpoints - Backend API (Tickets)

Archivo con ejemplos `curl` y JSON para probar cada método (GET, POST, PUT, DELETE).

Base URL local:

http://localhost:8080/api/tickets

Notas de respuesta esperada:
- `GET` → `200 OK` (JSON)
- `POST` → `201 Created` (JSON con el objeto creado)
- `PUT` → `200 OK` (JSON con el objeto actualizado)
- `DELETE` → `204 No Content` o `200 OK` (según implementación)

---

## 1) GET — Listar todos los tickets

Curl:

```bash
GET "http://localhost:8080/api/tickets" 
```

Respuesta esperada (ejemplo):

```json
[
  {
    "id": 1,
    "titulo": "No hay acceso a Internet",
    "descripcion": "Usuarios del piso 3 sin conectividad desde las 08:30",
    "categoria": "Red",
    "prioridad": "Alta",
    "estado": "Abierto"
  }
]
```

## 2) GET — Obtener ticket por id

Curl (ejemplo id 2):

```bash
GET "http://localhost:8080/api/tickets/2"
```

Respuesta esperada (ejemplo):

```json
{
  "id": 2,
  "titulo": "Impresora oficina B atascada",
  "descripcion": "Impresora HP cola de trabajos se queda en estado 'imprimiendo'",
  "categoria": "Hardware",
  "prioridad": "Media",
  "estado": "Abierto"
}
```

## 3) POST — Crear tickets (3 ejemplos)

Ticket 1:

```bash
POST "http://localhost:8080/api/tickets" 
{
    "titulo": "No hay acceso a Internet",
    "descripcion": "Usuarios del piso 3 sin conectividad desde las 08:30",
    "categoria": "Red",
    "prioridad": "Alta",
    "estado": "Abierto"
}
```

Ticket 2:

```bash
POST "http://localhost:8080/api/tickets"
{
    "titulo": "Impresora oficina B atascada",
    "descripcion": "Impresora HP cola de trabajos se queda en estado 'imprimiendo'",
    "categoria": "Hardware",
    "prioridad": "Media",
    "estado": "Abierto"
  }
```

Ticket 3:

```bash
POST "http://localhost:8080/api/tickets"
{
    "titulo": "Error en aplicación contable",
    "descripcion": "Al guardar una factura aparece NullPointerException",
    "categoria": "Software",
    "prioridad": "Alta",
    "estado": "Abierto"
  }
```

Ejemplo de body JSON (para usar en Postman -> Body -> raw -> JSON):

```json
{
  "titulo": "No hay acceso a Internet",
  "descripcion": "Usuarios del piso 3 sin conectividad desde las 08:30",
  "categoria": "Red",
  "prioridad": "Alta",
  "estado": "Abierto"
}
```

## 4) PUT — Actualizar ticket (reemplazo completo)

Actualizar el ticket con `id = 3`:

```bash
PUT "http://localhost:8080/api/tickets/3"
{
    "titulo": "Error en aplicación contable - seguimiento",
    "descripcion": "Se aplicó parche, pendiente verificación",
    "categoria": "Software",
    "prioridad": "Alta",
    "estado": "En Progreso"
}
```

Respuesta esperada (ejemplo): objeto JSON actualizado.

## 5) DELETE — Eliminar ticket

Eliminar `id = 1`:

```bash
DELETE "http://localhost:8080/api/tickets/1"
```

Respuesta esperada: `204 No Content` o JSON confirmando eliminación.

---

Consejos:
- Asegúrate que la aplicación esté corriendo en `http://localhost:8080`.
- Si tu API usa prefijo `/api`, las rutas arriba mostradas ya lo incluyen.
- En Postman define `Content-Type: application/json` para los POST y PUT.

Si quieres, creo también la colección Postman con estas 7 peticiones y la guardo en `backend-api/docs/postman_collection.json`.
