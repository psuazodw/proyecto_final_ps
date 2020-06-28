# Pablo Suazo
## Introduccion a Programacion Web 2020

### Proyecto Supermercado:

1. Requerimientos
	> La herramienta **Supermercado** consiste en un sistema para ser manejado por un **Cajero** de un supermercado el cual controla el inventario de los productos. 
	> Los productos estan asociados a una categoría (Lácteos, Panadería, Frutas, Verduras, etc).
	> La herramienta permite la creación de nuevos productos, cajeros y categorias mediante un formulario y su visualizacion. 
	> Dicha informacion es almacenada en la base de datos db_market para su mejor administracion.

2. Procedimientos
	>Se inicia con un menu con las siguentes opciones:
		* Crear Categoria: Permite la creacion de categorias solicitando al cajero el nombre de dicha categoria.
		* Ingresar Productos: Permite la creaciond de productos solicitando al cajero seleccionar la categoria,  
							  nombre del producto, el precio y la existencia.
		* Visualizar Productos: Muestra al cajero todos los productos creados y permite la **busqueda de producto por nombre.**
		* Nuevo cajero: Permite la creacion de un nuevo cliente solicitando al cajero el nombre y el apellido del cliente.
		* Pedido: Modulo donde el cajero crea el pedido del cliente solicitando el codigo del ciente, el producto deseado y la cantidad. Una vez este guardado el pedido, hacer click en **Factura** para visualizar el pedido, se solicitara el codigo de cliente para mostrar los datos.

3. Arquitectura
	> La herramienta consta de un indice con links que redirigen a distintos JSPs:
		* *crearCategoria.jsp* presetna el formulario de creacion de categorias y envia los datos recibidos al servlet *guardarCategoria.java*
		* *ingresarProductos.jsp* presenta el formulario para que el cajero cree los productos disponibles y envia los datos al servlet *guardarProducto.java*
		* *visualizarProductos.jsp* es un JSTL que presenta el listado de productos creados
		* *usuarioNuevo.jsp* permite la creacion de un cliente nuevo y guarda la informacion mediante el servlet *guardarUsuario.java*
		* *pedido.jsp* permite al cajero la creacion del pedido del cliente usando el servlet *guardarPedido.java* para el almacenamiento de datos. Este modulo permite redirigirse al modulo *factura.jsp* para la visualizacion del pedido.
		* *dbConnection.java* es el modulo encargado de la conexion, insercion y consulta de datos a la base de datos. 
		* *dbMarket_ER.drawio* para el diagrama Entidad-Relacion.