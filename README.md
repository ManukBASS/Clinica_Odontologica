# Clinica_Odontologica 🏥

Este proyecto simula una Clinica Odontológica, en la cual se pueden crear y modificar los datos tanto de Odontologos como de Pacientes y Turnos. \
Para el Backend trabajé con Java, utilizando Spring Boot como framework. \
Para el Frontend trabajé con React Js, consumiendo los endpoints que proporciona la API creada en el backend.

## Backend:

El backend del proyecto se creó utilizando el framework Spring Boot y en el mismo se encuentran todas las clases necesarias para crear y utilizar una API, la cual se puede manejar mediante Postman. 
En la misma se guardan los datos de tres entidades:

  -Pacientes (con datos relevantes para la clinica) \
  -Odontologos (con datos relevantes para la clinica) \
  -Turnos (que integra el ID de un paciente y un odontologo junto a la fecha del turno)
  
Mediante el uso de Controllers y Endpoints se pueden obtener los datos en formato JSON de cada entidad

![Datos en Postman](https://i.postimg.cc/jdGz2H2t/postman-clinica.jpg)


## Frontend:

Creado con React, el frontend de este proyecto nos permite gestionar la lista de pacientes utilizando métodos:

-GET: `http://localhost:8080/pacientes/listar` para renderizar en pantalla los pacientes existentes en la API.

-POST: `http://localhost:8080/pacientes/agregar` para crear un nuevo paciente mediante un formulario.

-PATCH: `http://localhost:8080/pacientes/modificarPaciente` para modificar uno o más datos del paciente elegido.

-DELETE: `http://localhost:8080/pacientes/eliminar/` para eliminar un paciente.

![Página del Proyecto](https://i.postimg.cc/8zRTWGps/work-clinica.jpg)
