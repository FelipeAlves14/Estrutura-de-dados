public class PilhaDFila{
    public class EPilhaVazia extends RuntimeException{
        public EPilhaVazia(String err){
            super(err);
        }
    }
    private Fila datas = new Fila();
    private Fila ord_datas = new Fila();
    public PilhaDFila(){}
    public int size(){
        return datas.size();
    }
    public boolean isEmpty(){
        return datas.isEmpty();
    }
    public Object top(){
        if(datas.isEmpty()) throw new EPilhaVazia("Ta vazia maluco vai printar oq KKKKKKKKKKKKKKKKKKK");
        int size = datas.size() - 1;
        for(int i = 0; i < size; ++i) ord_datas.push(datas.pop());
        Object obj = datas.first();
        ord_datas.push(datas.pop());
        size = ord_datas.size();
        for(int i = 0; i < size; ++i) datas.push(ord_datas.pop());
        return obj;
    }
    public void push(Object obj){
        datas.push(obj);
    }
    public Object pop(){
        if(datas.isEmpty()) throw new EPilhaVazia("Ta vazia maluco vai popar oq KKKKKKKKKKKKKKKKKKK");
        int size = datas.size() - 1;
        for(int i = 0; i < size; ++i) ord_datas.push(datas.pop());
        Object obj = datas.pop();
        size = ord_datas.size();
        for(int i = 0; i < size; ++i) datas.push(ord_datas.pop());
        return obj;
    }
}