package PerceptronParalelo;

import tech.tablesaw.api.Table;

public class tabla {

    public double [][] datos;
    public double [] targets;
    public double [] promedios;
    public double [] des_estandar;
    public int totalColumnas;
    public int totalFilas;

    public tabla(int filas, int columnas){
        totalColumnas = columnas;
        totalFilas = filas;
        datos = new double[filas][columnas];
        targets = new double[filas];
        promedios = new double[columnas];
        des_estandar = new double[columnas];
    }

    public void setDatos(Table tab){
        for(int i = 0; i < tab.rowCount(); i++){
            for (int j = 0; j < tab.columnCount(); j++){
                if(j == tab.columnCount() - 1) datos[i][j] = 1.0;
                datos[i][j] = tab.doubleColumn(j).get(i);
            }
        }
    }

    public void setTargets(Table tab){
        for(int i = 0; i < tab.rowCount(); i++) targets[i] = tab.doubleColumn(tab.columnCount() - 1).get(i);
    }

    public void setPromedios(){
        double suma = 0;
        for (int i = 0; i < totalColumnas; i++){
            for (int j = 0; j < totalFilas; j++){
                suma = suma + datos[j][i];
            }
            promedios[i] = suma / totalFilas;
            suma = 0;
        }
    }

    public void setDes_estandar(){
        double suma = 0;
        double varianza;
        double calculo;
        for (int i = 0; i < totalColumnas; i++){
            for (int j = 0; j < totalFilas; j++){
                calculo = Math.pow((datos[j][i] - promedios[i]), 2);
                suma = suma + calculo;
            }
            varianza = suma / totalFilas;
            varianza = Math.sqrt(varianza);
            des_estandar[i] = varianza;
            suma = 0;
        }
    }

    public void estandarizarDatos(){
        double temp;
        setPromedios();
        setDes_estandar();
        for(int i = 0; i < totalFilas; i++){
            for(int j = 0; j < totalColumnas; j++){
                temp = datos[i][j] - promedios[j];
                datos[i][j] = temp / des_estandar[j];
            }
        }
    }

    public void ImprimirTabla(){
        for (int i = 0; i < totalFilas; i++){
            for (int j = 0; j < totalColumnas; j++){
                System.out.printf("%f ", datos[i][j]);
            }
            System.out.println();
        }
    }

    public void info(){
        System.out.println();
        System.out.printf("TARGETS: ");
        for(int i = 0; i < totalFilas; i++){
            System.out.printf("%f ", targets[i]);
        }
        System.out.println();
        System.out.printf("PROMEDIOS: ");
        for(int i = 0; i < totalFilas; i++){
            System.out.printf("%f ", promedios[i]);
        }
        System.out.println();
        System.out.printf("DESVIACIONES ESTANDAR: ");
        for(int i = 0; i < totalFilas; i++){
            System.out.printf("%f ", des_estandar[i]);
        }
    }


}
