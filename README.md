# TP Inmobiliaria

Aplicación móvil Android desarrollada en Java para la gestión integral de inmuebles, contratos, inquilinos y propietarios mediante el consumo de una API REST.

## Características Implementadas

### Autenticación

* Login de propietarios.
* Persistencia de sesión mediante token JWT.
* Llamada telefónica directa desde la pantalla de Login.

### Inmuebles

* Listado de inmuebles pertenecientes al propietario.
* Visualización de imagen principal de cada inmueble.
* Detalle completo del inmueble.
* Cambio de disponibilidad (Disponible / No Disponible).

### Contratos

* Listado de contratos vigentes.
* Visualización de fechas de inicio y finalización.
* Detalle completo del contrato.
* Acceso a información del inquilino asociado.

### Inquilinos y Garantes

* Listado de inmuebles actualmente alquilados.
* Visualización de datos completos del inquilino.
* Visualización de datos del garante asociado (en xml).
* Pantalla de detalle con información organizada y de fácil acceso.

### Pagos

* Listado de pagos realizados para cada contrato.
* Visualización de fecha, número de pago e importe.

### Perfil

* Visualización de datos personales del propietario.
* Edición de perfil.
* Cambio de contraseña independiente.

### Mapas

* Integración con Google Maps.
* Visualización de ubicación de los inmuebles.
* Vista híbrida.
* Efecto de inclinación 3D.

### Sensores

* Implementación de acelerómetro del dispositivo.
* Interacción mediante hardware del teléfono.

### Arquitectura y Tecnologías

* Java
* Android Studio
* Fragments
* ViewModel
* LiveData
* RecyclerView
* Retrofit
* API REST
* Google Maps SDK
* Material Design
* JWT Authentication
* SensorManager (Acelerómetro)

## Distribución de Tareas

### Lourdes Villegas

* Login
* Integración con API REST
* Perfil del propietario
* Google Maps
* Vistas
* Pagos

### Milena Miselli

* Inmuebles
* Detalle de inmuebles
* Cambio de disponibilidad
* Cambio de contraseña
* Inquilinos
* Interfaz de usuario y validaciones
* Llamada en el login

## Funcionalidades Finales

✔ Login seguro mediante JWT
✔ Gestión completa de inmuebles
✔ Visualización de contratos vigentes
✔ Consulta de pagos realizados
✔ Visualización de inquilinos y garantes
✔ Edición de perfil del propietario
✔ Cambio de contraseña
✔ Integración con Google Maps
✔ Vista híbrida y efecto 3D
✔ Carga y visualización de imágenes
✔ Consumo de API REST
✔ Uso de sensores del dispositivo

## Autoras

**Villegas María Lourdes**
DNI: 46.332.709

**Miselli Milena**
DNI: 44.164.036
