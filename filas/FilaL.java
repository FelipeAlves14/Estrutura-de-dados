public class FilaL {
    public class EFilaVazia extends RuntimeException{
        public EFilaVazia(String err){
            super(err);
        }
    }
    public class No{
        private Object element;
        private No next;
        public No(){}
    }
    private int n = 0;
    private No i, f;
    public FilaL(){}
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return i == f;
    }
    public Object first(){
        if(isEmpty()) throw new EFilaVazia("A fila está vazia!");
        return i.element;
    }
    public void push(Object obj){
        No novo = new No();
        novo.element = obj;
        f.next = novo;
        f = novo;
        ++n;
    }
    public Object pop(){
        Object obj = i.element;
        i = i.next;
        --n;
        return obj;
    }
}