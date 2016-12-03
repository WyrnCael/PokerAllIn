# Poker-All-In

Repositorio para el desarrollo de las practicas de la asignatura "Herramientas Informaticas para los Juegos de Azar
". Se pretende desarrollar una herramienta agil y agradable que permita el estudio de los juegos de azar.

## Practica2: It's all about the range

El objetivo consiste en calcular y representar **rangos** en el juego NLHE. 

###Uso:

Para la entrada de datos, los valores de los rangos estaran representados por pares de los siguientes caracteres: A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2 y separado por comas; cuando los dos caracteres del par no son parejas, existe tercera caracter representado por: s(suited) u o(offSuited). Por ejemplo: AA, JJ, ATs, 76o.

Utilizar el operator **"+"** cuando:
* El rango es una **pareja**(misma caracter): El rango resultante seran todos los rangos superiores al rango indicado
* El rango **no** es una pareja(diferente caracter): El rango resultante seran todos los rangos superriores al rango indicado, tomando valor fijo el primer caracter y todos los valores superior del segundo caracter teniendo en cuenta suited u offsuited.
Por ejemplo: JJ+, T2s+, 52o+.

Utilizar el operator **"-"** para representar un intervalor entre dos rangos, por ejemplo: ATs-A2s.

La herramienta existe 3 panel:
* **Panel de los rangos**: representar los rangos en grafico.verde: pareja; rojo: suited; gris: offsuited.
* **Panel del control**: cambiar la forma de introducir los datos de entrada segun el apartado.
* **panel de los combos**: representar en grafico los posibles combos que puedan formar con el rango y carta en board.

<p align="center">
  <img src="http://i.imgur.com/PMEPOT6.jpg"/>
</p>

Ejemplo de salida:

<p align="center">
  <img src="http://i.imgur.com/DfDToKi.jpg"/>
</p>

###Funcionalidades actuales:

* Calcular un rango textual, representar en grafico.

<p align="center">
  <img src="http://i.imgur.com/Hh1FIQd.png"/>
</p>

* Seleccionar los rangos en grafico, calcular su rango textual.

<p align="center">
  <img src="http://i.imgur.com/yDXBh5g.png"/>
</p>

* Dado un porcentaje, calcular el rango y representar en grafico.

<p align="center">
  <img src="http://i.imgur.com/6LPXRyX.png"/>
</p>

* Dado un rango y con 3, 4 o 5 carta en board, calcular sus posibles cambos y draws.

<p align="center">
  <img src="http://i.imgur.com/CBhd4jJ.png"/>
</p>

###Ideas por desarrollar:

- [x] Cambiar readme
- [ ] Especificar los contenidos que forman cada combo.
