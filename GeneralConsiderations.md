Anotaciones y consideraciones generales sobre los algoritmos de implementación de hardware y de solución de laberintos.


# Detalles #

## Señales y algoritmo ##

Primero que todo, necesitamos definir las señales que el algoritmo que soluciona laberitnos le va a mandar al módulo que hace mover al robot. Iniclamente pensé en unos generales que permitirían viajar por un laberinto pero a la larga se necesita saber cuál es el algoritmo que vamos a implementar para poder definir las señales específicas que necesitamos implementar en el robot. Me explico; las señales en general que serían necesarias para implementar de manera genérica cualquier algoritmo que recorra un laberinto serían:

### Movidas ###
```
goForward                 - Se desplaza hacia el frente
goBackwards               - Se desplaza hacia atrás
stop                      - Se detiene
turn90Clockwise           - Gira 90 grados en dirección de las manecillas del reloj
turn90Counterclockwise    - Gira 90 grados en contra de las manecillas del reloj
```
### Señales ###
```
isLine                    - Si el segmento en el que se encuentra es una linea recta
isLCorner                 - Si se encuentra con una esquina en forma de L
isTCross                  - Si se encuentra con un cruce en forma de T
isXCross                  - Si se encuentra con un cruce en forma de X
isExit                    - Identifica si se llegó a la salida
isDeadEnd                 - Identifica se se topa con un callejón sin salida
```

Ahora, si el algoritmo para el laberinto lo enfocamos a grafos (e.g. aplicar el algoritmo de Prim para identificar los nodos y luego Dijkstra o búsqueda en profundidad o `A*`) se necesitarían otras movidas como por ejemplo una que haga regresar al robot al nodo anterior. Basado en la experiencia de la primera práctica, no estoy muy seguro de la viabilidad de esta aproximación (el "equipo hardware" es el que tiene la última palabra).

Buscando otras formas que no están enfocadas a grafos y que no están en un ambiente discreto (i.g. no recorren una matriz de posiciones como es el caso en las implementaciones de los algoritmos de búsqueda en profundidad y en anchura en general), existe una posible solución que probablemente no haga que el robot solucione el laberinto muy rápidamente pero seguro que por lo menos podrá solucionarlo. Se llama el algoritmo Wall Flower, también conocido como el de la mano derecha o el de la mano izquierda. [Aqui explican en qué consiste.](http://en.wikipedia.org/wiki/Maze#Wall_follower)

Ahora, si basamos el trabajo en este algoritmo, todas las señales definidas arriba serían casi que inservibles puesto que el robot se dedica únicamente a seguir uno de los bordes del laberinto (en nuestro caso, al seguir una línea negra gruesa, no debe de haber problema, incluso si se usan sólo dos sensores).

**¿Qué opina el "equipo hardware" de todo esto?**
  * Deberíamos enfocarnos a solucionarlo por medio de grafos y definir más movidas y señales?
  * Vamos a la fija con el Wall Flower?