# PeeperMaven


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

