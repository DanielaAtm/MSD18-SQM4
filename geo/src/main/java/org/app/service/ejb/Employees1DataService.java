package org.app.service.ejb;


import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Employees1;
@Remote
public interface Employees1DataService extends EntityRepository<Employees1>{
	
    //Others
	String getMessage();

	String sayrest();
	
	

}
