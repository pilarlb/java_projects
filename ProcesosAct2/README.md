## Programa simulacion procesamiento Emails

Los emails se crean con id, un destinatario, un remitente, un asunto y un cuerpo del mensaje.
Éstos se depositan en un buffer que tiene una capacidad de 5 emails como máximo.

Hay 3 hilos que producen 10 emails cada uno, cada uno de ellos 
con un destinatario, un remitente, un asunto y un cuerpo de mensaje diferente. Estos hilos 
ponen los emails en el buffer cada 0,5 segundos; cada vez que se introduzca un mail en el buffer, se
imprime por pantalla el nombre y el id del email metido.

También hay 2 hilos que consumen los emails del buffer siempre 
que haya email disponible. Cada vez que un consumidor coja un email, 
simulará su envio simplemente imprimiendo por pantalla los datos del email, 
así como el nombre del hilo consumidor que ha cogido dicho email.
