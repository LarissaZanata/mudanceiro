package br.com.mudanceiro.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mudanceiro.model.MudancaImagens;
import br.com.mudanceiro.service.MudancaImagensService;;

@Service
public class MudancaImagensImpl implements MudancaImagensService{

	@Override
	public List<MudancaImagens> converteMudancaImagens(List<MultipartFile> files){
		List<MudancaImagens> imagens = new ArrayList<MudancaImagens>();
		
		if(!files.isEmpty() && !files.equals(null)) {
			for (MultipartFile file : files) {
				MudancaImagens mudancaImagens = new MudancaImagens();
				try {
					mudancaImagens.setImagem(file.getBytes());
				} catch (IOException e) {
					new IOException("ARquivo de imagem inválido: " + e);
				}
				imagens.add(mudancaImagens);
			}
		}
		return imagens;
	}

	
}
