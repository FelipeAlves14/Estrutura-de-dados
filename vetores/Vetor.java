public class Vetor{
    public class IndexForaDoAlcance extends RuntimeException{
        public IndexForaDoAlcance(String err){
            super(err);
        }
    }
    public class VetorVazio extends RuntimeException{
        public VetorVazio(String err){
            super(err);
        }
    }
    private int i = 0, n = 0;
    private Object[] datas = new Object[10];
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n == i;
    }
    public Object elemAtRank(int index){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai mostrar oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index >= datas.length) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        return datas[(index + i) % datas.length];
    }
    public Object replaceAtRank(int index, Object obj){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai trocar oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index >= datas.length) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        Object retorno = datas[(index + i) % datas.length];
        datas[(index + i) % datas.length] = obj;
        return retorno;
    }
    public void insertAtRank(int index, Object obj){
        if(index < 0 || index >= datas.length) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        if(size() == datas.length){
            Object[] new_datas = new Object[datas.length * 2];
            for(int j = 0; j < n; ++j){
                new_datas[j] = datas[i];
                i = ++i % n;
            }
            i = 0;
            datas = new_datas;
        }
        if(index == 0 && datas[0] != null){
            i = i == 0 ? datas.length - 1 : --i;
            index = i;
            datas[index] = obj;
        }
        else{
            for(int j = n; j > index; --j)datas[j] = datas[j - 1];
            datas[(index + i) % datas.length] = obj;
        }
        ++n;
    }
    public Object removeAtRank(int index){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai remover oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index >= n) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        Object retorno = datas[(index + i) % datas.length];
        if(index == 0){
            datas[i] = null;
            i = ++i % n;
        }
        else{
            for (int j = (index + i) % datas.length; j < n; ++j){
                datas[(index + i) % datas.length] = datas[(index + i + 1) % datas.length];
                index = ++index % datas.length;
            }
        }
        --n;
        return retorno;
    }
}