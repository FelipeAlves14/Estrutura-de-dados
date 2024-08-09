public class VetorL {
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
    public class No{
        private No next;
        private No prev;
        private Object element;
    }
    private int n = 0;
    private No init = new No();
    private No fim = new No();
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public Object elemAtRank(int index){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai mostrar oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index > size()) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        No atual = init.next;
        for (int i = 0; i < index; i++) atual = atual.next;
        return atual.element;
    }
    public Object replaceAtRank(int index, Object obj){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai trocar oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index > size()) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        No atual = init.next;
        for (int i = 0; i < index; i++) atual = atual.next;
        Object retorno = atual.element;
        atual.element = obj;
        return retorno;
    }
    public void insertAtRank(int index, Object obj){
        if(index < 0 || index > size()) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        No novo = new No();
        novo.element = obj;
        if(isEmpty()){
            init.next = novo;
            fim.prev = novo;
            novo.next = fim;
            novo.prev = init;
        }
        else{
            No atual = init.next;
            for (int i = 0; i < index; i++) atual = atual.next;
            novo.prev = atual.prev;
            novo.next = atual;
            atual.prev.next = novo;
            atual.prev = novo;
        }
        ++n;
    }
    public Object removeAtRank(int index){
        if(isEmpty()) throw new VetorVazio("Vetor ta vazio vai remover oq KKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        if(index < 0 || index > size()) throw new IndexForaDoAlcance("Hoooooooooooooomi bote um índice dentro do alcance");
        No atual = init.next;
        for (int i = 0; i < index; i++) atual = atual.next;
        Object retorno = atual.element;
        atual.next.prev = atual.prev;
        atual.prev.next = atual.next;
        --n;
        return retorno;
    }
}