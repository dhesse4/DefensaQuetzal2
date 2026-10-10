public class ModulosVuelo extends Modulos
{   

    private String tipoInstrumento;
    private Double cantidadDatos;
    private Double consumoEnergia;
    private Integer cantidadCiclos;
    private Double totalHistorico;

    public ModulosVuelo(String id, String nombre, String estadoSalud, Boolean activo, Double costoConstruccion, String tipoInstrumento, Double cantidadDatos, Double consumoEnergia, Integer cantidadCiclos, Double totalHistorico) 
    {
        super(id, nombre, estadoSalud, activo, costoConstruccion);
        this.tipoInstrumento = tipoInstrumento;
        this.cantidadDatos = cantidadDatos;
        this.consumoEnergia = consumoEnergia;
        this.cantidadCiclos = cantidadCiclos;
        this.totalHistorico = totalHistorico;
    }

    public String getTipoInstrumento() 
    {
        return tipoInstrumento;
    }
    public void setTipoInstrumento(String tipoInstrumento) 
    {
        this.tipoInstrumento = tipoInstrumento;
    }

    public Double getCantidadDatos() 
    {
        return cantidadDatos;
    }
    public void setCantidadDatos(Double cantidadDatos) 
    {
        this.cantidadDatos = cantidadDatos;
    }

    public Double getConsumoEnergia() 
    {
        return consumoEnergia;
    }
    public void setConsumoEnergia(Double consumoEnergia) 
    {
        this.consumoEnergia = consumoEnergia;
    }

    public Integer getCantidadCiclos() 
    {
        return cantidadCiclos;
    }
    public void setCantidadCiclos(Integer cantidadCiclos) 
    {
        this.cantidadCiclos = cantidadCiclos;
    }

    public Double getTotalHistorico() 
    {
        return totalHistorico;
    }
    public void setTotalHistorico(Double totalHistorico) 
    {
        if(totalHistorico < 0)
        {
            System.out.println("El valor a agregar no puede ser negativo.");
        }
        else
        {
            this.totalHistorico = totalHistorico;
        }
    }

    // Metodo para reunir datos en la mision
    @Override
    public void procesarCiclo(Mision mision)
    {
        if (!getActivo()) 
        { // Si el modulo no esta activo, no pasa anda
            System.out.println("El módulo " + getId() + " no está activo.");
            return;
        }

        if (mision.getEnergiaDisponible() < consumoEnergia) //Si no hay energia suficiente, el modulo no puede trabajar
        { 
            System.out.println("No hay suficiente energía para el módulo " + getId() + ".");
            return;
        }
        
        //Si hay enegria y esta activo, se suben los datos a la orbita en base a la capacidad del modulo
        mision.setDatosOrbita(mision.getDatosOrbita() + cantidadDatos);
        mision.actualizarEnergia(consumoEnergia);
        totalHistorico += cantidadDatos;  // Se guarda el totalHistorico de datoas recibidos
        cantidadCiclos++;
    }

    @Override 
    public String mostrarInformacion() 
    {
        
        return super.mostrarInformacion() + "\nTipo de Instrumento: " + tipoInstrumento + "\nCantidad de Datos: " + cantidadDatos + "\nConsumo de Energia: " + consumoEnergia + "\nCantidad de Ciclos: " + cantidadCiclos + "\nTotal Historico: " + totalHistorico;
        
    }

}
