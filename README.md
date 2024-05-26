Esta es la versión correcta de nuestra fase 2 del proyecto 1 de Algoritmos y Estructuras de Datos. A la hora de realizar el interprete de LISP realizamos dos versiones simultaneamente ya que no teníamos muy claro el concepto. La versión subida en Canvas fue subida incorrectamente al confundirnos en cual versión debía de ser empaquetada con Maven y subida. Esta versión es la correcta finalizada el 22 de Marzo de 2024.

El programa puede evaluar expresiones Lisp proporcionadas por el usuario mediante el archivo de lisp.txt

Operaciones Aritméticas: Permite realizar operaciones básicas como suma, resta, multiplicación y división entre números.
Comparaciones: Puede comparar valores numéricos para verificar si son iguales, mayores o menores que otros.
Verificación de Propiedades: Permite verificar si un valor es un átomo o una lista según la definición en Lisp.

¿Cómo funciona? El programa consta de varias clases que se encargan de diferentes aspectos de la evaluación de expresiones Lisp:
Main: La clase Main sirve como punto de entrada del programa. Coordina la interacción con el usuario, lee las expresiones a evaluar y las pasa al Interprete para su procesamiento.
Interprete: La clase Interprete interpreta las expresiones recibidas del usuario, realiza operaciones y gestiona variables y funciones. Utiliza instancias de las clases PrefixCalc, Operator, Tokenizer y otras estructuras de datos como HashMap y ArrayList.
Reader: La clase Reader se encarga de leer y analizar archivos para convertir su contenido en instrucciones comprensibles por el intérprete. Proporciona el método Parse para realizar esta tarea.
Operator: La clase Operator contiene métodos para realizar operaciones específicas, como comparar valores, verificar si un valor es un átomo o una lista en el contexto de Lisp.
PrefixCalc: La clase PrefixCalc proporciona funcionalidad para calcular expresiones en notación prefija, como sumar, restar, multiplicar y dividir. Utiliza una estructura de datos Stack para realizar los cálculos.
Stack: La clase Stack implementa una pila genérica utilizando un ArrayList y proporciona operaciones básicas de pila como push, pop, top, isEmpty y count.
Tokenizer: La clase Tokenizer se encarga de tokenizar comandos de Lisp y determinar su tipo de comando. Convierte comandos de texto en listas de tokens y proporciona métodos para esta tarea.

![image](https://github.com/adirnnn/proy1-Correct-Version/assets/133666261/5ce3b37c-83b6-4a10-8954-4eace0a51b2a)
![image](https://github.com/adirnnn/proy1-Correct-Version/assets/133666261/78812433-2b21-40dc-bf7d-21694a86570d)
![image](https://github.com/adirnnn/proy1-Correct-Version/assets/133666261/490a08f6-5c18-4b56-978a-0c567d91e4a2)
