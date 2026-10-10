public class ModulosEnegia extends Modulos
{
    private Double cantidadEnergia; 
    private Double energiaGenerada;
    private Integer cantidadCiclos;

    public ModulosEnegia(String id, String nombre, String estadoSalud, Boolean activo, Double costoConstruccion, Double cantidadEnergia, Double energiaGenerada, Integer cantidadCiclos) 
    {
        super(id, nombre, estadoSalud, activo, costoConstruccion);
        this.cantidadEnergia = cantidadEnergia;
        this.energiaGenerada = energiaGenerada;
        this.cantidadCiclos = cantidadCiclos;
    }

    public Double getCantidadEnergia() 
    {
        return cantidadEnergia;
    }
    public void setCantidadEnergia(Double cantidadEnergia) 
    {
        this.cantidadEnergia = cantidadEnergia;
    }

    public Double getEnergiaGenerada() 
    {
        return energiaGenerada;
    }
    public void setEnergiaGenerada(Double energiaGenerada) 
    {
        this.energiaGenerada = energiaGenerada;
    }

    public Integer getCantidadCiclos() 
    {
        return cantidadCiclos;
    }
    public void setCantidadCiclos(Integer cantidadCiclos) 
    {
        this.cantidadCiclos = cantidadCiclos;
    }

    @Override
    public void procesarCiclo(Mision mision)
    {
        if(!super.getActivo())
        {
            System.out.println("El modulo " + getId() + " no esta activo, no se puede utilizar durante este ciclo.");
            return;
        }
        else
        {
            mision.setEnergiaDisponible(mision.getEnergiaDisponible() + this.cantidadEnergia);
            this.cantidadCiclos += 1;
            energiaGenerada += this.cantidadEnergia;
        }
    }

    @Override 
    public String mostrarInformacion() 
    {
        return super.mostrarInformacion() + "\nCantidad de Energia que genera: " + cantidadEnergia + "\nEnergia Generada: " + energiaGenerada + "\nCantidad de Ciclos: " + cantidadCiclos;
    }
    
}
