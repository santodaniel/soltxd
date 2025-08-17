package com.sistema.examenes.servicios.impl;

import java.awt.print.Pageable;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.examenes.entidades.Categoria;
import com.sistema.examenes.entidades.Examen;
import com.sistema.examenes.repositorios.ExamenRepository;
import com.sistema.examenes.servicios.ExamenService;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



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
	
    @Override
    public void importarDesdeExcel(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Saltar encabezado

                String titulo = getCellValue(row.getCell(0));
                String descripcion = getCellValue(row.getCell(1));
                String puntosMaximos = getCellValue(row.getCell(2));
                String numeroDePreguntas = getCellValue(row.getCell(3));
                String fechaStr = getCellValue(row.getCell(4));

                // Validaciones básicas
                if (titulo == null || descripcion == null) {
                    System.out.println("Fila " + row.getRowNum() + " incompleta. Saltada.");
                    continue;
                }

                LocalDate fechaDeSolicitud = null;
                try {
                    fechaDeSolicitud = LocalDate.parse(fechaStr);
                } catch (Exception e) {
                    System.out.println("Fecha inválida en fila " + row.getRowNum());
                }

                // Crear entidad
                Examen examen = new Examen();
                examen.setTitulo(titulo);
                examen.setDescripcion(descripcion);
                examen.setPuntosMaximos(puntosMaximos != null ? puntosMaximos : "100");
                examen.setNumeroDePreguntas(numeroDePreguntas != null ? numeroDePreguntas : "10");
                examen.setFechaDeSolicitud(fechaDeSolicitud);

                // Puedes setear usuario y categoría si tienes lógica para ello
                // examen.setUsuario(usuario);
                // examen.setCategoria(categoria);

                examenRepository.save(examen);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo Excel", e);
        }
    }

    
    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC: return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            default: return null;
        }
    }


    
   
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
