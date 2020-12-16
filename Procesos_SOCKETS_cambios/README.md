## App Biblioteca cliente-servidor Sockets
App para gestión de libros de una biblioteca virtual

En el menú del cliente aparece:
- Consulta libro por ISBN. Introduce 1.
- Consulta libro por titulo. Introduce 2.
- Consulta libro por autor. Introduce 3.
- Añadir libro a la biblioteca. Introduce 4.
- Salir de la aplicación. Introduce EXIT.

Los libros tienen un ISBN (único), un título (único), un autor (puede haber varios) y un precio (puede haber varios) 
y se encuentran alojados en el servidor. 
El servidor tiene 5 libros preestablecidos con todos los datos rellenos. Los libros se guardan en memoria 
en una lista. 
En la **opcion3** se consulta libros por autor, teniendo en cuenta que pueden aparecer varios libros del mismo autor, y que un libro puede tener varios autores.
En la **opcion4** el cliente pide todos los datos del libro a añadir, y se envian al servidor para que los dé de alta y los guarde en un array.
El servidor está preparado para que interactúen con él varios clientes.
El cliente se limita a enviar la petición al servidor, y el servidor contesta en caso de que encuentre algún tipo de coincidencia.
