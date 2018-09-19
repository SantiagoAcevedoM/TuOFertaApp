package com.negocio.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.negocio.model.RegistrarRequest;

@EnableScan
public interface NegocioRepository extends CrudRepository<RegistrarRequest, String>{
	//Filtros de búsqueda
	//public List<RegistrarRequest> findByRol(String rol);
	public List<RegistrarRequest> findByIdadmin(String idadmin);

	public List<RegistrarRequest> findByIdnegocio(String idnegocio);
	public List<RegistrarRequest> findByTipo(String idnegocio);


	
	//public List<RegistrarRequest> findAll();

}
