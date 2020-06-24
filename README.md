# PeeperMaven

## Maven Wrapper
Maven Wrapper es una manera fácil de garantizar que un usuario de su compilación Maven tenga todo lo necesario para ejecutar su compilación Maven.

¿Por qué podría ser esto necesario? Hasta la fecha, Maven ha sido muy estable para los usuarios, está disponible en la mayoría de los sistemas o es fácil de adquirir: pero con muchos de los cambios recientes en Maven, será más fácil para los usuarios tener una configuración de compilación totalmente encapsulada proporcionada por el proyecto. Con el Maven Wrapper esto es muy fácil de hacer y es una gran idea prestada de Gradle.

La forma más fácil de configurar Maven Wrapper para el proyecto es usar el complemento Takari Maven con su objetivo proporcionado. 

```
mvn -N io.takari:maven:wrapper -Dmaven=3.6.0
```

Normalmente, le indica a los usuarios que instalen una versión específica de Apache Maven, la coloque en la RUTA y luego ejecute el mvncomando de la siguiente manera:

```
mvn clean install
```
Pero ahora, con una configuración de Maven Wrapper, puede indicar a los usuarios que ejecuten scripts de envoltura:

```
./mvnw clean install
```
o en Windows

```
mvnw.cmd clean install
```

Se ejecutará una compilación normal de Maven con el único cambio importante que si el usuario no tiene la versión necesaria de Maven especificada .mvn/wrapper/maven-wrapper.properties, se descargará primero para el usuario, se instalará y luego se usará.

Los usos posteriores de mvn/ mvnw.cmd usan la versión específica descargada previamente según sea necesario.

Disponible en [github](https://github.com/takari/maven-wrapper)

## Maven Clean Plugin
Maven Clean Plugin, como su nombre lo indica, intenta limpiar los archivos y directorios generados por Maven durante su construcción. Si bien hay complementos que generan archivos adicionales, Clean Plugin supone que estos archivos se generan dentro del directorio target.


### Clean usando la línea de comando
Se puede llamar a Clean Plugin para que se ejecute en la línea de comandos sin ninguna configuración adicional. Al igual que los otros complementos, para ejecutar Clean, usa:

```
mvn clean:clean
```
donde la primera limpieza se refiere al alias del complemento y la segunda limpieza se refiere al objetivo del complemento.

Sin embargo, Clean Plugin es un complemento especial y está vinculado a su propia fase de ciclo de vida especial llamada clean. Por lo tanto, por simplicidad, también se puede ejecutar usando:

```
mvn clean
```

### Ejecutar Clean Plugin automáticamente durante una compilación
Si por alguna razón, agregar clean a la línea de comandos no es una opción, Clean Plugin se puede colocar en el pom.xml de un proyecto para que se ejecute cada vez que se construye el proyecto. A continuación se muestra un ejemplo de pom.xml para ejecutar Clean Plugin en la fase de inicialización cada vez que se construye el proyecto:

```
  <build>
    <plugins>
      <plugin>
        <artifactId>maven-clean-plugin</artifactId>
        <version>3.1.0</version>
        <executions>
          <execution>
            <id>auto-clean</id>
            <phase>initialize</phase>
            <goals>
              <goal>clean</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```

## Maven Compiler Plugin
El complemento del compilador se utiliza para compilar las fuentes de su proyecto. Desde 3.0, el compilador predeterminado es javax.tools.JavaCompiler (si está utilizando java 1.6) y se utiliza para compilar fuentes Java. Si desea forzar el complemento utilizando javac , debe configurar la opción del complemento forceJavacCompilerUse .

También tenga en cuenta que actualmente la configuración de source predeterminada es 1.6 y la configuración de target predeterminada es 1.6, independientemente del JDK con el que ejecuta Maven.

Se pueden usar otros compiladores que no sean javac y el trabajo ya ha comenzado en AspectJ, .NET y C #.

###Resumen de objetivos
El complemento del compilador tiene dos objetivos. Ambos ya están vinculados a sus fases adecuadas dentro del ciclo de vida de Maven y, por lo tanto, se ejecutan automáticamente durante sus fases respectivas.

```
compiler:compile
```
Está vinculado a la fase de compilación y se utiliza para compilar los archivos fuente principales.

```
compiler:testCompile
```
Está vinculado a la fase de compilación de prueba y se utiliza para compilar los archivos fuente de prueba.


## Maven Resources Plugin
Maven Resources Plugin maneja la copia de los recursos del proyecto en el directorio target. Hay dos tipos diferentes de recursos: recursos principales y recursos de prueba. La diferencia es que los recursos principales son los recursos asociados al código fuente principal, mientras que los recursos de prueba están asociados al código fuente de prueba.

Por lo tanto, esto permite la separación de recursos para el código fuente principal y sus pruebas unitarias.

A partir de la versión 2.3, este complemento utiliza el componente compartido Maven Filtering para filtrar recursos.

###Resumen de objetivos
Maven Resources Plugin copia los archivos especificados por los elementos de recursos en un directorio de salida. Las tres variaciones a continuación solo difieren en cómo se especifican o se omiten los elementos de directorio de recursos y de salida. 

El complemento de recursos tiene tres objetivos:

```
resources:resources
```
Copia los recursos para el código fuente principal en el directorio de salida principal.
Este objetivo generalmente se ejecuta automáticamente, ya que está vinculado de forma predeterminada a la fase del ciclo de vida de los recursos del proceso. Siempre usa el elemento project.build.resources para especificar los recursos y, de manera predeterminada, usa project.build.outputDirectory para especificar el destino de la copia.

```
resources:testResources
```
Copia los recursos para el código fuente de prueba en el directorio de salida de prueba.
Este objetivo generalmente se ejecuta automáticamente, ya que está vinculado de forma predeterminada a la fase del ciclo de vida proceso-prueba-recursos. Siempre usa el elemento project.build.testResources para especificar los recursos y, de forma predeterminada, usa project.build.testOutputDirectory para especificar el destino de la copia.

```
resources:copy-resources 
```
Copia recursos en un directorio de salida.
Este objetivo requiere que configure los recursos que se copiarán y especifique outputDirectory.


## Maven Surefire Plugin
Surefire Plugin se utiliza durante la testfase del ciclo de vida de la compilación para ejecutar las pruebas unitarias de una aplicación. 
Genera informes en dos formatos de archivo diferentes:
- Archivos de texto sin formato ( *.txt)
- -Archivos XML ( *.xml)
Por defecto, estos archivos se generan en ${basedir}/target/surefire-reports/TEST-*.xml.

El esquema para los informes XML de Surefire está disponible en el Esquema de informes XML de Surefire .

Los informes XML generados por los complementos heredados (versiones hasta 2.22.0) deben ser validados por el esquema de informe XML Legacy Surefire .

Sin embargo, dos versiones de complementos (2.22.1 y 3.0.0-M1) generan informes XML 3.0 y aún se refieren al esquema heredado. 
Los proyectos que esperan informes XML validados por el esquema XSD, herramientas, por ejemplo, xUnit, no deben usar las versiones 2.22.1 y 3.0.0-M1 del complemento Surefire.

###Resumen de objetivos
El complemento Surefire tiene un solo objetivo:

```
surefire:test
```
Ejecuta las pruebas unitarias de una aplicación.


## Maven JAR Plugin
Este complemento proporciona la capacidad de construir jars. Si desea firmar frascos, utilice el complemento Maven Jarsigner.

```
jar:jar
```
Crea un archivo jar para los recursos inclusivos de tus clases de proyecto.

```
jar:test-jar
```
Crea un archivo jar para las clases de prueba de tu proyecto.


## Maven Deploy Plugin
Maven Deploy Plugin tiene dos funciones básicas. En la mayoría de las compilaciones de proyectos, la fase de implementación del ciclo de vida de la compilación se implementa mediante la deploy:deploy mojo. Además, los artefactos que no se crean con Maven se pueden agregar a cualquier repositorio remoto mediante el deploy:deploy-file mojo.

###El deploy:deploy Mojo
En la mayoría de los casos, este mojo se invoca cuando llama a la fase de deploy del ciclo de vida de compilación predeterminado.

Para habilitar este mojo para que funcione, debe incluir un POM de sección <distributionManagement /> válido , que como mínimo proporciona un <repository/> que define la ubicación del repositorio remoto para su artefacto. Para separar los artefactos de la instantánea de los artefactos de la versión, también puede especificar una ubicación <snapshotRepository /> . Finalmente, para implementar un sitio web del proyecto, también debe especificar una sección <site/> aquí. También es importante tener en cuenta que esta sección se puede heredar, lo que le permite especificar la ubicación de implementación una vez para un conjunto de proyectos relacionados.

Si su repositorio está protegido, es posible que también desee configurar su archivo settings.xml para definir las entradas correspondientes <server/> que proporcionan información de autenticación. Las entradas del servidor se corresponden con las diferentes partes de la gestión de distribución utilizando sus elementos <id /> . Por ejemplo, su proyecto puede tener una sección de gestión de distribución similar a la siguiente:

```
[...]
  <distributionManagement>
    <repository>
      <id>internal.repo</id>
      <name>MyCo Internal Repository</name>
      <url>Host to Company Repository</url>
    </repository>
  </distributionManagement>
[...]
```


En este caso, puede especificar una definición de servidor en settings.xml para proporcionar información de autenticación para ambos repositorios a la vez. La sección de su servidor podría verse así:

```
[...]
    <server>
      <id>internal.repo</id>
      <username>maven</username>
      <password>foobar</password>
    </server>
[...]
```

Consulte el artículo sobre Cifrado de contraseña para obtener instrucciones sobre cómo evitar contraseñas de texto sin cifrar en settings.xml .

Una vez que haya configurado la información de implementación del repositorio, la implementación correcta del artefacto de su proyecto solo requerirá la invocación de la fase de deploy de la compilación:

```
mvn deploy
```

###El deploy:deploy-file Mojo

El despliegue: deploy-file mojo se usa principalmente para desplegar artefactos para los que Maven no construyó. El equipo de desarrollo del proyecto puede proporcionar o no un POM para el artefacto, y en algunos casos es posible que desee implementar el artefacto en un repositorio remoto interno. El archivo de implementación de mojo proporciona una funcionalidad que cubre todos estos casos de uso y ofrece una amplia gama de configuraciones para generar un POM sobre la marcha. Además, puede especificar qué diseño usa su repositorio. La declaración de uso completa del archivo de implementación mojo se puede describir como:

```
mvn deploy : deploy - file - Durl = file : // C: \ m2-repo \
                       - DrepositoryId = algunos . carné de identidad \
                       - Dfile = your - artefacto - 1.0 . tarro
                       [- DpomFile = tu - pom . xml ] \
                       [- DgroupId = org . alguna . grupo ] \
                       [- DartifactId = your - artifact ] \
                       [- Dversion = 1.0 ] \
                       [- Dpackaging = jar ] \
                       [- Dclassifier = prueba ] \
                       [- DgeneratePom = verdadero ] \
                       [- DgeneratePom . description = "Descripción de mi proyecto" ] \
                       [- DrepositoryLayout = legacy ]
```

Si la siguiente información requerida no se especifica de alguna manera, el objetivo fallará:

el archivo de artefactos para desplegar
El grupo, artefacto, versión y empaquetado del archivo a desplegar. Estos pueden tomarse del archivo pomFile especificado y anularse o especificarse utilizando la línea de comando. Cuando el pomFile contiene una sección principal , el groupId del padre se puede considerar si el groupId no se especifica más para el proyecto actual o en la línea de comando.
la información del repositorio: la url para implementar y la asignación de repositoryId a una sección del servidor en el archivo settings.xml. Si no especifica un ID de repositorio, Maven intentará extraer la información de autenticación utilizando el id 'repositorio remoto' .