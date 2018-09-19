package com.accenture.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.accenture.model.RegistrarRequest;

@EnableScan
public interface OfertaRepository extends CrudRepository<RegistrarRequest, String> {
	public List<RegistrarRequest> findByFechaFinal(String fechaFinal);
	public List<RegistrarRequest> findByIdnegocio(String idnegocio);
	public RegistrarRequest findById(String id);
	public List<RegistrarRequest> findAllByTipo(String tipo);
	public List<RegistrarRequest> findAllByIdnegocioAndTipo(String idnegocio,String tipo);
	public List<RegistrarRequest> findAllByIdnegocioAndTipoAndDescuento(String idnegocio,String tipo, String descuento);
	public List<RegistrarRequest> findDistinctDescuentoByDescuento(String descuento);

	public List<RegistrarRequest> findAll();
	

}
