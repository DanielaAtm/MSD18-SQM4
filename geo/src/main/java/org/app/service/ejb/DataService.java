package org.app.service.ejb;

import javax.ejb.Remote;

import org.app.patterns.EntityRepository;
import org.app.service.entities.Team;


@Remote
public interface DataService extends EntityRepository<Team>{

	String sayRest();

}