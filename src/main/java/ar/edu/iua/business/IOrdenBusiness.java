package ar.edu.iua.business;

import ar.edu.iua.business.exception.BusinessException;
import ar.edu.iua.business.exception.NotFoundException;
import ar.edu.iua.model.Orden;

import java.util.List;

public interface IOrdenBusiness {

    public Orden load(Long id) throws BusinessException, NotFoundException;

    public List<Orden> list() throws BusinessException;

    public Orden save(Orden orden) throws BusinessException;

    public void delete(Long id) throws BusinessException, NotFoundException;

}
