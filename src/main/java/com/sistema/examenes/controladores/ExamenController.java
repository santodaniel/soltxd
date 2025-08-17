package com.sistema.examenes.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sistema.examenes.entidades.Categoria;
import com.sistema.examenes.entidades.Examen;

import com.sistema.examenes.servicios.ExamenService;

@RestController
@RequestMapping("/examen")
//@CrossOrigin("*")
@CrossOrigin(origins = "*")
public class ExamenController {
	
	
	@Autowired
	private ExamenService examenService;
	
	
	@PostMapping("/")
	ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen){
		return ResponseEntity.ok(examenService.agregarExamen(examen));	
	}
	/*   nota este es el metodo original
	@PutMapping("/")
	ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
		return ResponseEntity.ok(examenService.actualizarExamen(examen));	
	}
	*/
	/*------------------------------inicio actualizar notificacion admin------------------------------------------------------------*/
	

	@PutMapping("/")
	public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen) {

	    // 1. Buscar el examen original desde la base de datos
	    Examen original = examenService.obtenerExamen(examen.getExamenId());

	    // 2. Obtener el usuario autenticado desde Spring Security
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String usuarioActual = authentication != null ? authentication.getName() : "desconocido";

	    // 3. Detectar si hubo modificación en campos sensibles
	    examenService.detectarCambiosCamposCriticos(original, examen, usuarioActual);

	    // 4. Guardar el examen actualizado
	    Examen actualizado = examenService.actualizarExamen(examen);

	    // 5. Retornar la respuesta
	    return ResponseEntity.ok(actualizado);
	}
	
	
	@PutMapping("/notificacion/desactivar/{examenId}")
	public ResponseEntity<?> desactivarNotificacion(@PathVariable Long examenId) {
	    examenService.desactivarNotificacion(examenId);
	    return ResponseEntity.ok().build();
	}

	
	
	/*	@PutMapping("/")
	ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
	    // 1. Buscar el examen original desde la base de datos
	    Examen original = examenService.obtenerExamen(examen.getExamenId());

	    // 2. Detectar si hubo modificación en campos sensibles
	    examenService.detectarCambiosCamposCriticos(original, examen, "usuario_actual"); // Reemplaza por el nombre real o toma del contexto

	    // 3. Guardar el examen actualizado
	    Examen actualizado = examenService.actualizarExamen(examen);

	    // 4. Retornar respuesta
	    return ResponseEntity.ok(actualizado);
	}
*/	
/*	@PutMapping("/") otra variante
	public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen){
	    // 1. Buscar el examen original desde la base de datos
	    Examen original = examenService.obtenerExamen(examen.getExamenId());

	    // 2. Obtener el nombre de usuario autenticado
	    String usuarioActual = org.springframework.security.core.context.SecurityContextHolder
	                             .getContext()
	                             .getAuthentication()
	                             .getName();

	    // 3. Detectar si hubo modificaciones relevantes
	    examenService.detectarCambiosCamposCriticos(original, examen, usuarioActual);

	    // 4. Guardar el examen modificado
	    Examen actualizado = examenService.actualizarExamen(examen);

	    return ResponseEntity.ok(actualizado);
	}

*/
/* ********************************fin actualizar notificacion admin********************************************************** */	
	
	@GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }
	
	@GetMapping("/{examenId}")      
    public Examen listarExamen(@PathVariable("examenId") Long examenId){
        return examenService.obtenerExamen(examenId);
    } 
	
	@DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId){
        examenService.eliminarExamen(examenId);
    }  
	
	@GetMapping("/categoria/{categoriaId}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }
	
	 @GetMapping("/activo")
	    public List<Examen> listarExamenesActivos(){
	        return examenService.obtenerExamenesActivos();
	    }

	    @GetMapping("/categoria/activo/{categoriaId}")
	    public List<Examen> listarExamenesActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
	        Categoria categoria = new Categoria();
	        categoria.setCategoriaId(categoriaId);
	        return examenService.obtenerExamenesActivosDeUnaCategoria(categoria);
	    }

	    
	    
	    @GetMapping("/buscar")
	    public ResponseEntity<List<Examen>> buscar(@RequestParam(name = "keyword", required = false) String keyword) {
	        return ResponseEntity.ok(examenService.buscar(keyword));
	    }
	   
	    //contar estático
	    @GetMapping("/contarDI")
	    public ResponseEntity<Long> contarExamenesDI() {
	        return ResponseEntity.ok(examenService.obtenerCantidadExamenesDI());
	    }
	    
	  //contar estático
	    @GetMapping("/contarCon_OS_Siprec")
	    public ResponseEntity<Long> contarExamenesCon_OS_Siprec() {
	        return ResponseEntity.ok(examenService.obtenerCantidadExamenesCon_OS_Siprec());
	    }
	    
	    //contar dinámico
	    @GetMapping("/contar/{estado}")
	    public ResponseEntity<Long> contarExamenesPorEstado(@PathVariable String estado) {
	        Long cantidad = examenService.contarExamenesPorEstado(estado);
	        return ResponseEntity.ok(cantidad);
	    }
	    
	    //caso ok
	    @GetMapping("/contarCon_OS_SiprecPorCategoria")
	    public ResponseEntity<Map<String, Long>> contarExamenesPorCategoriaCon_OS_Siprec() {
	        List<Object[]> resultados = examenService.obtenerCantidadExamenesPorCategoriaCon_OS_Siprec();
	        Map<String, Long> respuesta = new HashMap<>();

	        for (Object[] resultado : resultados) {
	            respuesta.put((String) resultado[0], (Long) resultado[1]);
	        }

	        return ResponseEntity.ok(respuesta);
	    }
	    
	    // caso notificación
	    @GetMapping("/conteo/{ctlc}")
	    public ResponseEntity<Integer> contarExamenesCTLC(@PathVariable String ctlc) {
	        int cantidad = examenService.obtenerCantidadExamenesCTLC(ctlc);
	        return ResponseEntity.ok(cantidad);
	    }
	    
	    
	    @PostMapping("/examenes/importar")
	    public ResponseEntity<?> importarExcel(@RequestParam("file") MultipartFile file) {
	        try {
	            examenService.importarDesdeExcel(file);
	            return ResponseEntity.ok("Importación completada correctamente.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al importar: " + e.getMessage());
	        }
	    }

	    
	   


}//fin


//-------------------------********************************************-------------------------------
/*
package com.sistema.examenes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.sistema.examenes.entidades.Categoria;
import com.sistema.examenes.entidades.Examen;
import com.sistema.examenes.servicios.ExamenService;

@RestController
@RequestMapping("/examen")
@CrossOrigin(origins = "*")
public class ExamenController {

    @Autowired
    private ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Examen> guardarExamen(@RequestBody Examen examen) {
        return ResponseEntity.ok(examenService.agregarExamen(examen));
    }

    @PutMapping("/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen) {
        return ResponseEntity.ok(examenService.actualizarExamen(examen));
    }

    @GetMapping("/")
    public ResponseEntity<Page<Examen>> listarExamenes(Pageable pageable) {
        return ResponseEntity.ok(examenService.obtenerExamenesActivos(pageable));
    }

    @GetMapping("/{examenId}")
    public Examen listarExamen(@PathVariable("examenId") Long examenId) {
        return examenService.obtenerExamen(examenId);
    }

    @DeleteMapping("/{examenId}")
    public void eliminarExamen(@PathVariable("examenId") Long examenId) {
        examenService.eliminarExamen(examenId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Page<Examen>> listarExamenesDeUnaCategoria(
            @PathVariable("categoriaId") Long categoriaId, Pageable pageable) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return ResponseEntity.ok(examenService.listarExamenesDeUnaCategoria(categoria, pageable));
    }

    @GetMapping("/activo")
    public ResponseEntity<Page<Examen>> listarExamenesActivos(Pageable pageable) {
        return ResponseEntity.ok(examenService.obtenerExamenesActivos(pageable));
    }

    @GetMapping("/categoria/activo/{categoriaId}")
    public ResponseEntity<Page<Examen>> listarExamenesActivosDeUnaCategoria(
            @PathVariable("categoriaId") Long categoriaId, Pageable pageable) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return ResponseEntity.ok(examenService.obtenerExamenesActivosDeUnaCategoria(categoria, pageable));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<Examen>> buscar(@RequestParam(name = "keyword", required = false) String keyword, Pageable pageable) {
        return ResponseEntity.ok(examenService.buscar(keyword, pageable));
    }
}
*/