Manual de Usuario - Gestión de Vehículos

Este manual describe cómo utilizar la aplicación de gestión de vehículos. Está destinado a los usuarios finales que interactuarán con el sistema para registrar, buscar y eliminar información de vehículos.

## 1. Introducción

La aplicación de Gestión de Vehículos permite mantener un registro organizado de la información de los vehículos, incluyendo su placa, color, línea, modelo y propietario. La información se almacena de manera eficiente, permitiendo búsquedas rápidas y sencillas.

## 2. Interfaz Principal

Al iniciar la aplicación, se mostrará una ventana con las siguientes secciones:

* *Panel de Controles (Parte Superior):* Contiene campos para ingresar la información de un nuevo vehículo (Placa, Color, Línea, Modelo, Propietario) y botones para realizar las acciones de "Insertar" y "Eliminar por Placa".
* *Tabla de Vehículos (Centro):* Muestra la lista de todos los vehículos registrados en el sistema en formato de tabla con las columnas: Placa, Color, Línea, Modelo y Propietario.
* *Panel de Búsqueda (Parte Inferior):* Contiene un campo de texto para ingresar un criterio de búsqueda y botones para "Buscar" y "Mostrar Todo".

## 3. Funcionalidades Principales

### 3.1. Insertar un Nuevo Vehículo

Para registrar un nuevo vehículo en el sistema, siga estos pasos:

1.  En el *Panel de Controles*, complete los siguientes campos con la información del vehículo:
    * *Placa:* Ingrese la placa del vehículo. Asegúrese de seguir el formato de placa de Guatemala (ejemplo: P123BCD).
    * *Color:* Ingrese el color del vehículo (solo letras).
    * *Línea:* Ingrese la línea o marca del vehículo (ejemplo: Toyota).
    * *Modelo:* Ingrese el año del modelo del vehículo (debe ser un número de 4 dígitos, ejemplo: 2023).
    * *Propietario:* Ingrese el nombre del propietario del vehículo.
2.  Haga clic en el botón *"Insertar"*.
3.  Si la información es válida y la placa no existe, el vehículo se agregará al registro y se mostrará en la *Tabla de Vehículos*. Si hay algún error (placa inválida o ya existente, formato incorrecto), se mostrará un mensaje informativo.

### 3.2. Eliminar un Vehículo por Placa

Para eliminar un vehículo del registro, siga estos pasos:

1.  En el *Panel de Controles, ingrese la placa del vehículo que desea eliminar en el campo *"Placa"**.
2.  Haga clic en el botón *"Eliminar por Placa"*.
3.  Si se encuentra un vehículo con la placa especificada, se mostrará un mensaje de confirmación y el vehículo se eliminará de la *Tabla de Vehículos*. Si no se encuentra ningún vehículo con esa placa, se mostrará un mensaje indicándolo.

### 3.3. Buscar Vehículos

Para buscar vehículos en el registro, puede utilizar un criterio de búsqueda:

1.  En el *Panel de Búsqueda, ingrese el texto que desea buscar en el campo *"Buscar"**. Puede ingresar parte de la placa, el color, la línea, el modelo o el nombre del propietario.
2.  Haga clic en el botón *"Buscar"*.
3.  La *Tabla de Vehículos* se actualizará para mostrar solo los vehículos que coincidan con su criterio de búsqueda.
4.  Para volver a ver todos los vehículos registrados, haga clic en el botón *"Mostrar Todo"*.

## 4. Consideraciones Importantes

* *Formato de Placa:* Al ingresar la placa, asegúrese de seguir el formato estándar de placas de Guatemala. La aplicación validará este formato.
* *Formato de Color:* El campo de color solo debe contener letras.
* *Formato de Modelo:* El campo de modelo debe ser un número de exactamente 4 dígitos.
* *Búsqueda:* La búsqueda es flexible y buscará el texto ingresado en todos los campos de información del vehículo.



## Manual Técnico - Documentación Interna

Este manual describe la estructura interna y el funcionamiento del sistema de gestión de vehículos implementado mediante una matriz ortogonal. Está destinado a desarrolladores y personal técnico encargado del mantenimiento y la expansión del sistema.

### 1. Estructura de la Matriz Ortogonal

La implementación utiliza una estructura de datos de matriz ortogonal para almacenar la información de los vehículos. Esta estructura se basa en nodos interconectados que representan tanto los datos de los vehículos como las cabeceras de filas (modelos) y columnas (propietarios).

#### 1.1. Clase MatrizOrtogonal02

Esta clase contiene la lógica principal para la gestión de la matriz ortogonal.

* *Atributos:*
    * cabeceraFilas: Un nodo especial que sirve como punto de entrada para las cabeceras de las filas (modelos de vehículos). Es un nodo de cabecera (esCabecera = true).
    * cabeceraColumnas: Un nodo especial que sirve como punto de entrada para las cabeceras de las columnas (propietarios de vehículos). Es un nodo de cabecera (esCabecera = false).

* **Constructor MatrizOrtogonal02():**
    * Inicializa cabeceraFilas y cabeceraColumnas como nodos de cabecera con nombres descriptivos.

* *Métodos:*
    * getCabeceraFilas(): Retorna la cabecera de las filas.
    * getCabeceraColumnas(): Retorna la cabecera de las columnas.
    * insertar(String placa, String color, String linea, String modelo, String propietario):
        * Valida el formato de la placa utilizando validarPlacaGuatemala().
        * Verifica si la placa ya existe utilizando existePlaca().
        * Busca o inserta la cabecera de fila correspondiente al modelo utilizando buscarCabeceraFila() e insertarCabeceraFila().
        * Busca o inserta la cabecera de columna correspondiente al propietario utilizando buscarCabeceraColumna() e insertarCabeceraColumna().
        * Crea un nuevo Nodo con la información del vehículo.
        * Inserta el nuevo nodo en la fila (enlazándolo a la derecha del último nodo del modelo) y en la columna (enlazándolo abajo del último nodo del propietario).
        * Retorna true si la inserción fue exitosa, false en caso contrario.
    * buscarCabeceraFila(String modelo): Recorre las cabeceras de fila y retorna el nodo de cabecera correspondiente al modelo, o null si no existe.
    * insertarCabeceraFila(String modelo): Crea un nuevo nodo de cabecera para el modelo y lo inserta al final de la lista de cabeceras de fila.
    * buscarCabeceraColumna(String propietario): Recorre las cabeceras de columna y retorna el nodo de cabecera correspondiente al propietario, o null si no existe.
    * insertarCabeceraColumna(String propietario): Crea un nuevo nodo de cabecera para el propietario y lo inserta al final de la lista de cabeceras de columna.
    * existePlaca(String placa): Recorre toda la matriz buscando un nodo con la placa especificada (ignorando mayúsculas/minúsculas). Retorna true si la encuentra, false en caso contrario.
    * buscar(String criterio): Recorre toda la matriz y retorna una List<Nodo> con todos los nodos cuyo placa, color, linea, modelo o propietario contengan el criterio de búsqueda (ignorando mayúsculas/minúsculas). Utiliza el método auxiliar contieneCriterio().
    * contieneCriterio(Nodo nodo, String criterio): Método auxiliar que verifica si algún atributo del nodo contiene el criterio de búsqueda (ignorando mayúsculas/minúsculas).
    * eliminarPorPlaca(String placa): Recorre la matriz buscando el nodo con la placa especificada (ignorando mayúsculas/minúsculas). Si lo encuentra, lo desvincula de sus nodos vecinos en la fila y la columna, y luego llama a eliminarCabeceraColumnaSiVacia() y eliminarCabeceraFilaSiVacia() para eliminar las cabeceras si ya no tienen nodos asociados. Retorna true si se eliminó el nodo, false en caso contrario.
    * eliminarCabeceraColumnaSiVacia(String propietario): Busca la cabecera de columna del propietario y verifica si no tiene nodos de vehículo asociados (columnaActual.abajo == null). Si está vacía, la desvincula de las otras cabeceras de columna.
    * eliminarCabeceraFilaSiVacia(String modelo): Busca la cabecera de fila del modelo y verifica si no tiene nodos de vehículo asociados (filaActual.derecha == null). Si está vacía, la desvincula de las otras cabeceras de fila.
    * validarPlacaGuatemala(String placa): Valida si la placa cumple con el formato específico de Guatemala (una letra inicial en "ACMOPTU", seguida de tres dígitos y luego tres letras permitidas).

#### 1.2. Clase Nodo

Esta clase representa cada nodo dentro de la matriz ortogonal.

* *Atributos:*
    * placa: Placa del vehículo (String).
    * color: Color del vehículo (String).
    * linea: Línea del vehículo (String).
    * modelo: Modelo del vehículo (String). También se utiliza para las cabeceras de fila.
    * propietario: Propietario del vehículo (String). También se utiliza para las cabeceras de columna.
    * derecha: Referencia al siguiente nodo en la misma fila (modelo).
    * izquierda: Referencia al nodo anterior en la misma fila (modelo).
    * arriba: Referencia al nodo superior en la misma columna (propietario).
    * abajo: Referencia al nodo inferior en la misma columna (propietario).

* *Constructores:*
    * Nodo(String placa, String color, String linea, String modelo, String propietario): Constructor para los nodos que almacenan la información de los vehículos.
    * Nodo(String valor, boolean esModelo): Constructor para los nodos de cabecera. Si esModelo es true, el valor se asigna al atributo modelo; de lo contrario, se asigna a propietario.

* **Método desconectar():**
    * Establece todas las referencias (derecha, izquierda, arriba, abajo) a null para facilitar la recolección de basura.

### 2. Interfaz Gráfica de Usuario (GUI)

La interfaz gráfica se implementa utilizando Swing y consta de las siguientes clases:

#### 2.1. Clase MatrizPanel

Este panel muestra la información de los vehículos en una tabla.

* *Atributos:*
    * matriz: Una instancia de la clase MatrizOrtogonal02 para acceder a los datos.
    * tabla: Un JTable para mostrar los datos en formato tabular.
    * modeloTabla: Un DefaultTableModel que gestiona los datos de la tabla.

* **Constructor MatrizPanel(MatrizOrtogonal02 matriz):**
    * Recibe una instancia de MatrizOrtogonal02.
    * Configura el layout del panel como BorderLayout.
    * Crea un DefaultTableModel y un JTable asociado.
    * Agrega la tabla a un JScrollPane y lo añade al centro del panel.
    * Llama a actualizarTablaCompleta() para mostrar todos los vehículos al inicio.

* *Métodos:*
    * actualizarTablaCompleta(): Limpia la tabla, define las columnas ("Placa", "Color", "Línea", "Modelo", "Propietario") y agrega todas las filas de vehículos obtenidas mediante obtenerTodosLosVehiculos().
    * actualizarTablaBusqueda(List<Nodo> resultados): Limpia la tabla, define las columnas y agrega solo las filas de los nodos proporcionados en la lista resultados.
    * obtenerTodosLosVehiculos(): Recorre la matriz ortogonal y retorna una List<Nodo> con todos los nodos que representan vehículos.

#### 2.2. Clase VentanaPrincipal

Esta clase crea la ventana principal de la aplicación y contiene los controles para interactuar con la matriz.

* *Atributos:*
    * matriz: Una instancia de MatrizOrtogonal02.
    * panelMatriz: Una instancia de MatrizPanel para mostrar la tabla de vehículos.
    * txtPlaca, txtColor, txtLinea, txtModelo, txtPropietario: Campos de texto para ingresar la información de un nuevo vehículo.
    * txtBuscar: Campo de texto para ingresar el criterio de búsqueda.

* **Constructor VentanaPrincipal():**
    * Crea instancias de MatrizOrtogonal02 y MatrizPanel.
    * Configura el título, tamaño y operación de cierre de la ventana.
    * Establece el layout como BorderLayout.
    * Agrega el panelMatriz al centro.
    * Llama a crearPanelControles() y crearPanelBusqueda() para obtener los paneles de control y búsqueda, y los agrega a la parte superior e inferior de la ventana, respectivamente.

* *Métodos:*
    * crearPanelControles(): Crea un JPanel con etiquetas y campos de texto para la placa, color, línea, modelo y propietario, así como botones para "Insertar" y "Eliminar por Placa". Asigna los ActionListener a los botones para llamar a los métodos insertarVehiculo() y eliminarVehiculo().
    * crearPanelBusqueda(): Crea un JPanel con una etiqueta, un campo de texto (txtBuscar) y botones para "Buscar" y "Mostrar Todo". Asigna los ActionListener a los botones para llamar a los métodos buscarVehiculos() y actualizarTablaCompleta() del panelMatriz.
    * insertarVehiculo(): Obtiene los valores de los campos de texto, realiza validaciones básicas (campos no vacíos, formato de color y modelo), y llama al método insertar() de la matriz. Muestra mensajes de éxito o error mediante JOptionPane y actualiza la tabla llamando a actualizarTablaCompleta() del panelMatriz.
    * eliminarVehiculo(): Obtiene la placa del campo de texto, verifica que no esté vacío y llama al método eliminarPorPlaca() de la matriz. Muestra mensajes de éxito o error y actualiza la tabla.
    * buscarVehiculos(): Obtiene el criterio de búsqueda del campo de texto y llama al método buscar() de la matriz. Actualiza la tabla de resultados llamando a actualizarTablaBusqueda() del panelMatriz.
    * limpiarCampos(): Limpia los campos de texto de la información del vehículo.
    * validarColor(String color): Valida si la cadena color contiene solo letras, espacios y caracteres comunes en español.
    * validarModelo(String modelo): Valida si la cadena modelo contiene exactamente 4 dígitos numéricos.
    * main(String[] args): Método principal que crea e inicia la VentanaPrincipal en el hilo de eventos de Swing.

### 3. Consideraciones de Diseño

* *Matriz Ortogonal:* Se eligió una matriz ortogonal para optimizar las búsquedas por modelo y propietario, ya que los nodos están indexados por ambos criterios.
* *Validación de Placas:* Se implementó una validación específica para el formato de placas de Guatemala.
* *Interfaz Swing:* Se utilizó Swing para crear una interfaz gráfica sencilla y funcional para la interacción del usuario.
* *Manejo de Errores:* Se incluyen mensajes de error básicos para informar al usuario sobre problemas durante la inserción o eliminación.

### 4. Posibles Mejoras

* Implementar funcionalidades de edición de vehículos existentes.
* Añadir persistencia de datos (guardar y cargar la matriz desde un archivo).
* Mejorar la interfaz de búsqueda con opciones de filtrado por campos específicos.
* Implementar pruebas unitarias para asegurar la robustez del código.
* Considerar el uso de un patrón de diseño como MVC (Modelo-Vista-Controlador) para una mejor separación de responsabilidades.

---

*Manual de Usuario - Documentación Externa*


# Manual de Usuario - Documentación Externa

Este manual describe cómo utilizar la aplicación de gestión de vehículos. Está destinado a los usuarios finales que interactuarán con el sistema para registrar, buscar y eliminar información de vehículos.

### 1. Introducción

La aplicación de Gestión de Vehículos permite mantener un registro organizado de la información de los vehículos, incluyendo su placa, color, línea, modelo y propietario. La información se almacena de manera eficiente, permitiendo búsquedas rápidas y sencillas.

### 2. Interfaz Principal

Al iniciar la aplicación, se mostrará una ventana con las siguientes secciones:

* *Panel de Controles (Parte Superior):* Contiene campos para ingresar la información de un nuevo vehículo (Placa, Color, Línea, Modelo, Propietario) y botones para realizar las acciones de "Insertar" y "Eliminar por Placa".
* *Tabla de Vehículos (Centro):* Muestra la lista de todos los vehículos registrados en el sistema en formato de tabla con las columnas: Placa, Color, Línea, Modelo y Propietario.
* *Panel de Búsqueda (Parte Inferior):* Contiene un campo de texto para ingresar un criterio de búsqueda y botones para "Buscar" y "Mostrar Todo".

### 3. Funcionalidades Principales

#### 3.1. Insertar un Nuevo Vehículo

Para registrar un nuevo vehículo en el sistema, siga estos pasos:

1.  En el *Panel de Controles*, complete los siguientes campos con la información del vehículo:
    * *Placa:* Ingrese la placa del vehículo. Asegúrese de seguir el formato de placa de Guatemala (ejemplo: P123BCD).
    * *Color:* Ingrese el color del vehículo (solo letras).
    * *Línea:* Ingrese la línea o marca del vehículo (ejemplo: Toyota).
    * *Modelo:* Ingrese el año del modelo del vehículo (debe ser un número de 4 dígitos, ejemplo: 2023).
    * *Propietario:* Ingrese el nombre del propietario del vehículo.
2.  Haga clic en el botón *"Insertar"*.
3.  Si la información es válida y la placa no existe, el vehículo se agregará al registro y se mostrará en la *Tabla de Vehículos*. Si hay algún error (placa inválida o ya existente, formato incorrecto), se mostrará un mensaje informativo.

#### 3.2. Eliminar un Vehículo por Placa

Para eliminar un vehículo del registro, siga estos pasos:

1.  En el *Panel de Controles, ingrese la placa del vehículo que desea eliminar en el campo **"Placa"*.
2.  Haga clic en el botón *"Eliminar por Placa"*.
3.  Si se encuentra un vehículo con la placa especificada, se mostrará un mensaje de confirmación y el vehículo se eliminará de la *Tabla de Vehículos*. Si no se encuentra ningún vehículo con esa placa, se mostrará un mensaje indicándolo.

#### 3.3. Buscar Vehículos

Para buscar vehículos en el registro, puede utilizar un criterio de búsqueda:

1.  En el *Panel de Búsqueda, ingrese el texto que desea buscar en el campo **"Buscar"*. Puede ingresar parte de la placa, el color, la línea, el modelo o el nombre del propietario.
2.  Haga clic en el botón *"Buscar"*.
3.  La *Tabla de Vehículos* se actualizará para mostrar solo los vehículos que coincidan con su criterio de búsqueda.
4.  Para volver a ver todos los vehículos registrados, haga clic en el botón *"Mostrar Todo"*.

### 4. Consideraciones Importantes

* *Formato de Placa:* Al ingresar la placa, asegúrese de seguir el formato estándar de placas de Guatemala. La aplicación validará este formato.
* *Formato de Color:* El campo de color solo debe contener letras.
* *Formato de Modelo:* El campo de modelo debe ser un número de exactamente 4 dígitos.
* *Búsqueda:* La búsqueda es flexible y buscará el texto ingresado en todos los campos de información del vehículo.
