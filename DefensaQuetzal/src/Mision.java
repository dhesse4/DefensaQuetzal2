public class Mision 
{
    private String id;
    private String operacion;
    private Double energiaDisponible;
    private Double datosOrbita;
    private Double datosDescargados;

    public Mision(String id, String operacion, Double energiaDisponible, Double datosOrbita, Double datosDescargados) 
    {
        this.id = id;
        this.operacion = operacion;
        this.energiaDisponible = energiaDisponible;
        this.datosOrbita = datosOrbita;
        this.datosDescargados = datosDescargados;
    }

    public String getId() 
    {
        return id;
    }
    public void setId(String id) 
    {
        this.id = id;
    }

    public String getOperacion() 
    {
        return operacion;
    }
    public void setOperacion(String operacion) 
    {
        this.operacion = operacion;
    }

    public Double getEnergiaDisponible() 
    {
        return energiaDisponible;
    }
    public void setEnergiaDisponible(Double energiaDisponible) 
    {
        this.energiaDisponible = energiaDisponible;
    }
    public void actualizarEnergia(Double energia) 
    {
        this.energiaDisponible -= energia;
    }

    public Double getDatosOrbita() 
    {
        return datosOrbita;
    }
    public void setDatosOrbita(Double datosOrbita) 
    {
        this.datosOrbita = datosOrbita;
    }
    public void reducirDatosOrbita(Double datos) 
    {
        this.datosOrbita -= datos;
    }

    public Double getDatosDescargados() 
    {
        return datosDescargados;
    }
    public void setDatosDescargados(Double datosDescargados) 
    {
        this.datosDescargados += datosDescargados;
    }

    @Override 
    public String toString() 
    {
        return "\nMision " + id + "\nOperacion: " + operacion + "\nEnergia disponible: " + energiaDisponible
                + "\nDatos en orbita: " + datosOrbita + "\nDatos descargados: " + datosDescargados + "\n";
    }

    public void datosSiguienteCiclo(Double energia, Double datosOrbita, Double datosDescargados) 
    {
        this.energiaDisponible = energia;
        this.datosOrbita = datosOrbita;
        this.datosDescargados = datosDescargados;
    }


}
