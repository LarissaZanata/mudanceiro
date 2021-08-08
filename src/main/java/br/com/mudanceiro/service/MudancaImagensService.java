package br.com.mudanceiro.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.mudanceiro.model.MudancaImagens;

public interface MudancaImagensService {

	List<MudancaImagens> converteMudancaImagens(List<MultipartFile> file);
}
