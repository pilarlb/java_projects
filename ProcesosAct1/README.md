## Programa ¿Es un número primo?

Se piden 4 números por pantalla y se determina cuál de ellos es primo y cual no. 
Por cada número se lanza un hilo de ejecucion (thread) en el programa, 
para que se procese cada numero en paralelo.

Cada vez que un hilo acabe, se muestra por pantalla el numero procesado,
el nombre del hilo que lo ha procesado, 
el tiempo que ha tardado en procesar el número y si es primo o no.

Se pregunta al usuario si se quiere guardar los resultados en un fichero por cada hilo.
Estos ficheros una vez creados, no se sobreescriben, para poder llevar 
un registro de todas las veces que se ejecutó el programa.
