package sys.model;
// Generated 26/11/2018 05:47:27 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cliente generated by hbm2java
 */
public class Cliente  implements java.io.Serializable {


     private Integer codCliente;
     private String nombres;
     private String apellidos;
     private String direccion;
     private Set<Factura> facturas = new HashSet<Factura>(0);

    public Cliente() {
    }

	
    public Cliente(String nombres, String apellidos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
    }
    public Cliente(String nombres, String apellidos, String direccion, Set<Factura> facturas) {
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.direccion = direccion;
       this.facturas = facturas;
    }
   
    public Integer getCodCliente() {
        return this.codCliente;
    }
    
    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set<Factura> getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }




}


