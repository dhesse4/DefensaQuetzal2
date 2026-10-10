public class Modulos 
{
   private String id;
   private String nombre;
   private String estadoSalud;
   private Boolean activo;
   private Double costoConstruccion;

   public Modulos(String id, String nombre, String estadoSalud, Boolean activo, Double costoConstruccion) 
   {
      this.id = id;
      this.nombre = nombre;
      this.estadoSalud = estadoSalud;
      this.activo = activo;
      this.costoConstruccion = costoConstruccion;
   }

   public String getId() 
   {
      return id;
   }
   public void setId(String id) 
   {
      this.id = id;
   }

   public String getNombre() 
   {
      return nombre;
   }
   public void setNombre(String nombre) 
   {
      this.nombre = nombre;
   }

   public String getEstadoSalud() 
   {
      return estadoSalud;
   }
   public void setEstadoSalud(String estadoSalud) 
   {
      this.estadoSalud = estadoSalud;
   }

   public Boolean getActivo() 
   {
      return activo;
   }
   public void setActivo(Boolean activo) 
   {
      this.activo = activo;
   }

   public Double getCostoConstruccion() 
   {
      return costoConstruccion;
   }
   public void setCostoConstruccion(Double costoConstruccion) 
   {
      this.costoConstruccion = costoConstruccion;
   }

   public void procesarCiclo(Mision mision) 
   {
      // 
   }

   public String mostrarInformacion() 
   {
      return "\nID: " + id + "\nNombre: " + nombre + "\nEstado de Salud: " + estadoSalud + "\nActivo: " + activo + "\nCosto de Construcción: " + costoConstruccion;
   }
}