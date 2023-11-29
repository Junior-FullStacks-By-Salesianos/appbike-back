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

6. **Credenciales**: Las credenciales que se pueden usar para probar la API REST:
- En el perfil de desarrollo se pueden hacer pruebas con estas cuentas o añadiendo más:
     - Admin:
       - username: admin
       - password: admin1
     - User (3 cuentas):
       - username: user1, user2, y user3
       - password: user1234

- En el perfil de producción se pueden hacer pruebas con la cuenta de admin, que es la única añadida hasta el momento, así como tampoco hay datos de prueba; tampoco hay cuentas de usuario activas:
   - Admin:
     - username: admin
     - password: admin1
  
6. **Disfruta:**
   - Desde los contribuidores de este repositorio esperamos que puedas disfrutar de este proyecto :)
  ###Informacion extra detallada
## Colección de Postman

Adjunto de la coleccion de postman para poder exportarla a su framework

**[BikeApp](https://api.postman.com/collections/30052626-0f50d748-26ab-4a2c-b025-a302cec28ea0?access_key=PMAT-01HGEK37V5912HWEEDQGWM0D6E)**
**[RestJWT](https://api.postman.com/collections/30052629-d013e8cb-d9f7-47a4-8d4c-8881e7dc2f70?access_key=PMAT-01HGEK7E757CA5MG4ZHPFR0PZD)**

Para poder utilizar el POSTMAN, primero se deberá iniciar sesión con un usuario o con admin en la coleccion BIKEAPP en la carpeta wo-auth
![image](https://github.com/Junior-FullStacks-By-Salesianos/appbike-back/assets/114216690/86aedb0e-4448-4484-b0bc-103f888959b8)
Para el user
![image](https://github.com/Junior-FullStacks-By-Salesianos/appbike-back/assets/114216690/43a4dec9-d5a7-43fe-8eaa-8f1568eb46e6)


---
## Estación Controller

### Descripción
Este controlador maneja las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las estaciones en la API. Proporciona endpoints para agregar, obtener, editar y eliminar estaciones de bicicletas.

### Endpoints Principales

- **POST /admin/add**
  - *Descripción*: Agrega una nueva estación.
  - *Requiere Autenticación*: Rol de administrador.
  - *Ejemplo de Cuerpo de Solicitud*: _(Ver ejemplo en la descripción del código)_ 
  - *Respuesta Exitosa*: Código 201 - La estación se ha creado correctamente.

- **GET /station/get**
  - *Descripción*: Obtiene una lista de todas las estaciones disponibles.
  - *Ejemplo de Respuesta*: _(Ver ejemplo en la descripción del código)_

- **GET /station/get/{id}**
  - *Descripción*: Obtiene información sobre una estación específica por su ID.
  - *Ejemplo de Respuesta*: _(Ver ejemplo en la descripción del código)_

- **PUT /admin/edit/{id}**
  - *Descripción*: Edita una estación existente por su ID.
  - *Requiere Autenticación*: Rol de administrador.
  - *Ejemplo de Cuerpo de Solicitud*: _(Ver ejemplo en la descripción del código)_
  - *Respuesta Exitosa*: Código 200 - La estación se ha editado correctamente.

- **DELETE /admin/delete/{id}**
  - *Descripción*: Elimina una estación por su ID.
  - *Requiere Autenticación*: Rol de administrador.
  - *Respuesta Exitosa*: Código 204 - La estación se ha eliminado correctamente, si no hay bicicletas asociadas.

### Autenticación y Autorización
- Los endpoints de modificación (`POST`, `PUT`, `DELETE`) requieren autenticación como administrador.

### Respuestas y Excepciones
- Los endpoints responden con códigos de estado apropiados y manejan excepciones como la falta de estaciones o bicicletas asociadas a una estación que se intenta eliminar.

### Notas Importantes
- Se utilizan DTOs (Objetos de Transferencia de Datos) para representar la información de las estaciones en las respuestas y solicitudes.
- Se siguen las prácticas de documentación de Swagger para describir las respuestas y ejemplos de los endpoints.


## Bicicleta Controller

### Descripción
Este controlador maneja todas las solicitudes relacionadas con bicicletas en la API. Proporciona endpoints para buscar, crear, editar y eliminar bicicletas.

### Endpoints Principales

- **GET /admin/bikes/paged**
  - *Descripción*: Obtiene una lista paginada de bicicletas.
  - *Respuesta*: Código 200 - Todas las bicicletas encontradas.

- **GET /admin/bikes**
  - *Descripción*: Obtiene una lista de bicicletas sin paginación.
  - *Respuesta*: Código 200 - Todas las bicicletas encontradas.

- **GET /bikes/station/{idEstacion}/bikes**
  - *Descripción*: Obtiene una lista de bicicletas de una estación específica.
  - *Respuesta*: Código 200 - Todas las bicicletas encontradas en la estación.

- **GET /bikes/{uuid}**
  - *Descripción*: Obtiene una bicicleta por su ID.
  - *Respuesta*: Código 200 - La bicicleta encontrada.

- **POST /admin/bikes/add**
  - *Descripción*: Crea una nueva bicicleta.
  - *Respuesta*: Código 201 - La bicicleta ha sido creada.

- **PUT /admin/bikes/edit/{nombre}**
  - *Descripción*: Edita una bicicleta existente por su nombre.
  - *Respuesta*: Código 200 - La bicicleta ha sido editada.

- **DELETE /admin/bikes/delete/{name}**
  - *Descripción*: Elimina una bicicleta por su nombre.
  - *Respuesta*: Código 204 - La bicicleta ha sido eliminada.

- **GET /admin/bikes/available/{nombre}**
  - *Descripción*: Verifica la disponibilidad de una bicicleta.
  - *Respuesta*: Código 200 - La bicicleta está disponible.

### Notas Adicionales
- Se utilizan DTOs (Objetos de Transferencia de Datos) para manejar la información de las bicicletas.
- Los métodos están documentados con las respuestas esperadas y los ejemplos correspondientes.

## Coste Controller

### Descripción
Este controlador maneja las solicitudes relacionadas con los costes en la API. Proporciona un endpoint para obtener el costo actual por minuto.

### Endpoints Principales

- **GET /cost/current**
  - *Descripción*: Obtiene el costo actual por minuto.
  - *Respuesta*: Código 200 - El costo actual ha sido encontrado.

### Notas Adicionales
- Este controlador utiliza el DTO (Objeto de Transferencia de Datos) `CosteResponse` para manejar la información del costo.
- El método está documentado con la respuesta esperada y un ejemplo correspondiente.

## Revision Controller

### Descripción
Este controlador maneja las solicitudes relacionadas con las revisiones en la aplicación. Ofrece funcionalidades para obtener, crear, editar y eliminar revisiones.

### Endpoints Principales

- **GET /admin/issues/**
  - *Descripción*: Obtiene una lista paginada de revisiones.
  - *Respuesta*: Código 200 - Devuelve una lista paginada de revisiones con detalles como fecha programada, fecha de realización, anotaciones, estado, estación asociada y trabajador.

- **POST /admin/issues/**
  - *Descripción*: Crea una nueva revisión.
  - *Respuesta*: Código 200 - Crea una nueva revisión con los detalles proporcionados en el cuerpo de la solicitud.

- **PUT /admin/issues/{id}**
  - *Descripción*: Edita una revisión existente.
  - *Respuesta*: Código 200 - Edita una revisión existente basada en el ID proporcionado en la URL y los detalles proporcionados en el cuerpo de la solicitud.

- **DELETE /admin/issues/{id}**
  - *Descripción*: Elimina una revisión existente.
  - *Respuesta*: Código 204 - Elimina una revisión basada en el ID proporcionado en la URL.

### Notas Adicionales
- Este controlador utiliza los DTOs (Objetos de Transferencia de Datos) `NewRevisionDTO` y `RevisionDTO` para manejar la información de las revisiones.
- Cada endpoint está documentado con la respuesta esperada y ejemplos correspondientes para una mejor comprensión del resultado.
## Trabajador Controller

### Descripción
Este controlador maneja las operaciones relacionadas con los trabajadores de la aplicación. Proporciona funcionalidades para obtener todos los trabajadores registrados.

### Endpoints Principales

- **GET /worker/**
  - *Descripción*: Obtiene una lista de todos los trabajadores.
  - *Respuesta*: Código 200 - Devuelve una lista de objetos `TrabajadorDTO` con detalles como ID, correo electrónico y nombre de cada trabajador.

### Notas Adicionales
- Este controlador utiliza el servicio `TrabajadorService` para obtener la información de los trabajadores y los transforma en objetos `TrabajadorDTO` para su representación.
- Está documentado para proporcionar información sobre la respuesta esperada y ejemplos correspondientes para una mejor comprensión del resultado.


## User Controller

### Descripción
Este controlador administra las operaciones relacionadas con los usuarios, incluyendo el registro y el inicio de sesión. Utiliza el servicio `UsuarioBiciService` para manejar la lógica de registro y autenticación de usuarios.

### Endpoints Principales

- **POST /auth/register**
  - *Descripción*: Registra un nuevo usuario con información relacionada con la bicicleta.
  - *Respuesta*: Código 201 - Retorna un objeto `JwtUserResponse` con detalles del usuario recién registrado y un token de autenticación.

- **POST /auth/login**
  - *Descripción*: Inicia sesión para obtener un token de autenticación.
  - *Respuesta*: Código 201 - Retorna un objeto `JwtUserResponse` con detalles del usuario autenticado y un token de autenticación.

### Notas Adicionales
- Las solicitudes de registro y inicio de sesión están validadas con anotaciones `@Valid`.
- Utiliza `JwtProvider` y `AuthenticationManager` para manejar la autenticación y generación de tokens JWT.
- Los métodos están documentados utilizando anotaciones `@Operation` y `@ApiResponse` para describir claramente el propósito y las respuestas esperadas de cada endpoint.


## Uso Controller

### Descripción
Este controlador gestiona las operaciones relacionadas con los usos de bicicletas. Permite el alquiler de bicicletas, finalización de usos, búsqueda de usos activos, y la edición y recuperación de información sobre usos pasados.

### Endpoints Principales

- **POST /bikes/rent/{idBicicleta}**
  - *Descripción*: Permite alquilar una bicicleta para su uso.
  - *Respuesta*: Código 201 - Retorna detalles del uso recién creado.

- **GET /use/last-use**
  - *Descripción*: Obtiene el último uso finalizado de un usuario.
  - *Respuesta*: Retorna detalles del último uso finalizado.

- **GET /use/active**
  - *Descripción*: Obtiene un uso activo de un usuario.
  - *Respuesta*: Retorna detalles del uso activo.

- **POST /use/finish/{idEstacion}**
  - *Descripción*: Finaliza un uso activo de un usuario.
  - *Respuesta*: Código 201 - Retorna detalles del uso finalizado.

- **GET /use/{userId}**
  - *Descripción*: Obtiene una lista paginada de usos de un usuario específico.
  - *Respuesta*: Retorna una página con los detalles de los usos del usuario.

- **GET /admin/travels**
  - *Descripción*: Obtiene una lista paginada de todos los usos.
  - *Respuesta*: Retorna una página con detalles de todos los usos.

- **PUT /admin/edit-use/{id}**
  - *Descripción*: Edita el costo de un uso específico.
  - *Respuesta*: Retorna los detalles del uso editado.

### Notas Adicionales
- Utiliza el servicio `UsoService` para manejar la lógica relacionada con los usos de bicicletas.
- Los métodos están documentados utilizando anotaciones `@Operation` y `@ApiResponse` para describir claramente el propósito y las respuestas esperadas de cada endpoint.
- Ofrece funcionalidades para alquilar, finalizar y consultar detalles de usos de bicicletas, tanto activos como pasados.

## UsuarioBici Controller

### Descripción
Este controlador administra todas las solicitudes relacionadas con los usuarios de bicicletas. Ofrece operaciones para obtener detalles de usuarios, recargar el saldo, y configurar la tarjeta de un usuario.

### Endpoints Principales

- **GET /user/details**
  - *Descripción*: Obtiene los detalles del usuario autenticado.
  - *Respuesta*: Retorna los detalles del usuario, incluyendo su saldo.

- **GET /user/{id}**
  - *Descripción*: Obtiene los detalles de un usuario por su ID.
  - *Respuesta*: Retorna los detalles del usuario correspondiente al ID.

- **PUT /user/recharge**
  - *Descripción*: Recarga el saldo del usuario autenticado.
  - *Respuesta*: Retorna los detalles del usuario actualizados después de la recarga.

- **PUT /user/setCard/{id}**
  - *Descripción*: Configura la tarjeta de un usuario por su ID.
  - *Respuesta*: Retorna los detalles del usuario actualizados con la tarjeta configurada.

### Notas Adicionales
- Utiliza el servicio `UsuarioBiciService` para manejar la lógica relacionada con los usuarios de bicicletas.
- Los métodos están documentados utilizando anotaciones `@Operation` y `@ApiResponse` para describir claramente el propósito y las respuestas esperadas de cada endpoint.
- Ofrece funcionalidades para obtener detalles de usuarios, recargar saldo y configurar la tarjeta de un usuario.
# Manejo de Errores en AppBike

Este proyecto utiliza un controlador global `GlobalRestControllerAdvice` para manejar y responder a una variedad de excepciones que pueden ocurrir durante la ejecución de la aplicación AppBike.

## Controlador: GlobalRestControllerAdvice

### Manejo de Errores de Validación - Método: `handleMethodArgumentNotValid`

#### Descripción:
Este método maneja errores de validación cuando los argumentos de los métodos no son válidos.

#### Flujo de Trabajo:
- Captura MethodArgumentNotValidException y extrae los errores de validación.
- Crea una respuesta estandarizada (ErrorResponse) con detalles del error de validación y los errores de campo asociados.

### Manejo de EntityNotFoundException - Método: `handleNotFoundException`

#### Descripción:
Maneja excepciones cuando una entidad no es encontrada.

#### Flujo de Trabajo:
- Captura EntityNotFoundException y crea una respuesta ErrorResponse indicando que la entidad no fue encontrada.

### Manejo de Excepciones de Uso - Método: `handleAlreadyInUseException`

#### Descripción:
Maneja excepciones cuando un recurso está en uso.

#### Flujo de Trabajo:
- Captura InUseException y devuelve una respuesta ErrorResponse indicando que el recurso ya está en uso.

### Manejo de Excepciones de Credenciales Inválidas - Método: `handleInvalidCredentialsException`

#### Descripción:
Maneja excepciones cuando las credenciales de inicio de sesión son inválidas.

#### Flujo de Trabajo:
- Captura InvalidCredentialsException y devuelve una respuesta ErrorResponse indicando credenciales incorrectas.

### Manejo de Excepciones de Autenticación - Método: `handleAuthenticationException`

#### Descripción:
Maneja excepciones relacionadas con problemas de autenticación.

#### Flujo de Trabajo:
- Captura AuthenticationException y devuelve una respuesta ErrorResponse indicando un problema de autenticación.

### Manejo de Excepciones de Acceso Denegado - Método: `handleAccessDeniedException`

#### Descripción:
Maneja excepciones cuando se deniega el acceso a un recurso.

#### Flujo de Trabajo:
- Captura AccessDeniedException y devuelve una respuesta ErrorResponse indicando acceso denegado.

### Manejo de Excepciones de Token JWT - Método: `handleTokenException`

#### Descripción:
Maneja excepciones relacionadas con problemas en el token JWT.

#### Flujo de Trabajo:
- Captura JwtTokenException y devuelve una respuesta ErrorResponse indicando un token inválido.

### Manejo de Excepciones de Usuario no Encontrado - Método: `handleUserNotExistsException`

#### Descripción:
Maneja excepciones cuando un usuario no es encontrado.

#### Flujo de Trabajo:
- Captura UsernameNotFoundException y devuelve una respuesta ErrorResponse indicando que el usuario no existe.

### Manejo de Excepciones de PIN Inválido - Método: `handleInvalidPin`

#### Descripción:
Maneja excepciones cuando se proporciona un PIN inválido.

#### Flujo de Trabajo:
- Captura InvalidPinExcepcion y devuelve una respuesta ErrorResponse indicando un PIN inválido.

### Manejo de Excepciones de Saldo Insuficiente - Método: `handleNotEnoughBalance`

#### Descripción:
Maneja excepciones cuando el saldo es insuficiente.

#### Flujo de Trabajo:
- Captura NotEnoughBalanceException y devuelve una respuesta ErrorResponse indicando saldo insuficiente.

### Manejo de Excepciones de Estación con Bicicletas - Método: `handleBikesInThatStationException`

#### Descripción:
Maneja excepciones cuando no se puede eliminar una estación porque tiene bicicletas.

#### Flujo de Trabajo:
- Captura BikesInThatStationException y devuelve una respuesta ErrorResponse indicando que la estación no puede ser eliminada.

### Manejo de Otras Excepciones Relacionadas con Bicicletas - Método: `handleBadRequestBikeAdd`, `handleBikeWithSameNameException`, `handleStationWithoutCapacityException`, `handleNameOfBikeNotFoundException`

#### Descripción:
Maneja excepciones específicas relacionadas con bicicletas y estaciones.

#### Flujo de Trabajo:
- Captura diferentes excepciones y devuelve una respuesta ErrorResponse específica para cada caso.

Este controlador proporciona un manejo centralizado de errores para capturar y responder consistentemente a una variedad de excepciones que pueden ocurrir en la aplicación. Utiliza objetos ErrorResponse para estructurar las respuestas de error, lo que ayuda a mantener coherencia y brindar información significativa sobre los problemas encontrados.

---



