# BikeApp - Api Rest de Spring Boot 3

## Descripción del Proyecto

**BikeApp** es una API REST desarrollada en Spring Boot 3 que proporciona endpoints para gestionar las entidades relacionadas con un sistema de gestión de reserva de bicicletas. 
La API cuenta con dos tipos de usuarios con diferentes permisos: 
- Usuario Bici: Este usuario será el cliente de la aplicación que podrá, tanto darse de alta, como iniciar sesión si ya existe anteriormente en la base de datos. Sus endpoints son:
  
  - Controller *USO*: 
    - `POST ("/bikes/rent/{idBicicleta}")` - El usuario puede alquilar una bicicleta en el caso de que esta esté disponible
    - `GET ("/use/last-use")` - El usuario obtiene los detalles de su último viaje en caso de que tenga alguno
    - `GET ("/use/active")` - El usuario obtiene los detalles de el viaje que tenga activo, si fuera el caso
    - `POST ("/use/finish/{idEstacion}")` - Finaliza el viaje que el usuario tiene activo

  - Controller *User*: 
    - `GET ("/user/details")` - Devuelve los detalles del usuario que está logeado
    - `PUT ("/user/recharge")` - El usuario puede realizar una recarga de saldo
    - `GET ("/user/{id}")` - Se obtienen los datos del usuario proporcionado en el id
    - `GET ("/use/{id}?page=0")` - Obtiene el historial de uso del usuario proporcionado en el id
    - `PUT ("/user/setCard/{id}")` - Permite agregar una tarjeta para añadir saldo y crear un pin para futuras recargas de saldo
   
  - Controller *Coste*: 
    - `GET ("/cost/current")` - Permite ver el precio/minuto actual de la plataforma

  - Controller *Bicicleta*: 
    - `GET ("/bikes/station{idEstacion}/bikes")` - Permite ver las bicicletas disponibles en la estación proporcionada
    - `GET ("/bikes/{id}")` - Permite ver los detalles de una bicicleta

  - Controller *Estación*: 
    - `GET ("/station/get/{id}")` - Permite ver las la información de una estación
    - `GET ("/station/get")` - Permite ver todas las estaciones


- Admin: Este usuario será el administrador del programa por lo que dispondrá de endpoints con peticiones CRUD, además de otras, para gestionar la API correctamente:

  - Controller *USO*: 
    - `GET ("/admin/travels?page=0")` - Obtiene una lista de los viajes realizados, tanto finalizados como en proceso
    - `PUT ("/admin/edit-use")` - Permite editar única y exclusivamente el coste de un viaje, cuando esté ya se ha finalizado anteriormente. Se gestiona de esta manera para que no se puedan manipular datos, que no deberían ser equívocos, asi como tampoco se puede eliminar un uso.

  - Controller *Revisión*: 
    - `GET ("/admin/issues/?page=0")` - Devuelve una lista de revisiónes realizadas
    - `PUT ("/admin/issues/{id}")` - Se puede editar una revisión obteniendo su id
    - `DELETE ("/user/{id}")` - Permite borrar una revisión
    - `POST ("/admin/issues")` - Permite agregar una revisión
   
  - Controller *Bicicletas*: 
    - `POST ("/admin/bikes/add")` - Permite agregar una bicicleta
    - `GET ("/admin/bikes/edit/{nombreBicicleta}")` - Permite editar una bicicleta
    - `GET ("/admin/bikes")` - Permite ver una lista de bicicletas
    - `GET ("/admin/bikes/paged")` - Permite ver una lista de bicicletas paginada
    - `DELETE ("/admin/bikes/delete/{nombreBicicleta}")` - Permite borrar una bicicleta

  - Controller *Estación*: 
    - `POST ("/admin/add")` - Permite agregar una estación
    - `DELETE ("/admin/delete/{numeroEstacion}")` - Permite borrar una estación
    - `PUT ("/admin/edit/4")` - Permite editar una estación
    - `GET ("/admin/station")` - Permite ver una lista de estaciones


- Por último, tenemos los endpoints de registro e inicio de sesión que no requieren de autenticación:
    - `POST ("/auth/login")` - Login tanto para admin como para user
    - `POST ("/auth/register")` - Registro de Usuarios

## Cómo Desplegar o Arrancar en Localhost

Para ejecutar la aplicación en tu entorno local, sigue estos pasos:

1. **Clona el Repositorio:**
   ```bash
   git clone https://github.com/Junior-FullStacks-By-Salesianos/appbike-back.git
   cd appbike-back
   ```
   
2. **Haz docker compose:**
   Inicia Docker para poder arrancar la base de datos en un contenedor y seguido esto ejecutamos este comando
   ```bash
   docker-compose up -d
   ```
   
4. **Inicia la Aplicación:**
   ```bash
   cd appbike
   ```
   Y dentro de nuestro IDE ejecutamos `mvn:spring-boot-run` y nuestra API REST estará funcionando en `http://localhost:8080`
   
5. **Para acceder a la base de datos**
   - Nos dirigiremos en nuestro navegador a `http://localhost:5050` e iniciaremos sesión con estas credenciales
     - Username: admin@admin.com
     - Password: admin
   - Podemos crear un servidor para producción y otro para desarrollo, para desarrollo tenemos estas credenciales:
     - host: pg_dev
     - username: postgres
     - password: 12345678
  - Para producción solo cambiará el host
     - host: pg_dev
     - username: postgres
     - password: 12345678

   - Por último podemos gestionar en el archivo application.properties, qué perfil de base de datos se usará: 
   `spring.profiles.active=dev`.
    De esta manera el perfil de base de datos utilizado es el de desarrollo y podemos cambiarlo a producción, sustituyendo `dev` por `prod`

6. **Credenciales**
   En el perfil de desarrollo se pueden hacer pruebas con estas cuentas o añadiendo más:
   - Admin:
     - username: admin
     - password: admin1
   - User (3 cuentas):
     - username: user1, user2, y user3
     - password: user1234

En el perfil de producción se pueden hacer pruebas con la cuenta de admin, que es la única añadida hasta el momento, así como tampoco hay datos de prueba; tampoco hay cuentas de usuario activas:
   - Admin:
     - username: admin
     - password: admin1
  
6. **Disfruta:**
   - Desde los contribuidores de este repositorio esperamos que puedas disfrutar de este proyecto :)
