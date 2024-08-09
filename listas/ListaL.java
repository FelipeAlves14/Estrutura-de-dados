public class ListaL{
    public class ListaVazia extends RuntimeException{
        public ListaVazia(String err){
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
    public ListaL(){
        init.next = fim;
        fim.prev = init;
    }
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return init.next == fim;
    }
    public boolean isFirst(No no){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return no == init.next;
    }
    public boolean isLast(No no){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return no == fim.prev;
    }
    public No first(){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return init.next;
    }
    public No last(){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        return fim.prev;
    }
    public No before(No no){
        if(n <= 1) throw new ListaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKKKK");
        if(isFirst(no)) return last();
        return no.prev;
    }
    public No after(No no){
        if(n <= 1) throw new ListaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKKKK");
        if(isLast(no)) return first();
        return no.next;
    }
    public Object replaceElement(No no, Object obj){
        Object retorno = no.element;
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        no.element = obj;
        return retorno;
    }
    public void swapElements(No um, No dois){
        if(n <= 1) throw new ListaVazia("Tem nem dois elemento ai KKKKKKKKKKKKKKKKKKKKK");
        Object aux = um.element;
        um.element = dois.element;
        dois.element = aux;
    }
    public void insertBefore(No before, Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko vai inserir antes de quem KKKKKKKKKKKKKKKKKKKKK");
        No novo = new No(obj);
        novo.next = before;
        novo.prev = before.prev;
        before.prev.next = novo;
        before.prev = novo;
        ++n;
    }
    public void insertAfter(No after, Object obj){
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko vai inserir depois de quem KKKKKKKKKKKKKKKKKKKKK");
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
        if(isEmpty()) throw new ListaVazia("Ta vazia oh loko KKKKKKKKKKKKKKKKKKKKK");
        no.next.prev = no.prev;
        no.prev.next = no.next;
        --n;
        return no.element;
    }
}