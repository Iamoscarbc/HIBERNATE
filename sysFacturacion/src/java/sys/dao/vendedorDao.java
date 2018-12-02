package sys.dao;

import java.util.List;
import sys.model.Vendedor;

public interface vendedorDao {
    
    public List<Vendedor> listarVendedor();
    public void newVendedor(Vendedor vendedor);
    public void updateVendedor(Vendedor vendedor);
    public void deleteVendedor(Vendedor vendedor);
    
}
