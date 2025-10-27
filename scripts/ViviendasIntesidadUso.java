// Carlos C ^^

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ViviendasIntesidadUso {

    static class Municipio {
        String territorio;
        String codigo;
        double valor;

        Municipio(String territorio, String codigo, double valor) {
            this.territorio = territorio;
            this.codigo = codigo;
            this.valor = valor;
        }
    }

    private static void comprobarEntrada(int entrada) {
        if (entrada != 1) {
            System.out.println("Argumentos no validos\n" +
                "Ejemplo de uso del script: java ViviendasIntensidadUso ejemplo.csv");
        }
    }

    private static void leerCSV(String rutaCSV) {
        List<Municipio> municipios = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaCSV))) {
            String linea = br.readLine();

            while ((linea = br.readLine()) != null) {
                String [] partes = linea.split(";");
                
                String tipo = partes[1].trim();
                String codigo = partes[2].trim();
                String territorio = partes[3].trim();
                String valor = partes[4].trim();

                try {
                    double valorDouble = Double.parseDouble(valor);
                    municipios.add(new Municipio(territorio, codigo, valorDouble));
                } catch (Exception e) {
                    // Aquí no pongo nada por que saltarian muchos mensajes de municipios sin Double
                }
            }
        } catch (Exception e) {
            System.out.println("Algo a fallado");
        }
        mostrarTop3Municipios(municipios);
    }
    
    private static void mostrarTop3Municipios(List<Municipio> municipios) {
        municipios.sort((a, b) -> Double.compare(b.valor, a.valor));

        System.out.println("Municipios con mediana más alta:");
        System.out.println();
        municipios.stream().limit(3).forEach(m -> {
            System.out.println("Territorio: " + m.territorio);
            System.out.println("Valor: " + m.valor);
            System.out.println("Código: " + m.codigo);
            System.out.println("--------------------------");
        });
    }

    public static void main(String[] args) {
        comprobarEntrada(args.length);
        leerCSV(args[0]);
    }
}
