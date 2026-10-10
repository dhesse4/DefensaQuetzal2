public class ModulosEnegia extends Modulos //Debia ser energia pero funciona
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

    //Metodo sobrescrito, donde el modulo de energia le da su cantidad de energia a la mision para que pueda ser usado por los otros modulos
    @Override
    public void procesarCiclo(Mision mision)
    {
        if(!super.getActivo()) // si no esta activo, no puede dar energia, se muestra mensaje de error
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
