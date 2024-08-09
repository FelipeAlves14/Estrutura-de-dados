public class Sequencia{
    public class IndexForaDoAlcance extends RuntimeException{
        public IndexForaDoAlcance(String err){
            super(err);
        }
    }
    public class SequenciaVazia extends RuntimeException{
        public SequenciaVazia(String err){
            super(err);
        }
    }
    public class No{
        private Object element;
        private No next, prev;
        public No(Object elem){
            element = elem;
        }
        public Object getElement(){
            return element;
        }
    }
    private int n = 0;
    private No init = new No(null);
    private No fim = new No(null);
    public Sequencia(){
        init.next = fim;
        fim.prev = init;
    }
    public No atRank(int index){
        if(isEmpty()) throw new SequenciaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKK");
        if(index < 0 || index >= n) throw new IndexForaDoAlcance("Bote um índice direito ai por favor");
        No doIndex = init.next;
        for(int i = 0; i < index; ++i) doIndex = doIndex.next;
        return doIndex;
    }
    public int rankOf(No no){
        if(isEmpty()) throw new SequenciaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKK");
        No esseNo = init.next;
        for(int i = 0; i < n; ++i){
            if(esseNo == no) return i;
            esseNo = esseNo.next;
        }
        return -1;
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return n == 0;
    }
    public Object elemAtRank(int index){
        return atRank(index).element;
    }
    public Object replaceAtRank(int index, Object obj){
        No doIndex = atRank(index);
        Object retorno = doIndex.element;
        doIndex.element = obj;
        return retorno;
    }
    public void insertAtRank(int index, Object obj){
        No novo = new No(obj);
        if(isEmpty()){
            init.next = novo;
            fim.prev = novo;
            novo.next = fim;
            novo.prev = init;
        }
        else{
            No doIndex = atRank(index);
            novo.prev = doIndex.prev;
            novo.next = doIndex;
            doIndex.prev.next = novo;
            doIndex.prev = novo;
        }
        ++n;
    }
    public Object removeAtRank(int index){
        No doIndex = atRank(index);
        doIndex.prev.next = doIndex.next;
        doIndex.next.prev = doIndex.prev;
        --n;
        return doIndex.element;
    }
    public No first(){
        return init.next;
    }
    public No last(){
        return fim.prev;
    }
    public No before(No no){
        if(n <= 1) throw new SequenciaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKK");
        if(rankOf(no) == 0) return last();
        return no.prev;
    }
    public No after(No no){
        if(n <= 1) throw new SequenciaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKK");
        if(rankOf(no) == n - 1) return first();
        return no.next;
    }
    public Object replaceElement(No no, Object obj){
        Object retorno = no.element;
        no.element = obj;
        return retorno;
    }
    public void swapElements(No um, No dois){
        if(n <= 1) throw new SequenciaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKK");
        Object aux = um.element;
        um.element = dois.element;
        dois.element = aux;
    }
    public void insertBefore(No before, Object obj){
        if(isEmpty()) throw new SequenciaVazia("Ta vazia oh loko vai inserir atrás de quem KKKKKKKKKKKKKKKKKK");
        No novo = new No(obj);
        novo.prev = before.prev;
        novo.next = before;
        before.prev.next = novo;
        before.prev = novo;
        ++n;
    }
    public void insertAfter(No after, Object obj){
        if(isEmpty()) throw new SequenciaVazia("Ta vazia oh loko vai inserir depois de quem KKKKKKKKKKKKKKKKKK");
        No novo = new No(obj);
        novo.prev = after;
        novo.next = after.next;
        after.next.prev = novo;
        after.next = novo;
        ++n;
    }
    public void insertFirst(Object obj){
        No novo = new No(obj);
        novo.prev = init;
        novo.next = init.next;
        init.next.prev = novo;
        init.next = novo;
        ++n;
    }
    public void insertLast(Object obj){
        No novo = new No(obj);
        novo.next = fim;
        novo.prev = fim.prev;
        fim.prev.next = novo;
        fim.prev = novo;
        ++n;
    }
    public Object remove(No no){
        if(isEmpty()) throw new SequenciaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKK");
        no.prev.next = no.next;
        no.next.prev = no.prev;
        --n;
        return no.element;
    }
}