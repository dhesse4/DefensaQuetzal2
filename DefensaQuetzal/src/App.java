import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        Scanner scan = new Scanner(System.in);

        //La mision general, se inicia con todo en 0.
        Mision mision = new Mision("M001", "Exploracion", 0.0, 0.0, 0.0);

        List<Modulos> modulos = new ArrayList<>();

        //Los modulos de la mision, cada uno con datos diferentes.
        modulos.add(new ModulosEnegia("ME001", "ModuloSolar1", "Bueno", true, 1000.0, 50.0, 0.0, 0));
        modulos.add(new ModulosVuelo("MV001", "ModuloVuelo1", "Bueno", true, 2000.0, "Camara", 30.0, 20.0, 0, 0.0));
        modulos.add(new ModuloTierra("MT001", "ModuloTierra01", "Bueno", false, 1500.0, 50.0, 10.0, "Estacion1", 0.0));
        modulos.add(new ModulosVuelo("MV002", "ModuloVuelo2", "Bueno", true, 1300.0, "Sensor", 30.0, 20.0, 0, 0.0));
        modulos.add(new ModuloTierra("MT002", "ModuloTierra02", "Regular", true, 1750.0, 100.0, 50.0, "Estacion2", 0.0));
        modulos.add(new ModulosEnegia("ME002", "ModuloSolar2", "Bueno", true, 900.0, 20.0, 0.0, 0));
        modulos.add(new ModuloTierra("MT003", "ModuloTierra03", "Bueno", true, 1700.0, 70.0, 15.0, "Estacion3", 0.0));
        modulos.add(new ModulosEnegia("ME003", "ModuloSolar3", "Bueno", true, 1100.0, 60.0, 0.0, 0));
        modulos.add(new ModuloTierra("MT004", "ModuloTierra04", "Bueno", false, 1900.0, 100.0, 20.0, "Estacion4", 0.0));
        int eleccion = 0;

        //El while pal menu
        while(eleccion != 9)
        {
            System.out.println("\nSeleccione una opcion:");
            System.out.println("1. Listar todos los modulos.");
            System.out.println("2. Buscar modulo por id.");
            System.out.println("3. Catalogo ordenado por costo.");
            System.out.println("4. Procesar un ciclo.");
            System.out.println("5. Modificar modulos por id.");
            System.out.println("6. Agregar un nuevo modulo.");
            System.out.println("7. Resumen de comunicaciones.");
            System.out.println("8. Iniciar nueva mision");
            System.out.println("9. Salir del programa.");
            System.out.print("Ingresa tu eleccion: ");
            eleccion = Integer.parseInt(scan.nextLine());

            switch(eleccion)
            {
                case 1: //Solo se recorre la lista y se llama al metodo mostrar info de cada uno

                    for(int i = 0; i < modulos.size(); i++)
                    {
                        System.out.println(modulos.get(i).mostrarInformacion());
                    }
                    break;
                
                case 2: // Se usa el id para buscar el modulo por metodo y luego se muestra su info. 
                    
                    System.out.print("Ingrese el ID del modulo a buscar: ");
                    String idBusqueda = scan.nextLine();

                    Modulos moduloEncontrado = BuscarporId(modulos, idBusqueda);
                    if(moduloEncontrado != null)
                    {
                        System.out.println(moduloEncontrado.mostrarInformacion());
                    }
                    else
                    {
                        System.out.println("Modulo no encontrado.");
                    }
                     
                    break;
                
                case 3: // Se lleva a un metodo para ordenar, se devuelve una copia de la lista ya ordenada y se imprime de menor a mayor coste
                    
                    List<Modulos> modulosOrdenados = ordenarModulosPorCosto(modulos);
                    for(int i = 0; i < modulosOrdenados.size(); i++)
                    {
                        System.out.println(modulosOrdenados.get(i).mostrarInformacion());
                    }
                    break;

                case 4: //Se recorre toda la lista, por cada modulo se llama al procesar ciclo, como esta override, cada uno actua diferente

                    for(int i = 0; i < modulos.size(); i++)
                    {
                        modulos.get(i).procesarCiclo(mision);
                    }

                    mision.datosSiguienteCiclo(mision.getEnergiaDisponible(), mision.getDatosOrbita(), mision.getDatosDescargados());

                    System.out.println("Estado de la misión al terminar el ciclo:");
                    System.out.println(mision);
                    break;
                
                case 5: // se modifica toda la info de un modulo, se busca igual como en el case 2, y se inicia en 0 ya que simula un reinicio del modulo. (no estaba en el enunciado pero le da más vida al programa imo)

                    System.out.print("Ingrese el ID del modulo a buscar: ");
                    String idBuscar = scan.nextLine();

                    Modulos moduloEncontrado2 = BuscarporId(modulos, idBuscar);

                    if(moduloEncontrado2 != null)
                    {

                        System.out.println("Ingrese el nuevo nombre del modulo: ");
                        String nuevoNombre = scan.nextLine();
                        moduloEncontrado2.setNombre(nuevoNombre);

                        System.out.println("Ingrese el nuevo estado de salud del modulo: ");
                        String nuevoEstadoSalud = scan.nextLine();
                        moduloEncontrado2.setEstadoSalud(nuevoEstadoSalud);

                        System.out.println("Ingrese el nuevo estado de activo del modulo (true/false): ");
                        Boolean nuevoActivo = Boolean.parseBoolean(scan.nextLine());
                        moduloEncontrado2.setActivo(nuevoActivo);

                        System.out.println("Ingrese el nuevo costo de construccion del modulo: ");
                        Double nuevoCostoConstruccion = Double.parseDouble(scan.nextLine());
                        moduloEncontrado2.setCostoConstruccion(nuevoCostoConstruccion);

                        if(moduloEncontrado2 instanceof ModulosEnegia)
                        {
                            ModulosEnegia moduloEnergia = (ModulosEnegia) moduloEncontrado2;

                            System.out.println("Ingrese la nueva cantidad de energia que genera el modulo: ");
                            Double nuevaCantidadEnergia = Double.parseDouble(scan.nextLine());
                            moduloEnergia.setCantidadEnergia(nuevaCantidadEnergia);

                            moduloEnergia.setEnergiaGenerada(0.0);
                            moduloEnergia.setCantidadCiclos(0);
                        }

                        else if(moduloEncontrado2 instanceof ModulosVuelo)
                        {
                            ModulosVuelo moduloVuelo = (ModulosVuelo) moduloEncontrado2;

                            System.out.println("Ingrese el nuevo tipo de instrumento del modulo: ");
                            String nuevoTipoInstrumento = scan.nextLine();
                            moduloVuelo.setTipoInstrumento(nuevoTipoInstrumento);

                            System.out.println("Ingrese la nueva cantidad de datos del modulo: ");
                            Double nuevaCantidadDatos = Double.parseDouble(scan.nextLine());
                            moduloVuelo.setCantidadDatos(nuevaCantidadDatos);

                            System.out.println("Ingrese el nuevo consumo de energia del modulo: ");
                            Double nuevoConsumoEnergia = Double.parseDouble(scan.nextLine());
                            moduloVuelo.setConsumoEnergia(nuevoConsumoEnergia);

                            moduloVuelo.setCantidadCiclos(0);
                            moduloVuelo.setTotalHistorico(0.0);
                        }

                        else if(moduloEncontrado2 instanceof ModuloTierra)
                        {
                            ModuloTierra moduloTierra = (ModuloTierra) moduloEncontrado2;

                            System.out.print("Ingrese la nueva estación: ");
                            moduloTierra.setEstacion(scan.nextLine());

                            System.out.print("Ingrese la nueva capacidad de descarga: ");
                            moduloTierra.setCapacidadDescarga(Double.parseDouble(scan.nextLine()));

                            System.out.print("Ingrese el nuevo consumo de energía: ");
                            moduloTierra.setConsumoEnergia(Double.parseDouble(scan.nextLine()));

                            moduloTierra.setTotalDescargado(0.0);
                        }

                        System.out.println("Modulo actualizado exitosamente.");
                    }
                    else
                    {
                        System.out.println("Modulo no encontrado.");
                    }
                    break;

                case 6: // Se agregan modulos a la lista dependiendo del tipo que quieran, se llevan al metodo donde ya se dan las especificaciones.

                    System.out.print("Ingrese el tipo de modulo a agregar (energia/vuelo/tierra): ");
                    String tipoModulo = scan.nextLine();
                    agregarModulo(modulos, tipoModulo, scan);

                    break;
                case 7: // el resumen que se se hace por medio de un metodo. 
                    resumenComunicaciones(modulos, mision);
                    break;
                case 8: // Se reinicia la mision, se le piden los datos y se crea una nueva mision con esos datos.
                
                    System.out.print("Ingrese el ID de la nueva misión: ");
                    String nuevoId = scan.nextLine();
                    
                    if(nuevoId.equals(mision.getId()))
                    {
                        System.out.println("El ID de la nueva misión no puede ser igual al de la misión actual. Por favor, ingresa un ID diferente.");
                        break;
                    }

                    System.out.print("Ingrese el nombre de la nueva misión: ");
                    String nuevoNombreMision = scan.nextLine();
                    System.out.print("Ingrese la energía disponible inicial: ");
                    double nuevaEnergiaDisponible = Double.parseDouble(scan.nextLine());
                    System.out.print("Ingrese los datos en órbita iniciales: ");
                    double nuevosDatosOrbita = Double.parseDouble(scan.nextLine());
                    System.out.print("Ingrese los datos descargados iniciales: ");
                    double nuevosDatosDescargados = Double.parseDouble(scan.nextLine());
                    
                    modulos.clear(); // Limpiar la lista de módulos para la nueva misión
                    System.out.println("Todos los módulos han sido eliminados para la nueva misión.");
                    mision = new Mision(nuevoId, nuevoNombreMision, nuevaEnergiaDisponible, nuevosDatosOrbita, nuevosDatosDescargados);
                    System.out.println("Nueva misión iniciada exitosamente.");

                    break;

                case 9:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente.");
            }
        }
        scan.close();
    }

    public static List<Modulos> ordenarModulosPorCosto(List<Modulos> modulos) 
    {   
        List<Modulos> modulosOrdenados = new ArrayList<>(modulos);

        for (int i = 0; i < modulosOrdenados.size() - 1; i++) 
        {
            for (int j = 0; j < modulosOrdenados.size() - i - 1; j++) 
            {
                if (modulosOrdenados.get(j).getCostoConstruccion() > modulosOrdenados.get(j + 1).getCostoConstruccion()) 
                {
                    Modulos temp = modulosOrdenados.get(j);
                    modulosOrdenados.set(j, modulosOrdenados.get(j + 1));
                    modulosOrdenados.set(j + 1, temp);
                }
            }
        }
        return modulosOrdenados;
    }

    public static Modulos BuscarporId(List<Modulos> modulos, String id) 
    {
        boolean encontrado = false;
        for(int i = 0; i < modulos.size(); i++)
        {
            if(modulos.get(i).getId().equals(id))
            {
                encontrado = true;
                return modulos.get(i);
            }
        }
        if(!encontrado)
        {
            System.out.println("Modulo no encontrado.");
            return null;
        }

        return null;
    }

    public static void agregarModulo(List<Modulos> modulos, String tipoModulo, Scanner scan) 
    {
        System.out.println("Ingrese el ID del modulo: ");
        String id = scan.nextLine();  
        
        for(Modulos modulo : modulos)
        {
            if(modulo.getId().equals(id))
            {
                System.out.println("El ID ya existe. No se puede agregar el modulo.");
                return;
            }
        }

        System.out.println("Ingrese el nombre del modulo: ");
        String nombre = scan.nextLine();
        System.out.println("Ingrese el estado de salud del modulo: ");
        String estadoSalud = scan.nextLine();
        System.out.println("Ingrese si el modulo esta activo (true/false): ");
        Boolean activo = Boolean.parseBoolean(scan.nextLine());
        System.out.println("Ingrese el costo de construccion del modulo: ");
        Double costoConstruccion = Double.parseDouble(scan.nextLine());

        if(tipoModulo.equalsIgnoreCase("energia"))
        {
            System.out.println("Ingrese la cantidad de energia del modulo de energia: ");
            Double cantidadEnergia = Double.parseDouble(scan.nextLine());
            ModulosEnegia nuevoModuloEnergia = new ModulosEnegia(id, nombre, estadoSalud, activo, costoConstruccion, cantidadEnergia, 0.0, 0);
            modulos.add(nuevoModuloEnergia);
        }
        else if(tipoModulo.equalsIgnoreCase("vuelo"))
        {
            System.out.println("Ingrese el tipo de instrumento del modulo de vuelo: ");
            String tipoInstrumento = scan.nextLine();
            System.out.println("Ingrese la cantidad de datos del modulo de vuelo: ");
            Double cantidadDatos = Double.parseDouble(scan.nextLine());
            System.out.println("Ingrese el consumo de energia del modulo de vuelo: ");
            Double consumoEnergia = Double.parseDouble(scan.nextLine());
            ModulosVuelo nuevoModuloVuelo = new ModulosVuelo(id, nombre, estadoSalud, activo, costoConstruccion, tipoInstrumento, cantidadDatos, consumoEnergia, 0, 0.0);
            modulos.add(nuevoModuloVuelo);
        }
        else if(tipoModulo.equalsIgnoreCase("tierra"))
        {
            System.out.print("Ingrese la estación del módulo de tierra: ");
            String estacion = scan.nextLine();

            System.out.print("Ingrese la capacidad de descarga: ");
            Double capacidadDescarga = Double.parseDouble(scan.nextLine());

            System.out.print("Ingrese el consumo de energía: ");
            Double consumoEnergia = Double.parseDouble(scan.nextLine());

            ModuloTierra nuevoModuloTierra = new ModuloTierra(id, nombre, estadoSalud, activo, costoConstruccion, capacidadDescarga, consumoEnergia, estacion, 0.0);
            modulos.add(nuevoModuloTierra);
        }
    }

    public static void resumenComunicaciones(List<Modulos> modulos, Mision mision)
    {
        int cantidadTierra = 0;
        int cantidadActivos = 0;
        double capacidadActiva = 0.0;
        double totalHistorico = 0.0;
        double maximo = -1.0;
        List<ModuloTierra> mayores = new ArrayList<>();

        for (Modulos modulo : modulos)
        {
            if (modulo instanceof ModuloTierra)
            {
                ModuloTierra tierra = (ModuloTierra) modulo;
                cantidadTierra++;
                totalHistorico += tierra.getTotalDescargado();

                if (tierra.getActivo())
                {
                    cantidadActivos++;
                    capacidadActiva += tierra.getCapacidadDescarga();
                }

                if (tierra.getTotalDescargado() > maximo)
                {
                    maximo = tierra.getTotalDescargado();
                    mayores.clear();
                    mayores.add(tierra);
                }
                else if (tierra.getTotalDescargado() == maximo)
                {
                    mayores.add(tierra);
                }
            }
        }

        System.out.println("\nMódulos de tierra: " + cantidadTierra);
        System.out.println("Módulos de tierra activos: " + cantidadActivos);
        System.out.println("Capacidad activa total por ciclo: " + capacidadActiva);
        System.out.println("Total histórico descargado: " + totalHistorico);

        if (mayores.isEmpty())
        {
            System.out.println("No hay módulos de tierra.");
        }
        else
        {
            System.out.println("Mayor total histórico por módulo: " + maximo);
            for (ModuloTierra tierra : mayores)
            {
                System.out.println(tierra.mostrarInformacion());
            }
        }
    }
}


