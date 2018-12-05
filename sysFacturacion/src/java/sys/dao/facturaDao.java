package sys.dao;

import org.hibernate.Session;
import sys.model.Factura;

public interface facturaDao {
    //OBTENER ULTIMO REGISTRO EN LA TABLA FACTURA EN LA BD
    public Factura obtenerUltimoRegistro(Session session)throws Exception;
    //AVERIGUAR SI LA TABLA FACTURA POSEE REGISTROS
    public Long obtenerTotalRegistrosEnFactura(Session session);
    //METODO PARA GUARDAR EL REGISTRO EN LA TABLA FACTURA EN LA BD
    public boolean guardarVentaFactura(Session session, Factura factura)throws Exception;
    
}
