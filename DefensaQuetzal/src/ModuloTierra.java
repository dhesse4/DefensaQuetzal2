public class ModuloTierra extends Modulos
{

    private Double capacidadDescarga;
    private Double consumoEnergia;
    private String estacion;
    private Double totalDescargado;

    public ModuloTierra(String id, String nombre, String estadoSalud, Boolean activo, Double costoConstruccion, Double capacidadDescarga, Double consumoEnergia, String estacion, Double totalDescargado) 
    {
        super(id, nombre, estadoSalud, activo, costoConstruccion);
        this.capacidadDescarga = capacidadDescarga;
        this.consumoEnergia = consumoEnergia;
        this.estacion = estacion;
        this.totalDescargado = totalDescargado;
    }
    
    public Double getCapacidadDescarga() 
    {
        return capacidadDescarga;
    }
    public void setCapacidadDescarga(Double capacidadDescarga) 
    {
        this.capacidadDescarga = capacidadDescarga;
    }

    public Double getConsumoEnergia() 
    {
        return consumoEnergia;
    }
    public void setConsumoEnergia(Double consumoEnergia) 
    {
        this.consumoEnergia = consumoEnergia;
    }

    public String getEstacion() 
    {
        return estacion;
    }
    public void setEstacion(String estacion) 
    {
        this.estacion = estacion;
    }

    public Double getTotalDescargado() 
    {
        return totalDescargado;
    }
    public void setTotalDescargado(Double totalDescargado) 
    {
        this.totalDescargado = totalDescargado;
    }

    // Metodo para descargar los datos enviados por los modulos de vuelo
    @Override
    public void procesarCiclo(Mision mision)
    {
        if(!super.getActivo()) // si no esta activo, se muestra mensaje de error y no pasa nada.
        {
            System.out.println("El modulo " + getId() + " no esta activo, no se puede utilizar durante este ciclo.");
            return;
        }
        if(mision.getEnergiaDisponible() < this.consumoEnergia) // si la mision no tiene suficiente energia para abastecer el modulo, no funciona
        {
            System.out.println("No hay suficiente energia para procesar el modulo " + getId() + ".");
            return;
        }
        if(mision.getDatosOrbita() <= 0) //Si no hay datos para descargar, no sucede nada
        {
            System.out.println("No hay datos de la mision para descargar en el modulo " + getId() + ".");
            return;
        }
        else
        {
            if(mision.getDatosOrbita() < this.capacidadDescarga) // si hay menos datos que la capacidad de descarga, se bajan todos los datos de la mision
            {
                this.totalDescargado += mision.getDatosOrbita();
                mision.setDatosDescargados(this.totalDescargado);
                mision.setDatosOrbita(0.0);
                mision.actualizarEnergia(consumoEnergia);
            }
            else // Si hay mas datos que la capacidad, simplemente se descarga la capacidad maxima de datos del modulo, dejando los demas en orbita
            {
                this.totalDescargado += this.capacidadDescarga; // Se guarda el total descargado por el modulo para sumarlo a su total para el resumen de coms
                mision.setDatosDescargados(this.totalDescargado);
                mision.reducirDatosOrbita(this.capacidadDescarga);
                mision.actualizarEnergia(consumoEnergia);
            }
            
        }
    }

    @Override
    public String mostrarInformacion() 
    {
        return super.mostrarInformacion() + "\nCapacidad de Descarga: " + capacidadDescarga + "\nConsumo de Energia: " + consumoEnergia + "\nTotal Descargado: " + totalDescargado;
    }

}
