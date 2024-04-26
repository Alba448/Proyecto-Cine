/**
 * @author Alba Garcia
 */
-- Tabla de Complementos
CREATE TABLE IF NOT EXISTS ComplementoEntity (
    id INTEGER PRIMARY KEY,
    bebida TEXT,
    comida TEXT ,
    otros TEXT ,
    stock TEXT NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER NOT NULL DEFAULT 0
);

-- Tabla de Butaca
CREATE TABLE IF NOT EXISTS ButacaEntity (
    id INTEGER PRIMARY KEY,
    fila TEXT NOT NULL,
    columna TEXT NOT NULL,
    estado TEXT NOT NULL,
    ocupacion TEXT NOT NULL,
    tipo TEXT NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER NOT NULL DEFAULT 0
);

-- Tabla de Socios
CREATE TABLE IF NOT EXISTS SociosEntity (
    id INTEGER PRIMARY KEY,
    nombre TEXT NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER NOT NULL DEFAULT 0
);

-- Tabla de Lineas de Ventas
CREATE TABLE IF NOT EXISTS LineaVentaEntity (
    id TEXT PRIMARY KEY,
    venta_id TEXT NOT NULL REFERENCES VentaEntity(id),
    butaca_id TEXT NOT NULL REFERENCES ButacaEntity(id),
    complemento_id INTEGER NOT NULL REFERENCES Complemento(id),
    cantidad INTEGER NOT NULL,
    precio REAL NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER NOT NULL DEFAULT 0
);

-- Tabla de Ventas
CREATE TABLE IF NOT EXISTS VentaEntity (
    id TEXT PRIMARY KEY,
    socios_id INTEGER NOT NULL REFERENCES SociosEntity(id),
    total REAL NOT NULL,
    created_at TEXT NOT NULL,
    updated_at TEXT NOT NULL,
    is_deleted INTEGER NOT NULL DEFAULT 0
);