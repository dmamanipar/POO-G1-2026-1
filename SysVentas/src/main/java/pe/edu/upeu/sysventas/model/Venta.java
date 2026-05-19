package pe.edu.upeu.sysventas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    private Long idVenta;
    private Double preciobase;
    private Double igv;
    private Double preciototal;
    private String dniruc;
    private Usuario idUsuario;
    private String numDoc;
    private LocalDate fechaGener;
    private String serie;
    private String tipoDoc;
    private List<VentaDetalle> detalleVenta;
}