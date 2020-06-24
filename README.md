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

