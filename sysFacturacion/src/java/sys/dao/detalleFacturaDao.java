package sys.dao;

import org.hibernate.Session;
import sys.model.Detallefactura;

public interface detalleFacturaDao {
    
    //METODO PARA GUARDAR EL REGISTRO EN LA TABLA FACTURA EN LA BD
    public boolean guardarVentaDetalleFactura(Session session, Detallefactura detallefactura)throws Exception;
    
}
