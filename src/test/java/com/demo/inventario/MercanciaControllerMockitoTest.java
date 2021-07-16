package com.demo.inventario;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.inventario.controllers.MercanciaController;
import com.demo.inventario.models.Mercancia;
import com.demo.inventario.services.CargoService;
import com.demo.inventario.services.MercanciaService;
import com.demo.inventario.services.UsuarioService;
/***
 * Project Name : inventario-back-nexos Username : Jorge Ortiz Date Time :
 * 15/07/2021
 */

@SpringBootTest
@AutoConfigureMockMvc
public class MercanciaControllerMockitoTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private MercanciaService mercanciaService;
	@Mock
	private UsuarioService usuarioService;
	@Mock
	private CargoService cargoService;


    @Autowired
    private ObjectMapper objectMapper;
    

    
	@Test
	public void createMercancia() {
		
		
		Mercancia mercancia = new Mercancia();
		mercancia.setNombreproducto("preuba 1");
		mercancia.setCantidad(456);
		mercancia.setFechaingreso(Calendar.getInstance().getTime());
		mercancia.setUsuarioingreso(usuarioService.findById(1).get());
		
		given(mercanciaService.save(any(Mercancia.class))).willAnswer((invocation) -> invocation.getArgument(0));

        try {
			this.mockMvc.perform(post("/mercancia/")
			        .contentType(MediaType.APPLICATION_JSON)
			        .content(objectMapper.writeValueAsString(mercancia)))
			        .andDo(print())
			        .andExpect(status().isCreated());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
