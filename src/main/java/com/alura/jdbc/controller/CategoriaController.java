package com.alura.jdbc.controller;

import com.alura.jdbc.DAO.categoriaDAO;
import com.alura.jdbc.factory.connectionF;
import com.alura.jdbc.modelo.Categoria;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

	public List<Categoria> listar() {
            
		return new categoriaDAO(new connectionF().create()).Read();
	}

    public List<?> cargaReporte() {
        // TODO
        return new ArrayList<>();
    }

}
