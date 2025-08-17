package com.sistema.examenes.servicios.impl;

import java.awt.print.Pageable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Categoria;
import com.sistema.examenes.entidades.Examen;
import com.sistema.examenes.repositorios.ExamenRepository;
import com.sistema.examenes.servicios.ExamenService;


@Service
public class ExamenServiceImpl implements ExamenService {
	
	
	@Autowired
	private ExamenRepository examenRepository;

	@Override
	public Examen agregarExamen(Examen examen) {
		// TODO Auto-generated method stub
		return examenRepository.save(examen);
	}	

	@Override
	public Examen actualizarExamen(Examen examen) {
		// TODO Auto-generated method stub
		return examenRepository.save(examen);
	}

	@Override
	public Set<Examen> obtenerExamenes() {
		// TODO Auto-generated method stub
		return new LinkedHashSet<>(examenRepository.findAll());
	}

	@Override
	public Examen obtenerExamen(Long examenId) {
		// TODO Auto-generated method stub
		return examenRepository.findById(examenId).get();
	}

	@Override
	public void eliminarExamen(Long examenId) {
		// TODO Auto-generated method stub
		Examen examen = new Examen();
		examen.setExamenId(examenId);
		examenRepository.delete(examen);
	}
	
	@Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivos() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria,true);
    }

    @Override
    public List<Examen> buscar(String keyword) {
        return examenRepository.buscar(keyword);
    }
  
    // contar estático
    @Override
    public Long obtenerCantidadExamenesDI() {
        return examenRepository.contarExamenesConEstadoDI();
    }
    // contar estático
    @Override
    public Long obtenerCantidadExamenesCon_OS_Siprec() {
        return examenRepository.contarExamenesConEstadoCon_OS_Siprec();
    }
    
    
    //contar dinámico
    
    public Long contarExamenesPorEstado(String estado) {
        return examenRepository.contarExamenesPorEstado(estado);
    }

    @Override
    public List<Object[]> obtenerCantidadExamenesPorCategoriaCon_OS_Siprec() {
        return examenRepository.contarExamenesPorCategoriaConEstadoCon_OS_Siprec();
    }
    
    
    //caso notificacion
    @Override
    public int obtenerCantidadExamenesCTLC(String ctlc) {
        return examenRepository.contarExamenesPorUsuarioCTLC(ctlc);
    }


//---------------------------inicio notificacion admin--------------------------------------------------------	
    @Override
    public void detectarCambiosCamposCriticos(Examen original, Examen modificado, String usuario) {

        if (!Objects.equals(original.getEstadoDeCalificacionDeLosCentros(), modificado.getEstadoDeCalificacionDeLosCentros())) {
            registrarCambio(modificado, "estadoDeCalificacionDeLosCentros", usuario);
        }

        if (!Objects.equals(original.getObservacion(), modificado.getObservacion())) {
            registrarCambio(modificado, "observacion", usuario);
        }

        if (!Objects.equals(original.getEvaluacion(), modificado.getEvaluacion())) {
            registrarCambio(modificado, "evaluacion", usuario);
        }

        if (!Objects.equals(original.getObservacionesEspecialistaDeOperaciones(), modificado.getObservacionesEspecialistaDeOperaciones())) {
            registrarCambio(modificado, "observacionesEspecialistaDeOperaciones", usuario);
        }

        if (!Objects.equals(original.getPropuestaDeSoluionTecnica(), modificado.getPropuestaDeSoluionTecnica())) {
            registrarCambio(modificado, "propuestaDeSoluionTecnica", usuario);
        }

        if (!Objects.equals(original.getTipoDeRecursosADemandar(), modificado.getTipoDeRecursosADemandar())) {
            registrarCambio(modificado, "tipoDeRecursosADemandar", usuario);
        }

        if (!Objects.equals(original.getObservacionEspInversiones(), modificado.getObservacionEspInversiones())) {
            registrarCambio(modificado, "observacionEspInversiones", usuario);
        }

        // Puedes agregar más comparaciones aquí si es necesario
    }

    
    private void registrarCambio(Examen examen, String campo, String usuario) {
        examen.setCampoModificado(campo);
        examen.setUsuarioQueModifico(usuario);
        examen.setFechaUltimaModificacion(java.time.LocalDateTime.now());
        examen.setNotificarAdministrador(true);
    }

    
    @Override
    public void desactivarNotificacion(Long examenId) {
        Examen examen = examenRepository.findById(examenId).orElseThrow();
        examen.setNotificarAdministrador(false);
        examen.setCampoModificado(null); // opcional
        examenRepository.save(examen);
    }

//--------------------------fin notificacion admin -----------------------------------------------------------   
	
   
}

//*******************************************-------------------------------*********************************


/*package com.sistema.examenes.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Categoria;
import com.sistema.examenes.entidades.Examen;
import com.sistema.examenes.repositorios.ExamenRepository;
import com.sistema.examenes.servicios.ExamenService;

@Service
public class ExamenServiceImpl implements ExamenService {

    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findById(examenId).orElse(null);
    }

    @Override
    public void eliminarExamen(Long examenId) {
        examenRepository.deleteById(examenId);
    }

    @Override
    public Page<Examen> listarExamenesDeUnaCategoria(Categoria categoria, Pageable pageable) {
        return examenRepository.findByCategoria(categoria, pageable);
    }

    @Override
    public Page<Examen> obtenerExamenesActivos(Pageable pageable) {
        return examenRepository.findByActivo(true, pageable);
    }

    @Override
    public Page<Examen> obtenerExamenesActivosDeUnaCategoria(Categoria categoria, Pageable pageable) {
        return examenRepository.findByCategoriaAndActivo(categoria, true, pageable);
    }

    @Override
    public Page<Examen> buscar(String keyword, Pageable pageable) {
        return examenRepository.buscar(keyword, pageable);
    }
}
*/
