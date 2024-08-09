public class PilhaL{
    public class EPilhaVazia extends RuntimeException{
        public EPilhaVazia(String err){
            super(err);
        }
    }
    public class No{
        private Object element;
        private No next;
        public No(){}
    }
    private int n = 0;
    private No t;
    public PilhaL(){}
    public int size(){
        return n;
    }
    public boolean isEmpty(){
        return t == null;
    }
    public Object top(){
        if(isEmpty()) throw new EPilhaVazia("A pilha está vazia!");
        return t.element;
    }
    public void push(Object obj){
        No novo = new No();
        novo.element = obj;
        novo.next = t;
        t = novo;
        ++n;
    }
    public Object pop(){
        if(isEmpty()) throw new EPilhaVazia("A pilha está vazia!");
        Object obj = t.element;
        t = t.next;
        --n;
        return obj;
    }
}