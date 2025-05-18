package br.com.solvilabs.dao;

import br.com.solvilabs.model.Veiculo;
import br.com.solvilabs.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public List<Veiculo> findAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM T_SL_VEICULO";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setQuilometragem(rs.getInt("quilometragem"));
                veiculo.setDiagnostico(rs.getString("diagnostico"));
                veiculo.setSintomas(rs.getString("sintomas"));
                veiculo.setTipoProblema(rs.getString("tipo_problema"));
                veiculo.setCustoEstimado(rs.getDouble("custo_estimado"));
                veiculos.add(veiculo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    public Veiculo findByPlaca(String placa) {
        Veiculo veiculo = null;
        String sql = "SELECT * FROM T_SL_VEICULO WHERE placa = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, placa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setPlaca(rs.getString("placa"));
                    veiculo.setModelo(rs.getString("modelo"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setAno(rs.getInt("ano"));
                    veiculo.setQuilometragem(rs.getInt("quilometragem"));
                    veiculo.setDiagnostico(rs.getString("diagnostico"));
                    veiculo.setSintomas(rs.getString("sintomas"));
                    veiculo.setTipoProblema(rs.getString("tipo_problema"));
                    veiculo.setCustoEstimado(rs.getDouble("custo_estimado"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public boolean isPlacaUnique(String placa) {
        String sql = "SELECT COUNT(*) FROM T_SL_VEICULO WHERE placa = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, placa);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void save(Veiculo veiculo) {
        String sql = "INSERT INTO T_SL_VEICULO (placa, modelo, marca, ano, quilometragem, diagnostico, sintomas, tipo_problema, custo_estimado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, veiculo.getPlaca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getMarca());
            pstmt.setInt(4, veiculo.getAno());
            pstmt.setInt(5, veiculo.getQuilometragem());
            pstmt.setString(6, veiculo.getDiagnostico());
            pstmt.setString(7, veiculo.getSintomas());
            pstmt.setString(8, veiculo.getTipoProblema());
            pstmt.setDouble(9, veiculo.getCustoEstimado());
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update(Veiculo veiculo) {
        String sql = "UPDATE T_SL_VEICULO SET modelo = ?, marca = ?, ano = ?, quilometragem = ?, diagnostico = ?, sintomas = ?, tipo_problema = ?, custo_estimado = ? WHERE placa = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, veiculo.getModelo());
            pstmt.setString(2, veiculo.getMarca());
            pstmt.setInt(3, veiculo.getAno());
            pstmt.setInt(4, veiculo.getQuilometragem());
            pstmt.setString(5, veiculo.getDiagnostico());
            pstmt.setString(6, veiculo.getSintomas());
            pstmt.setString(7, veiculo.getTipoProblema());
            pstmt.setDouble(8, veiculo.getCustoEstimado());
            pstmt.setString(9, veiculo.getPlaca());
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void delete(String placa) {
        String sql = "DELETE FROM T_SL_VEICULO WHERE placa = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, placa);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM T_SL_VEICULO";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
