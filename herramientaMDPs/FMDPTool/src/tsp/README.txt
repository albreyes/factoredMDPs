Traductor sintáctico de Sistemas de planificación.
----------------------------------------------------

El traductor incorpora: 

Un analizador de la especificación generada en SPI.
Una interfaz gráfica de usuario.
Un traductor sintáctico.

Un analizador de la especificación generada en SPI.
-------------------------------------------------------------
El analizador incorpora funciones para realizar un análisis en 
el archivo de especificación de entrada (MDP SPI), que sirve para
detectar errores, en la interfaz gráfica se puede desplegar un
String que contiene los errores que han sido detectados durante el
análisis.

La fase de análisis de la función de reward ha sido diseñada de manera
sencilla, se puede realizar un mejor análisis para dectectar un mayor 
número de errores.

El analizador también realiza funciones para configurar el archivo de una
forma en que los elementos del archivo estén completos, asigna elementos por
default y realiza correcciones en el archivo en algunos casos.

Una interfaz gráfica de usuario.
--------------------------------------------------------------
La interfaz sirve como vínculo con el usuario para hacer uso de las
funciones.

Un traductor sintáctico.
--------------------------------------------------------------
El traductor sintáctico es la pieza fundamental de este sistema, cuenta con 
las funciones necesarias para poder traducir la especificación de SPI a SPUDD.
 





