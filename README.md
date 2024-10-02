# Prueba Inditex


## Índice


- [Tecnologia Utilizada](#tecnologia-utilizada)
- [Argumentación](#argumentación)
- [Autor](#autor)


## Tecnologia Utilizada

Se trata de un proyecto construído en java 17, con version de Spring Boot 3.1.0
- Se construye con open api para generar los objetos
- Swagger
- Lombok
- MapStruct
- Jacoco
- Manejador global de excepciones
- JPA
- H2 BBDD



## Argumentación

En la prueba se pide realizar un endpoint que devuelva los datos de una oferta cuyas fechas de inicio y finalización sean acordes con la fecha enviada en la request, y caso de que exista más de una oferta activa para la fecha enviada, devolver la de mayor prioridad.

En la petición se incluye la 'fecha', 'productId' y 'brandId'. Estos dos últimos son siempre los mismos. Existe tambien otro campo 'priceList'.

Hemos determinado que esos campos, aunque no usen y se podria simplificar el problema, no están ahi por nada y hemos decidido incluir una serie de tablas adicionales para futuras llamadas  que puedan incluir estas propiedades en sus request.

- PRECIOS
- PRODUCTOS
- OFERTAS (fechas)
- BRANDS
- LISTA_PRECIOS

El parámetro de entrada 'fecha' hemos establecido que sea un OffsetDateTime, que posteriormente convertiremos a UTC, para evitar el problema de fechas en distintas zonas horarias


## Autor

Agustin Muñoz Perez
- agustin.munoz.afincor@gmail.com

