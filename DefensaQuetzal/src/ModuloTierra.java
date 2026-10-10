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

    @Override
    public void procesarCiclo(Mision mision)
    {
        if(!super.getActivo())
        {
            System.out.println("El modulo " + getId() + " no esta activo, no se puede utilizar durante este ciclo.");
            return;
        }
        if(mision.getEnergiaDisponible() < this.consumoEnergia)
        {
            System.out.println("No hay suficiente energia para procesar el modulo " + getId() + ".");
            return;
        }
        if(mision.getDatosOrbita() <= 0)
        {
            System.out.println("No hay datos de la mision para descargar en el modulo " + getId() + ".");
            return;
        }
        else
        {
            if(mision.getDatosOrbita() < this.capacidadDescarga)
            {
                this.totalDescargado += mision.getDatosOrbita();
                mision.setDatosDescargados(this.totalDescargado);
                mision.setDatosOrbita(0.0);
                mision.actualizarEnergia(consumoEnergia);
            }
            else
            {
                this.totalDescargado += this.capacidadDescarga;
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
