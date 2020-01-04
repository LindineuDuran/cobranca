package com.lduran.cobranca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lduran.cobranca.model.StatusTitulo;
import com.lduran.cobranca.model.Titulo;
import com.lduran.cobranca.repository.Titulos;

@Service
public class CadastroTituloService
{
	@Autowired
	private Titulos titulos;

	public void salvar(Titulo titulo)
	{
		try
		{
			titulos.save(titulo);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo)
	{
		titulos.deleteById(codigo);
	}

	public String receber(Long codigo)
	{
		Optional<Titulo> titulo = titulos.findById(codigo);
		titulo.get().setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo.get());

		return StatusTitulo.RECEBIDO.getDescricao();
	}
}