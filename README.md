# Perceptron paralelo usando la regla P-delta.

Este modelo fue desarrollado personalmente como proyecto de la materia de Inteligencia
Artifical dentro de la Licenciatura de Sistemas de Informacion Administrativo
en la Universidad de Guanajuato.

Su fin es solo academico, ya que solo logra clasificacion binaria. Para el
desarrollo habiamos utilizado dos datasets basados en la deteccion de correos
de spam, estas hojas de datos vienen anexados al repositorio.

Explicacion de los datos:

Datos:

A partir de un conjunto de correos electrónicos (web), previamente etiquetados
como easy ham, hard ham y spam, se eligieron 50 y 10 correos para entrenamiento
y prueba, respectivamente, de cada tipo.

Los correos electrónicos fueron separados en dos problemas biclase: 1) easy ham
y spam y 2) hard hamd y spam. Por lo tanto, ambos problemas contienen 100
correos electrónicos para entrenar y 20 para probar.

Con base al conjunto de entrenamiento en cada problema, se usaron
independientemente dos enfoques de Bag of Words -BoW- (Count Occurrence
y TF-IDF) para crear los patrones (vectores de
características).

Con los dos problemas y los dos enfoques de BoW, se crearon 4 conjuntos
problema (archivo adjunto):

- CountVectorizer-EasyMailSpam - 2219 características
- CountVectorizer-HardMailSpam - 5035 características
- Tfidf-EasyMailSpam - 2219 características
- Tfidf-HardMailSpam - 5035 características

La diferencia en cantidad de características se debe a las diferentes palabras
que se encuentran en los conjuntos de entrenamiento. Cada conjunto tiene dos
archivos, el que empieza con tr contiene los patrones de entrenamiento y el que
empieza con te contiene los patrones de prueba. Todos los archivos listan
inicialmente todas las mediciones de las características y al último la
etiqueta de clase (1-Easy/Hard Mail, 2-Spam).

![CredencialUG](/perfilUG.png)
