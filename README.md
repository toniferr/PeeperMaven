# PeeperMaven


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

