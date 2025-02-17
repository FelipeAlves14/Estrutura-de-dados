class Algoritmo{

}

class AlgoritmoAEstrela{

    public int[][] menorCaminho(int[][] labirinto){
        int entrada;
        ArrayList<int> saidas = new ArrayList<int>();
        for(int i = 0; i < labirinto.length; i++){
            for(int j = 0; j < labirinto[i].length; j++){
                if(labirinto[i][j] == 2) entrada = labirinto[i][j];
                else if(labirinto[i][j] == 3) saidas.push_back(labirinto[i][j]);
            }
        }
    }
}

class Vertice{
    private int valor;
    private ArrayList<Vertice> verticesComArestas = new ArrayList<Vertice>();

    public Vertice(int valor){
        this.valor = valor;
    }
}

class Aresta{
    private Vertice[] vertices = new Vertice[2];
}