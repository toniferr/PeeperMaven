# PeeperMaven

## Maven Wrapper
Maven Wrapper es una manera f�cil de garantizar que un usuario de su compilaci�n Maven tenga todo lo necesario para ejecutar su compilaci�n Maven.

�Por qu� podr�a ser esto necesario? Hasta la fecha, Maven ha sido muy estable para los usuarios, est� disponible en la mayor�a de los sistemas o es f�cil de adquirir: pero con muchos de los cambios recientes en Maven, ser� m�s f�cil para los usuarios tener una configuraci�n de compilaci�n totalmente encapsulada proporcionada por el proyecto. Con el Maven Wrapper esto es muy f�cil de hacer y es una gran idea prestada de Gradle.

La forma m�s f�cil de configurar Maven Wrapper para el proyecto es usar el complemento Takari Maven con su objetivo proporcionado. 

```
mvn -N io.takari:maven:wrapper -Dmaven=3.6.0
```

Normalmente, le indica a los usuarios que instalen una versi�n espec�fica de Apache Maven, la coloque en la RUTA y luego ejecute el mvncomando de la siguiente manera:

```
mvn clean install
```
Pero ahora, con una configuraci�n de Maven Wrapper, puede indicar a los usuarios que ejecuten scripts de envoltura:

```
./mvnw clean install
```
o en Windows

```
mvnw.cmd clean install
```

Se ejecutar� una compilaci�n normal de Maven con el �nico cambio importante que si el usuario no tiene la versi�n necesaria de Maven especificada .mvn/wrapper/maven-wrapper.properties, se descargar� primero para el usuario, se instalar� y luego se usar�.

Los usos posteriores de mvn/ mvnw.cmd usan la versi�n espec�fica descargada previamente seg�n sea necesario.

Disponible en [github](https://github.com/takari/maven-wrapper)

## Maven Clean Plugin
Maven Clean Plugin, como su nombre lo indica, intenta limpiar los archivos y directorios generados por Maven durante su construcci�n. Si bien hay complementos que generan archivos adicionales, Clean Plugin supone que estos archivos se generan dentro del directorio target.


### Clean usando la l�nea de comando
Se puede llamar a Clean Plugin para que se ejecute en la l�nea de comandos sin ninguna configuraci�n adicional. Al igual que los otros complementos, para ejecutar Clean, usa:

```
mvn clean:clean
```
donde la primera limpieza se refiere al alias del complemento y la segunda limpieza se refiere al objetivo del complemento.

Sin embargo, Clean Plugin es un complemento especial y est� vinculado a su propia fase de ciclo de vida especial llamada clean. Por lo tanto, por simplicidad, tambi�n se puede ejecutar usando:

```
mvn clean
```

### Ejecutar Clean Plugin autom�ticamente durante una compilaci�n
Si por alguna raz�n, agregar clean a la l�nea de comandos no es una opci�n, Clean Plugin se puede colocar en el pom.xml de un proyecto para que se ejecute cada vez que se construye el proyecto. A continuaci�n se muestra un ejemplo de pom.xml para ejecutar Clean Plugin en la fase de inicializaci�n cada vez que se construye el proyecto:

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
El complemento del compilador se utiliza para compilar las fuentes de su proyecto. Desde 3.0, el compilador predeterminado es javax.tools.JavaCompiler (si est� utilizando java 1.6) y se utiliza para compilar fuentes Java. Si desea forzar el complemento utilizando javac , debe configurar la opci�n del complemento forceJavacCompilerUse .

Tambi�n tenga en cuenta que actualmente la configuraci�n de source predeterminada es 1.6 y la configuraci�n de target predeterminada es 1.6, independientemente del JDK con el que ejecuta Maven.

Se pueden usar otros compiladores que no sean javac y el trabajo ya ha comenzado en AspectJ, .NET y C #.

###Resumen de objetivos
El complemento del compilador tiene dos objetivos. Ambos ya est�n vinculados a sus fases adecuadas dentro del ciclo de vida de Maven y, por lo tanto, se ejecutan autom�ticamente durante sus fases respectivas.

```
compiler:compile
```
Est� vinculado a la fase de compilaci�n y se utiliza para compilar los archivos fuente principales.

```
compiler:testCompile
```
Est� vinculado a la fase de compilaci�n de prueba y se utiliza para compilar los archivos fuente de prueba.


## Maven Resources Plugin
Maven Resources Plugin maneja la copia de los recursos del proyecto en el directorio target. Hay dos tipos diferentes de recursos: recursos principales y recursos de prueba. La diferencia es que los recursos principales son los recursos asociados al c�digo fuente principal, mientras que los recursos de prueba est�n asociados al c�digo fuente de prueba.

Por lo tanto, esto permite la separaci�n de recursos para el c�digo fuente principal y sus pruebas unitarias.

A partir de la versi�n 2.3, este complemento utiliza el componente compartido Maven Filtering para filtrar recursos.

###Resumen de objetivos
Maven Resources Plugin copia los archivos especificados por los elementos de recursos en un directorio de salida. Las tres variaciones a continuaci�n solo difieren en c�mo se especifican o se omiten los elementos de directorio de recursos y de salida. 

El complemento de recursos tiene tres objetivos:

```
resources:resources
```
Copia los recursos para el c�digo fuente principal en el directorio de salida principal.
Este objetivo generalmente se ejecuta autom�ticamente, ya que est� vinculado de forma predeterminada a la fase del ciclo de vida de los recursos del proceso. Siempre usa el elemento project.build.resources para especificar los recursos y, de manera predeterminada, usa project.build.outputDirectory para especificar el destino de la copia.

```
resources:testResources
```
Copia los recursos para el c�digo fuente de prueba en el directorio de salida de prueba.
Este objetivo generalmente se ejecuta autom�ticamente, ya que est� vinculado de forma predeterminada a la fase del ciclo de vida proceso-prueba-recursos. Siempre usa el elemento project.build.testResources para especificar los recursos y, de forma predeterminada, usa project.build.testOutputDirectory para especificar el destino de la copia.

```
resources:copy-resources 
```
Copia recursos en un directorio de salida.
Este objetivo requiere que configure los recursos que se copiar�n y especifique outputDirectory.


## Maven Surefire Plugin
Surefire Plugin se utiliza durante la testfase del ciclo de vida de la compilaci�n para ejecutar las pruebas unitarias de una aplicaci�n. 
Genera informes en dos formatos de archivo diferentes:
- Archivos de texto sin formato ( *.txt)
- -Archivos XML ( *.xml)
Por defecto, estos archivos se generan en ${basedir}/target/surefire-reports/TEST-*.xml.

El esquema para los informes XML de Surefire est� disponible en el Esquema de informes XML de Surefire .

Los informes XML generados por los complementos heredados (versiones hasta 2.22.0) deben ser validados por el esquema de informe XML Legacy Surefire .

Sin embargo, dos versiones de complementos (2.22.1 y 3.0.0-M1) generan informes XML 3.0 y a�n se refieren al esquema heredado. 
Los proyectos que esperan informes XML validados por el esquema XSD, herramientas, por ejemplo, xUnit, no deben usar las versiones 2.22.1 y 3.0.0-M1 del complemento Surefire.

###Resumen de objetivos
El complemento Surefire tiene un solo objetivo:

```
surefire:test
```
Ejecuta las pruebas unitarias de una aplicaci�n.


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
Maven Deploy Plugin tiene dos funciones b�sicas. En la mayor�a de las compilaciones de proyectos, la fase de implementaci�n del ciclo de vida de la compilaci�n se implementa mediante la deploy:deploy mojo. Adem�s, los artefactos que no se crean con Maven se pueden agregar a cualquier repositorio remoto mediante el deploy:deploy-file mojo.

###El deploy:deploy Mojo
En la mayor�a de los casos, este mojo se invoca cuando llama a la fase de deploy del ciclo de vida de compilaci�n predeterminado.

Para habilitar este mojo para que funcione, debe incluir un POM de secci�n <distributionManagement /> v�lido , que como m�nimo proporciona un <repository/> que define la ubicaci�n del repositorio remoto para su artefacto. Para separar los artefactos de la instant�nea de los artefactos de la versi�n, tambi�n puede especificar una ubicaci�n <snapshotRepository /> . Finalmente, para implementar un sitio web del proyecto, tambi�n debe especificar una secci�n <site/> aqu�. Tambi�n es importante tener en cuenta que esta secci�n se puede heredar, lo que le permite especificar la ubicaci�n de implementaci�n una vez para un conjunto de proyectos relacionados.

Si su repositorio est� protegido, es posible que tambi�n desee configurar su archivo settings.xml para definir las entradas correspondientes <server/> que proporcionan informaci�n de autenticaci�n. Las entradas del servidor se corresponden con las diferentes partes de la gesti�n de distribuci�n utilizando sus elementos <id /> . Por ejemplo, su proyecto puede tener una secci�n de gesti�n de distribuci�n similar a la siguiente:

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


En este caso, puede especificar una definici�n de servidor en settings.xml para proporcionar informaci�n de autenticaci�n para ambos repositorios a la vez. La secci�n de su servidor podr�a verse as�:

```
[...]
    <server>
      <id>internal.repo</id>
      <username>maven</username>
      <password>foobar</password>
    </server>
[...]
```

Consulte el art�culo sobre Cifrado de contrase�a para obtener instrucciones sobre c�mo evitar contrase�as de texto sin cifrar en settings.xml .

Una vez que haya configurado la informaci�n de implementaci�n del repositorio, la implementaci�n correcta del artefacto de su proyecto solo requerir� la invocaci�n de la fase de deploy de la compilaci�n:

```
mvn deploy
```

###El deploy:deploy-file Mojo

El despliegue: deploy-file mojo se usa principalmente para desplegar artefactos para los que Maven no construy�. El equipo de desarrollo del proyecto puede proporcionar o no un POM para el artefacto, y en algunos casos es posible que desee implementar el artefacto en un repositorio remoto interno. El archivo de implementaci�n de mojo proporciona una funcionalidad que cubre todos estos casos de uso y ofrece una amplia gama de configuraciones para generar un POM sobre la marcha. Adem�s, puede especificar qu� dise�o usa su repositorio. La declaraci�n de uso completa del archivo de implementaci�n mojo se puede describir como:

```
mvn deploy : deploy - file - Durl = file : // C: \ m2-repo \
                       - DrepositoryId = algunos . carn� de identidad \
                       - Dfile = your - artefacto - 1.0 . tarro
                       [- DpomFile = tu - pom . xml ] \
                       [- DgroupId = org . alguna . grupo ] \
                       [- DartifactId = your - artifact ] \
                       [- Dversion = 1.0 ] \
                       [- Dpackaging = jar ] \
                       [- Dclassifier = prueba ] \
                       [- DgeneratePom = verdadero ] \
                       [- DgeneratePom . description = "Descripci�n de mi proyecto" ] \
                       [- DrepositoryLayout = legacy ]
```

Si la siguiente informaci�n requerida no se especifica de alguna manera, el objetivo fallar�:

el archivo de artefactos para desplegar
El grupo, artefacto, versi�n y empaquetado del archivo a desplegar. Estos pueden tomarse del archivo pomFile especificado y anularse o especificarse utilizando la l�nea de comando. Cuando el pomFile contiene una secci�n principal , el groupId del padre se puede considerar si el groupId no se especifica m�s para el proyecto actual o en la l�nea de comando.
la informaci�n del repositorio: la url para implementar y la asignaci�n de repositoryId a una secci�n del servidor en el archivo settings.xml. Si no especifica un ID de repositorio, Maven intentar� extraer la informaci�n de autenticaci�n utilizando el id 'repositorio remoto' .