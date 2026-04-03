///////////////////////////////
        JUEGO DE LA VIDA
///////////////////////////////

descargar y descomprimir el archivo


usar su IDE de preferencia para ver dicho archivo


ejecutar 


El juego cuenta con tres formas de iniciarse

৹ aleatoria
    
    construira el tablero de manera aleatoria con un tamaño indicado

৹ ingreso manual
    
    hay 2 opciones
        
        ৹ ingresar siguiendo las instrucciones
        ৹ ingresar teniendo copiado todo y con un control+V
        
    tomara dichos escenarios sin necesidad de agregar los estados

৹ ingresando la ruta de un archivo

    cuando la consola lo requiera insertar uno de los siguientes:
    
        ./ejemplos/cruz.txt
        ./ejemplos/cuadrado.txt
        ./ejemplos/cultivoBacteriano.txt
        
    tomara dichos escenarios sin necesidad de agregar los estados


si quisiera meter un estado nuevo debera llamar a un metodo llamado addEstadoNuevo(estadoNuevo).

     ৹ por ejemplo en el codigo de la interfaz "CLI" se le llama para incluir a dos estados nuevos "EstadoLatente" y "EstadoZombi".



clases principales

CLI:

    pide datos para crear el juego y muestra informacion del mismo

Juego:

    crea un tablero y le da ordenes, gestiona el pasar de generaciones

Tablero:
    
    administra la matriz de celdas y les da ordenes, a su vez le da informacion al juego

Celdas:

    guarda su estado actual y calcula/aplica el proximo cuando se lo ordene tablero

Estado:

    define la estructura de cada estado posible de una celda, sus clases hijas seran las que definan su comportamientos
