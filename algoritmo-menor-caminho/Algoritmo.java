import java.lang.reflect.Array;
import java.util.*;

class Algoritmo {

}

class AlgoritmoAEstrela {

    public ArrayList<Vertice> menorCaminho(Integer[][] labirinto) {
        Vertice entrada = new Vertice();
        entrada.setValor(2);
        ArrayList<ArrayList<Vertice>> caminhos = new ArrayList<ArrayList<Vertice>>();
        ArrayList<Vertice> saidas = new ArrayList<Vertice>();
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                if (labirinto[i][j] == 2) {
                    entrada.setX(i);
                    entrada.setY(j);
                } else if (labirinto[i][j] == 3) {
                    Vertice saida = new Vertice();
                    saida.setX(i);
                    saida.setY(j);
                    saidas.add(saida);
                }
            }
        }
        for(Vertice saida : saidas){
            ArrayList<Vertice> caminho = new ArrayList<Vertice>();
            caminho.add(entrada);



            caminhos.add(caminho);

        }

    }

    public Integer heuristic(Vertice entrada, Vertice saida) {
        return (int) Math.sqrt(Math.pow(entrada.getX() - saida.getX(), 2) + Math.pow(entrada.getY() - saida.getY(), 2));
    }
}

class Vertice {
    private Integer valor;
    private Integer x;
    private Integer y;
    private Integer f;
    private Integer g;
    private Integer h;

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getValor() {
        return valor;
    }

    public Integer getF() {
        return f;
    }

    public Integer getG() {
        return g;
    }

    public Integer getH() {
        return h;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void setG(Integer g) {
        this.g = g;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public void setF(Integer f) {
        this.f = f;
    }
}