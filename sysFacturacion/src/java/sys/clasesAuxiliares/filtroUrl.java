package sys.clasesAuxiliares;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class filtroUrl implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        
        //CAPTURAMOS EL NOMBRE DE LA PAGINA ACTUAL
        String currentPage = facesContext.getViewRoot().getViewId();
        
        //CREAMOS UNA VARIABLE BOOLEANA PARA COMPROBAR SI ES LA PAGINA DE LOGIN LA QUE SE CAPTURO
        boolean isPageLogin = currentPage.lastIndexOf("login.xhtml") > -1 ? true : false;
        
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        
        //RECUPEARAMOS UN OBJECTO DEL STRING QUE SE GUARDÓ, PARA ELLO SE TOMA LA SESIÓN AL USUARIO QUE SE DEFINIÓ EN EL LOGINBEAN
        Object usuario = session.getAttribute("usuario");
        
        if (!isPageLogin && usuario==null) {//SI NO ES LA PÁGINA DE LOGUEO Y EL USUARIO ES NULO, LO REDIRIGIMOS A LA PAGINA PRINCIPAL
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null,"/login.xhtml");            
        }
    }
    
    @Override
    public void beforePhase(PhaseEvent event) {        
    }
    
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }    
}
