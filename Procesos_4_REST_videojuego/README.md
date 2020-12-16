## APP REST VIDEOJUEGOS

Los videojuegos tienen un ID (único), un nombre (único), una compañía (puede haber varias) 
y una nota (puede haber varias) y se encuentran alojados en el servidor REST. 
Hay validación para que no haya dos videojuegos con ID o nombre repetido.

El servidor tiene 5 videojuegos preestablecidos con todos los datos rellenos en el arranque. 
Los videojuegos se guardan en una lista.

El servicio REST proporcionaun servicio CRUD completo, y puede ser consumido mediante un cliente 
como Postman y todo el intercambio de mensajes se realiza través de JSON.

Con Postman se puede:
- Dar de alta un videojuego
- Dar de baja un videojuego
- Modificar un videojuego por ID
- Obtener un videojuego por ID
- Listar todos los videojuegos

Además, hay una app en cliente con el menú:
- Consultar videojuego por id
- Consultar videojuegos por compañía
- Alta de videojuego
- Salir
