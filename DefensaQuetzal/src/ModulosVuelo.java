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


    @Override
    public void procesarCiclo(Mision mision)
    {
        if (!getActivo()) {
            System.out.println("El módulo " + getId() + " no está activo.");
            return;
        }

        if (mision.getEnergiaDisponible() < consumoEnergia) {
            System.out.println("No hay suficiente energía para el módulo " + getId() + ".");
            return;
        }

        mision.setDatosOrbita(mision.getDatosOrbita() + cantidadDatos);
        mision.actualizarEnergia(consumoEnergia);
        totalHistorico += cantidadDatos; // Requiere cambiar totalHistorico a Double
        cantidadCiclos++;
    }

    @Override 
    public String mostrarInformacion() 
    {
        
        return super.mostrarInformacion() + "\nTipo de Instrumento: " + tipoInstrumento + "\nCantidad de Datos: " + cantidadDatos + "\nConsumo de Energia: " + consumoEnergia + "\nCantidad de Ciclos: " + cantidadCiclos + "\nTotal Historico: " + totalHistorico;
        
    }

}
