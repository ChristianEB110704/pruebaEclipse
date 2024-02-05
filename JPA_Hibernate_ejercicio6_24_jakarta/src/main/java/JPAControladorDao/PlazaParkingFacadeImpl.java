package JPAControladorDao;

import java.util.List;


import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


import entidades.PlazaParking;




//@Stateless
public class PlazaParkingFacadeImpl extends AbstractFacadeJPAImpl<PlazaParking> implements PlazaParkingFacade {


public PlazaParkingFacadeImpl() {

	super(PlazaParking.class);

}

public List<PlazaParking> mostrarTodos() {
    
    TypedQuery<PlazaParking> q = this.getEm().createQuery("SELECT p FROM PlazaParking AS p",PlazaParking.class);
	return q.getResultList();
}




}


