package Modelos;

public class ResultadoConversion {
    private String result;
    private double conversion_rate;
    private double conversion_result;
    private double conversion_origin;
    private String base_code;
    private String target_code;

    public ResultadoConversion(ConversionExchange miConversion) {
        this.result = miConversion.result();
        this.conversion_result = miConversion.conversion_result();
        this.conversion_rate = miConversion.conversion_rate();
        this.base_code = miConversion.base_code();
        this.target_code = miConversion.target_code();
    }

    public ResultadoConversion(ResultadoConversion resultadoConversion) {
        this.result = resultadoConversion.result;
        this.conversion_rate = resultadoConversion.conversion_rate;
        this.conversion_result = resultadoConversion.conversion_result;
        this.conversion_origin = resultadoConversion.conversion_origin;
        this.base_code = resultadoConversion.base_code;
        this.target_code = resultadoConversion.target_code;
    }


    @Override
    public String toString() {
        return "\nResultado de la Conversión:    " + conversion_origin + " " + base_code + " = " +
                    conversion_result + " " + target_code
                    + "\n           (Tipo de cambio = " + conversion_rate + " "
                    + " " + base_code + " / " + target_code + ")\n";
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_origin(double conversion_origin) {
        this.conversion_origin = conversion_origin;
    }

    public ResultadoConversion(String result, double conversion_rate, double conversion_result, double conversion_origin, String base_code, String target_code) {
        this.result = result;
        this.conversion_rate = conversion_rate;
        this.conversion_result = conversion_result;
        this.conversion_origin = conversion_origin;
        this.base_code = base_code;
        this.target_code = target_code;
    }
}



