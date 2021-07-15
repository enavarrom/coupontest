# Coupon Test

_Ejemplo de cálculo de productos a comprar a partir de un monto determinado._


## Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_

```
1. Java 11 o superior.
2. IDE de tu preferencia (Intellij Recomendado).
3. Habilitar el procesamiento de Anotaciones de tu IDE.

NOTA: Este proyecto tiene una integración con una API para consultar los precios de los items. 
Se debe configurar una variable de entorno con la URL de esa api.

```

## Configuración, Instalación y ejecución 🔧

_El siguiente paso a paso te ayudará a configurar tu ambiente de trabajo y ejecutar el proyecto localmente_


```
1. Clonar el proyecto del repositorio github.
2. Importar el proyecto en tu IDE de desarrollo como proyecto Gradle.
3. En la ruta del proyecto compilar el proyecto ejecutando el comando: gradle clean build (En linux: ./gradlew clean build)
4. Ejecutar el proyecto con el comando ./gradlew bootRun --args='--CO_COM_MELI_COUPON_ITEM_CLIENT_URL=http://localhost:8082'

NOTA: La variable CO_COM_MELI_COUPON_ITEM_CLIENT_URL indica la url donde se consultará la información de los items (precio, title, etc.).

```

## Coverage ⚙️

_Puede ver el coverage del proyecto en la ruta build/jacocoHtml/index.html_



## Despliegue 📦

_Agrega notas adicionales sobre como hacer deploy_

## Construido con 🛠️

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Spring](https://spring.io/projects/) - Spring Framework
* [Gradle](https://docs.gradle.org/) - Manejador de dependencias


## Autor ✒️


* **Eder Navarro** - *Trabajo Inicial* - [enavarrom](https://github.com/enavarrom)

