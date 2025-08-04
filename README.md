# Proyecto de Automatización con Selenium, TestNG y Log4j2

Este proyecto utiliza Selenium WebDriver junto con TestNG para realizar pruebas automatizadas en distintos navegadores. Está configurado para ejecutarse en Chrome y Firefox.

## Estructura del Proyecto

- `src/main/java`: Código principal de la automatización.
- `src/test/java`: Clases de prueba con TestNG.
- `resources/log4j2.xml`: Configuración de logging con Log4j2.
- `testng.xml`: Archivo de configuración de TestNG para ejecución de pruebas.
- `logs/`: Carpeta donde se guardan los logs de ejecución.
- `tmp/`: Carpeta autogenerada para guardar reportes PDFs.

## Ejecución de Pruebas

### Desde línea de comandos

```bash
mvn clean test
```

### Desde IntelliJ usando `testng.xml`

1. Abre el archivo `testng.xml`.
2. Haz clic derecho y selecciona `Run 'testng.xml'`.

### Parámetros de Navegador

El archivo `testng.xml` está configurado para correr pruebas en Chrome y Firefox.

## Logging

Los logs se generan en la carpeta `logs/automation.log`. La configuración se encuentra en `resources/log4j2.xml`.

## Exclusión de Archivos Sensibles

Se excluyen del repositorio archivos `.xlsx` y `.pdf` para evitar exponer datos sensibles.

### `.gitignore`

```gitignore
*.xlsx
*.pdf
/logs/
/.idea/
/target/
```

## Requisitos Previos

- Java 11+
- Maven 3.6+
- Navegadores Chrome y Firefox instalados
- **Los archivos `.xlsx` que se utilizan como fuente para los `DataProvider` deben estar en la carpeta `src/test/resources`**
- Archivo `log4j2.xml` ubicado también en `src/test/resources`

## Dependencias Destacadas

- Selenium WebDriver
- TestNG
- Log4j2
- iTextPDF

## Autor

- P Bordones
