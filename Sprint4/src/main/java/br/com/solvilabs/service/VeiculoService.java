package br.com.solvilabs.service;

import br.com.solvilabs.dao.VeiculoDAO;
import br.com.solvilabs.model.Veiculo;

import java.util.List;

public class VeiculoService {
    private final VeiculoDAO veiculoDAO = new VeiculoDAO();

    public List<Veiculo> getAllVeiculos() {
        return veiculoDAO.findAll();
    }

    public Veiculo getVeiculoByPlaca(String placa) {
        return veiculoDAO.findByPlaca(placa);
    }

    public void createVeiculo(Veiculo veiculo) {
        veiculoDAO.save(veiculo);
    }

    public void updateVeiculo(Veiculo veiculo) {
        veiculoDAO.update(veiculo);
    }

    public void deleteVeiculo(String placa) {
        veiculoDAO.delete(placa);
    }

    public void deleteAllVeiculos() {
        veiculoDAO.deleteAll();
    }

    public boolean isPlacaUnique(String placa) {
        return veiculoDAO.isPlacaUnique(placa);
    }
}
