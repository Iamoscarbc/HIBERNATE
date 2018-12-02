package sys.dao;

import java.util.List;
import sys.model.Producto;

public interface productoDao {
    
    public List<Producto> listarProductos();
    public void newProducto(Producto producto);
    public void updateProducto(Producto producto);
    public void deleteProducto(Producto producto);
    
}
