package sys.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import sys.dao.clienteDao;
import sys.dao.detalleFacturaDao;
import sys.dao.facturaDao;
import sys.dao.productoDao;
import sys.imp.clienteDaoImp;
import sys.imp.detalleFacturaDaoImp;
import sys.imp.facturaDaoImp;
import sys.imp.productoDaoImp;
import sys.model.Cliente;
import sys.model.Detallefactura;
import sys.model.Factura;
import sys.model.Producto;
import sys.model.Vendedor;
import sys.util.HibernateUtil;

@ManagedBean
@ViewScoped
public class facturaBean {

    Session session = null;
    Transaction transaction = null;

    private Cliente cliente;
    private Integer codigoCliente;

    private Producto producto;
    private String codigoBarra;
    
    private Vendedor vendedor;
    private Integer codigovendedor;

    private List<Detallefactura> listaDetallefactura;

    private String cantidadProducto;
    private String productoSeleccionado;
    private Factura factura;

    private String cantidadProducto2;
    
    private Long numeroFactura;
    
    private BigDecimal totalVentaFactura;

    public facturaBean() {
        this.factura = new Factura();
        this.listaDetallefactura = new ArrayList<>();
        this.vendedor = new Vendedor();
        this.cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<Detallefactura> getListaDetallefactura() {
        return listaDetallefactura;
    }

    public void setListaDetallefactura(List<Detallefactura> listaDetallefactura) {
        this.listaDetallefactura = listaDetallefactura;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public String getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(String productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public String getCantidadProducto2() {
        return cantidadProducto2;
    }

    public void setCantidadProducto2(String cantidadProducto2) {
        this.cantidadProducto2 = cantidadProducto2;
    }

    public Long getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(Long numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public BigDecimal getTotalVentaFactura() {
        return totalVentaFactura;
    }

    public void setTotalVentaFactura(BigDecimal totalVentaFactura) {
        this.totalVentaFactura = totalVentaFactura;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getCodigovendedor() {
        return codigovendedor;
    }

    public void setCodigovendedor(Integer codigovendedor) {
        this.codigovendedor = codigovendedor;
    }
    
    //METODO PARA MOSTRAR LOS DATOS DE LOS CLIENTES POR MEDIO DEL DIALOGCLIENTES
    public void agregarDatosCliente(Integer codCliente) {
        this.session = null;
        this.transaction = null;

        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //OBTENER LOS DATOS DEL CLIENTE EN LA VARIABLE OBJETO CLIENTE, SEGUN EL CODIGO DEL CLIENTE
            this.cliente = cDao.obtenerClientePorCodigo(this.session, codCliente);

            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Datos del Cliente Agregados"));

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }
    

    //METODO PARA MOSTRAR LOS DATOS DE LOS CLIENTES BUSCADO POR CODCLIENTE
    public void agregarDatosCliente2() {
        this.session = null;
        this.transaction = null;

        try {
            if (this.codigoCliente == null) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            clienteDao cDao = new clienteDaoImp();
            this.transaction = this.session.beginTransaction();

            //OBTENER LOS DATOS DEL CLIENTE EN LA VARIABLE OBJETO CLIENTE, SEGUN EL CODIGO DEL CLIENTE
            this.cliente = cDao.obtenerClientePorCodigo(this.session, this.codigoCliente);

            if (this.cliente != null) {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Cliente Agregado"));
            } else {
                this.codigoCliente = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cliente No Encontrado"));
            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //METODO PARA SOLICITAR LA CANTIDAD DE PRODUCTOS A VENDER
    public void pedirCantidadProducto(String codBarra) {
        this.productoSeleccionado = codBarra;
    }

    //METODO PARA MOSTRAR LOS DATOS DE LOS PRODUCTOS POR MEDIO DEL DIALOGPRODUCTOS
    public void agregarDatosProducto() {
        this.session = null;
        this.transaction = null;

        try {
            if (!(this.cantidadProducto.matches("[0-9]") || this.cantidadProducto.equals("0") || this.cantidadProducto.equals(""))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cantidad Incorrecta"));
                this.cantidadProducto="";
            } else {
                this.session = HibernateUtil.getSessionFactory().openSession();
                productoDao pDao = new productoDaoImp();
                this.transaction = this.session.beginTransaction();

                //OBTENER LOS DATOS DEL PRODUCTO EN LA VARIABLE OBJETO PRODUCTO, SEGUN EL CODIGO DE BARRAS
                this.producto = pDao.obtenerProductoPorCodigoBarra(this.session, this.productoSeleccionado);

                this.listaDetallefactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),
                        this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto), this.producto.getPrecioVenta(),
                        BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto) * this.producto.getPrecioVenta().floatValue())));

                this.transaction.commit();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto Agregado al Detalle"));

                //LLAMADA AL METODO CALCULAR TOTAL FACTURA VENTA
                this.calcularTotalFactura();

                this.cantidadProducto = "";
            }

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //METODO PARA MOSTRAR EL DIALOGCANTIDADPRODUCTO2
    public void mostrarCantidadProducto2() {
        this.session = null;
        this.transaction = null;

        try {
            if (this.codigoBarra.equals("")) {
                return;
            }

            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();

            this.transaction = this.session.beginTransaction();

            //OBTENER LOS DATOS DEL CLIENTE EN LA VARIABLE OBJETO CLIENTE, SEGUN EL CODIGO DEL CLIENTE
            this.producto = pDao.obtenerProductoPorCodigoBarra(this.session, this.codigoBarra);

            if (this.producto != null) {
                //solicitar mostrar el dialog cantidadproducto2

                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dialogCantidadProducto2').show();");

                this.codigoBarra = null;

            } else {
                this.codigoBarra = null;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Producto No Encontrado"));
            }

            this.transaction.commit();

        } catch (Exception e) {
            if (this.transaction != null) {
                System.out.println(e.getMessage());
                transaction.rollback();
            }
        } finally {
            if (this.session != null) {
                this.session.close();
            }
        }
    }

    //METODO PARA MOSTRAR LOS DATOS DE LOS PRODUCTOS BUSCADO POR CODBARRA
    public void agregarDatosProducto2() {
        if (!(this.cantidadProducto2.matches("[0-9]") || this.cantidadProducto2.equals("0") || this.cantidadProducto2.equals(""))) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cantidad Incorrecta"));
                this.cantidadProducto2="";
            } else {
        this.listaDetallefactura.add(new Detallefactura(null, null, this.producto.getCodBarra(),
                this.producto.getNombreProducto(), Integer.parseInt(this.cantidadProducto2), this.producto.getPrecioVenta(),
                BigDecimal.valueOf(Integer.parseInt(this.cantidadProducto2) * this.producto.getPrecioVenta().floatValue())));
        this.cantidadProducto2 = "";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Producto Agregado"));

        //LLAMADA AL METODO CALCULAR TOTALFACTURA
        this.calcularTotalFactura();
        }
    }

    //METODO PARA CALUCLAR EL TOTAL A VENDER EN LA FACTURA
    public void calcularTotalFactura() {
        this.totalVentaFactura = new BigDecimal("0");

        try {
            for (Detallefactura item : listaDetallefactura) {
                BigDecimal totalVentaPorProducto = item.getPrecioVenta().multiply(new BigDecimal(item.getCantidad()));
                item.setTotal(totalVentaPorProducto);
                totalVentaFactura = totalVentaFactura .add(totalVentaPorProducto);
            }

            this.factura.setTotalVenta(totalVentaFactura );

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //METODO PARA QUITAR PRODUCTO DE LA FACTURA
    public void quitarProductoDetalleFactura(String codBarra, Integer filaSeleccionada){
        try {
            int i=0;
            for (Detallefactura item : this.listaDetallefactura) {
                if(item.getCodBarra().equals(codBarra) && filaSeleccionada.equals(i)){
                    this.listaDetallefactura.remove(i);
                    break;
                }
                i++;
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Información", "Se retiró el producto de la factura"));
            
            //INVOCAMOS AL METODO CALCULAR FACTURA, PARA ACTUALIZAR EL TOTAL A VENDER
            this.calcularTotalFactura();
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error",e.getMessage()));
        }
    }
    //METODOS PARA EDITAR LA CANTIDAD DE PRODUCTO EN LA TABLA PRODUCTOS FACTURA
    public void onRowEdit(RowEditEvent event) {        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información","Se Modificó la cantidad"));
        //Invocar al método calcularTotalFactura
        this.calcularTotalFactura();
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información","No se hizo ningún cambio"));
        //Invocar al método calcularTotalFactura
        this.calcularTotalFactura();
    }
    
    //METODO PARA GENERAR EL NUMERO DE FACTURA
    public void numeracionFactura(){
        this.session=null;
        this.transaction=null;
        
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            this.transaction = this.session.beginTransaction();
            facturaDao fDao = new facturaDaoImp();
            //VERIFICAR SI HAY REGISTROS EN LA TABLA FACTURA DE LA BD
            this.numeroFactura = fDao.obtenerTotalRegistrosEnFactura(this.session);
            
            if (this.numeroFactura <=0 || this.numeroFactura==null) {
                this.numeroFactura = Long.valueOf("1");
            }else{
                //RECUPERAMOS EL ULTIMO REGISTRO QUE EXISTA EN LA TABLA FACTURA DE LA BD
                this.factura = fDao.obtenerUltimoRegistro(this.session);
                this.numeroFactura = Long.valueOf(this.factura.getNumeroFactura()+1);
                //LIMPIAR LA VARIABLE TOTALVENTAFACTURA
                this.totalVentaFactura= new BigDecimal("0");
            }
            
            this.transaction.commit();
            
        } catch (Exception e) {
            if (this.transaction != null) {
                this.transaction.rollback();
            }
            System.out.println(e.getMessage());
        }finally{
            if (this.session!=null) {
                this.session.close();
            }
        }
    }
    
    //METODO PARA LIMPIAR LA FACTURA
    public void limpiarFactura(){
        this.cliente = new Cliente();
        this.factura = new Factura();
        this.listaDetallefactura = new ArrayList<>();
        this.numeroFactura = null;
        this.totalVentaFactura = null;
        
        //INVOCANDO AL MÉTODO PARA DESACTIVAR CONTROLES EN LA FACTURA
        this.disableButton();
    }
    
    //METODO PARA GUARDAR VENTA
    public void guardarVenta(){
        this.session = null;
        this.transaction = null;
        this.vendedor.setCodVendedor(4);
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            productoDao pDao = new productoDaoImp();
            facturaDao fDao = new facturaDaoImp();
            detalleFacturaDao dFDao = new detalleFacturaDaoImp();
            
            this.transaction = this.session.beginTransaction();
            
            //DATOS PARA GUARDAR EN FACTURA DE LA BD
            this.factura.setNumeroFactura(this.numeroFactura.intValue());
            this.factura.setCliente(this.cliente);
            this.factura.setVendedor(this.vendedor);
            
            //hacemos el insert a la tabla factura en la BD
            fDao.guardarVentaFactura(this.session, this.factura);
            
            //RECUPERAR EL ULTIMO REGISTRO DE LA TABLA FACTURA
            this.factura=fDao.obtenerUltimoRegistro(this.session);
            
            //RECORREMOS EL ARRAYLIST PARA GUARDAR CADA REGISTRO EN LA BD
            for (Detallefactura item : listaDetallefactura) {
                this.producto = pDao.obtenerProductoPorCodigoBarra(this.session, item.getCodBarra());
                item.setFactura(this.factura);
                item.setProducto(this.producto);
                
                //HACEMOS EL INSERT EN LA TABLA DETALLEFACTURA EN LA BD
                dFDao.guardarVentaDetalleFactura(this.session, item);
            }
            
            this.transaction.commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto","Venta Registrada"));
            
            this.limpiarFactura();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (this.transaction!=null) {
                this.transaction.rollback();
            }
        }finally{
            if (this.session!=null) {
                this.session.clear();
            }
        }
    }
    
    //METODOS PARA ACTIVAR O DESACTIVAR LOS CONTROLES EN LA FACTURA
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public void enableButton(){
        enabled = true;
    }
    
    public void disableButton(){
        enabled = false;
    }
    
    //RECUPERAR FECHA DEL SISTEMA
    private String fechaSistema;
    
    public String getFechaSistema(){
        Calendar fecha = new GregorianCalendar();
        
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        
        this.fechaSistema = (dia + "/" + (mes+1) + "/" + anio);
        
        return fechaSistema;
    }

}
