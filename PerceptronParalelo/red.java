package PerceptronParalelo;


import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.IOException;

public class red{

    public perceptron [] perceptrones;
    public double lr;
    public static double margen;
    public static double ml;
    public int epocas;
    public static int numPerceptrones;
    public double [] z;
    public double resultado;
    public double target;
    public int totalAcertados;
    public static String ArchivoE;
    public static String ArchivoP;
    public tabla EM;

    public red(double LR, double MARGEN, int NP, double margen_limpio, int ep, String AE, String AP) throws IOException {
        System.out.printf("INICIALIZACION DE RED:\n\n\n");
        epocas = ep;
        ml = margen_limpio;
        lr = LR;
        margen = MARGEN;
        numPerceptrones = NP;
        perceptrones = new perceptron [numPerceptrones];
        totalAcertados = 0;
        ArchivoE = AE;
        ArchivoP = AP;
        Table easyMail = Table.read().usingOptions(CsvReadOptions
                .builder(ArchivoE)
                .header(false));
        EM = new tabla(easyMail.rowCount(), easyMail.columnCount());
        EM.setDatos(easyMail);
        EM.setTargets(easyMail);
        z = new double [EM.totalColumnas];
        for (int i = 0; i < numPerceptrones; i++){
            perceptrones[i] = new perceptron(EM.totalColumnas);
            perceptrones[i].InicializarPesos(i);
            System.out.printf("Pesos iniciales del perceptron: %d\n[", (i + 1));
            for (int j = 0; j < EM.totalColumnas; j++){
                System.out.printf("%f, ", perceptrones[i].alpha[j]);
            }
            System.out.printf("]\n");
        }
        System.out.println("Lenght de pesos" + perceptrones[0].alpha.length);
    }

    public void setZ(/*tabla EM,*/ int j){
        for(int i = 0; i < EM.totalColumnas; i++){
                z[i] = EM.datos[j][i];
        }
        if(EM.targets[j] > 1) {
            target = -1;
        }else{
            target = 1;
        }
    }

    public void setResultado(){
        int suma = 0;
        for(int i = 0; i < numPerceptrones; i++){
            suma = suma + perceptrones[i].s_p;
        }
        if(suma >= 0){
            resultado = 1;
        }else{
            resultado = -1;
        }
        System.out.printf(">PREDICCION = %f\n\n", resultado);
        if(resultado == target) totalAcertados++;
    }

    public void ActualizacionDePesos(){
        //Inciso A:
        for(int i = 0; i < numPerceptrones; i++){
            if(resultado > target && perceptrones[i].s_p >= 0){
                for(int j = 0; j < perceptrones[i].alpha.length; j++){
                    perceptrones[i].alpha[j] = perceptrones[i].alpha[j] - (lr * z[i]);
                }
            }else if(resultado < target && perceptrones[i].s_p < 0){
                for (int j = 0; j < perceptrones[i].alpha.length; j++) {
                    perceptrones[i].alpha[j] = perceptrones[i].alpha[j] + (lr * z[i]);
                }
            }else if(resultado <= target && perceptrones[i].s_p >= 0 && margen > perceptrones[i].s_p){
                for (int j = 0; j < perceptrones[i].alpha.length; j++) {
                    perceptrones[i].alpha[j] = perceptrones[i].alpha[j] + (lr * z[i] * ml);
                }
            }else if(resultado >= target && perceptrones[i].s_p < 0 && (margen * -1) < perceptrones[i].s_p){
                for (int j = 0; j < perceptrones[i].alpha.length; j++) {
                    perceptrones[i].alpha[j] = perceptrones[i].alpha[j] - (lr * z[i] * ml);
                }
            }
        }

        //Inciso B:
        double divisor = 0;
        for(int i = 0; i < numPerceptrones; i++){
            for (int j = 0; j < perceptrones[i].alpha.length; j++){
                divisor = divisor + Math.pow(perceptrones[i].alpha[j], 2);
            }
            divisor = Math.sqrt(divisor);
            for (int j = 0; j < perceptrones[i].alpha.length; j++){
                perceptrones[i].alpha[j] = perceptrones[i].alpha[j] / divisor;
            }
        }

        System.out.printf("Pesos de los perceptrones actualizados...\n");
    }

    public void ActualizarLR(int t){
        double divisor = 4 * Math.sqrt(t);
        lr = 1 / divisor;
    }

    public void EjecutarRed() throws IOException {
        //- entrenamiento:

        for(int k = 0; k < epocas; k++) {
            System.out.printf("EPOCA: %d\n\n\n", (k + 1));
            for (int i = 0; i < EM.totalFilas; i++) {
                System.out.printf("REGISTRO: %d\n", (i+1));
                setZ(i);
                System.out.printf(">OBJETIVO: %f\n", target);
                for (int j = 0; j < perceptrones.length; j++) {
                    //System.out.printf("Perceptron %d: ", j);
                    perceptrones[j].CalcularProductos(z);
                    perceptrones[j].Sumatoria();
                    //System.out.printf("Sumatoria de productos = %d\n", R.perceptrones[j].s_p);
                }
                setResultado();
                ActualizacionDePesos();
            }
            System.out.println("ACERTADOS: " + totalAcertados + " de 100\n\n");
            System.out.printf("------------------------------------------------------------------------------------------\n\n");
            if(k != epocas-1) totalAcertados = 0;
        }

        double precision =((double)totalAcertados / (double)EM.totalFilas) * 100;
        String p = precision + "%";
        System.out.printf("PRECISION DEL ENTRENAMIENTO:\n----> %s\n", p);
        totalAcertados = 0;

        //- Prueba:
        System.out.printf("***********************************************************************************************\n");
        Table easyMail = Table.read().usingOptions(CsvReadOptions
                .builder(ArchivoP)
                .header(false));
        EM = new tabla(easyMail.rowCount(), easyMail.columnCount());
        EM.setDatos(easyMail);
        EM.setTargets(easyMail);
        System.out.printf("\n\n\n\n INICIO DE PRUEBA: \n\n");
        for (int i = 0; i < EM.totalFilas; i++) {
            System.out.printf("REGISTRO: %d\n", (i+1));
            setZ(i);
            System.out.printf(">OBJETIVO: %f\n", target);
            for(int j = 0; j < perceptrones.length; j++) {
                //System.out.printf("Perceptron %d: \n",  j);
                perceptrones[j].CalcularProductos(z);
                perceptrones[j].Sumatoria();
                //System.out.printf("Sumatoria de productos = %d\n", R.perceptrones[j].s_p);
            }
            setResultado();
        }
        System.out.println("Acertados: " + totalAcertados + " de 20");
        precision =((double)totalAcertados / (double)EM.totalFilas) * 100;
        p = precision + "%";
        System.out.printf("PRECISION DE PRUEBA:\n----> %s\n\n\n", p);
    }

}
