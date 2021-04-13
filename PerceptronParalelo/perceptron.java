package PerceptronParalelo;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class perceptron {

    public double [] alpha;
    public double [] productoF;
    //public int [] producto;

    public int s_p;

    public perceptron(int tamano){
        alpha = new double [tamano];
        //producto = new int [tamano];
        productoF = new double [tamano];
        s_p = 0;
    }


    public void InicializarPesos(int row) throws IOException {
        for(int i = 0; i < alpha.length; i++){
            BigDecimal bd = BigDecimal.valueOf(Math.random() * (1.0 - (-1.0)) + (-1.0)).setScale(6, RoundingMode.HALF_EVEN);
            alpha[i] = bd.doubleValue();
        }
    }

    public void CalcularProductos(double [] z){
        for(int i = 0; i < alpha.length; i++) {
            productoF[i] = alpha[i] * z[i];
        }
    }

    public void Sumatoria(){
        double suma = 0;
        for(int i = 0; i < productoF.length; i++) {
            suma = suma + productoF[i];
        }
        if(suma >= 0){
            s_p = 1;
        }else{
            s_p = -1;
        }
    }



}
