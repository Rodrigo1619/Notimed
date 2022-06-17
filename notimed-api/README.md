# Notimed API
Desarrollo backend en NodeJS con Typescript.

## Configuración
### Requisitos
- WSL 2
- Node 18.1.0
- Docker

### Pasos (sin Docker)

1. Instalar dependencias de Node `npm install`.
2. Para correr la app en modo de desarrollo, correr `npm run start:dev` 
3. Si se desea construir la app para producción:
    - Correr `npm run build` (se creara una carpeta .dist)
    - Correr la app en producción: `npm run start`  

### Pasos para montar contenedores e imagenes en Docker

1. Construir imagen(es) para producción o desarrollo
```
docker build -f Dockerfile.dev -t notimed/dev-api .
docker build -f Dockerfile.prod -t notimed/prod-api .
```

2. Montar contenedor para producción o desarrollo. 
    - puerto_contenedor para desarrollo: **3000**
    - puerto_contenedor para producción: **5000**
```
docker run -d -p <puerto_local>:<puerto_contenedor> --name <nombre_contenedor> <imagen>
```

## Información extra
Para la base de datos usamos MongoDB, en especifico [MongoDB Atlas](https://www.mongodb.com/es/atlas/database)
