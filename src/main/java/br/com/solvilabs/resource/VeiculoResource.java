package br.com.solvilabs.resource;

import br.com.solvilabs.model.Veiculo;
import br.com.solvilabs.service.VeiculoService;
import br.com.solvilabs.validation.VeiculoValidator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/veiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VeiculoResource {
    private final VeiculoService veiculoService = new VeiculoService();

    @GET
    public Response getAllVeiculos() {
        try {
            List<Veiculo> veiculos = veiculoService.getAllVeiculos();
            return Response.ok(veiculos).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar veículos").build();
        }
    }

    @GET
    @Path("/{placa}")
    public Response getVeiculoByPlaca(@PathParam("placa") String placa) {
        try {
            Veiculo veiculo = veiculoService.getVeiculoByPlaca(placa);
            if (veiculo == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Veículo não encontrado").build();
            }
            return Response.ok(veiculo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao buscar veículo").build();
        }
    }

    @POST
    public Response createVeiculo(Veiculo veiculo) {
        try {
            if (!VeiculoValidator.isPlacaValida(veiculo.getPlaca())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Placa inválida").build();
            }
            if (!VeiculoValidator.isMarcaValida(veiculo.getMarca())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Marca inválida").build();
            }
            if (!VeiculoValidator.isAnoValido(veiculo.getAno())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Ano inválido").build();
            }
            if (!veiculoService.isPlacaUnique(veiculo.getPlaca())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Placa já existe").build();
            }

            veiculoService.createVeiculo(veiculo);
            return Response.status(Response.Status.CREATED).entity(veiculo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar veículo").build();
        }
    }

    @PUT
    @Path("/{placa}")
    public Response updateVeiculo(@PathParam("placa") String placa, Veiculo veiculo) {
        try {
            veiculo.setPlaca(placa);

            if (!VeiculoValidator.isPlacaValida(veiculo.getPlaca())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Placa inválida").build();
            }
            if (!VeiculoValidator.isMarcaValida(veiculo.getMarca())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Marca inválida").build();
            }
            if (!VeiculoValidator.isAnoValido(veiculo.getAno())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Ano inválido").build();
            }

            veiculoService.updateVeiculo(veiculo);
            return Response.ok(veiculo).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar veículo").build();
        }
    }

    @DELETE
    @Path("/{placa}")
    public Response deleteVeiculo(@PathParam("placa") String placa) {
        try {
            veiculoService.deleteVeiculo(placa);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar veículo").build();
        }
    }

    @DELETE
    public Response deleteAllVeiculos() {
        try {
            veiculoService.deleteAllVeiculos();
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao deletar todos os veículos").build();
        }
    }
}
